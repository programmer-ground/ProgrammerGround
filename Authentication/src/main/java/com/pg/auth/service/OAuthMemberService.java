package com.pg.auth.service;

import com.pg.auth.domain.OAuthMember;
import com.pg.auth.domain.github.Oauth2AuthorizedClient;
import com.pg.auth.exception.InvalidCodeException;
import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.jwtConfig.JwtTokenProvider;
import com.pg.auth.repository.Oauth2AuthorizedClientRepository;
import com.pg.auth.repository.OAuthMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OAuthMemberService {
    private static final int VALID_CODE = 0;
    private final OAuthMemberRepository oAuthMemberRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    private final JwtTokenProvider jwtTokenProvider;


    //jdbc service에서 load하여 가져온다.
    public OAuthMemberService(OAuthMemberRepository oAuthMemberRepository, Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository, JwtTokenProvider jwtTokenProvider) {
        this.oAuthMemberRepository = oAuthMemberRepository;
        this.oauth2AuthorizedClientRepository = oauth2AuthorizedClientRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * OAuth 인증 성공후 유저 생성
     */
    @Transactional
    public OAuthMember createUser(OAuth2AuthenticationToken authentication) throws OAuthLoginException {
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
                .findById(Long.valueOf(authentication.getName())).orElseThrow(() -> new OAuthLoginException("OAuth 로그인 에러"));

        UUID loginCode = UUID.randomUUID();
        OAuthMember oAuthMember = oAuthMemberRepository.findByOauth2AuthorizedClient(authorizedClient);

        //신규 유저 체크
        if (oAuthMember == null) {
            OAuth2User oAuth2User = authentication.getPrincipal();
            OAuthMember createOAuthMember = OAuthMember.builder()
                    .userName(oAuth2User.getAttribute("name"))
                    .oauth2AuthorizedClient(authorizedClient)
                    .OAuthName(oAuth2User.getAttribute("login"))
                    .code(loginCode.toString())
                    .Role(oAuth2User.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority).
                            collect(Collectors.joining(",")))
                    .build();
            return oAuthMemberRepository.save(createOAuthMember);
        }
        oAuthMember.setCode(loginCode.toString());
        return oAuthMember;
    }

    /**
     * OAuth 인증 후 발급된 Code를 확인해 Jwt 발급
     */
    @Transactional
    public String jwtLogin(String code, Long id) throws InvalidCodeException {
        OAuthMember oAuthMember = oAuthMemberRepository.findByCodeAndOauth2AuthorizedClient(code, oauth2AuthorizedClientRepository.findById(id).orElseThrow());
        if (oAuthMember == null || !validateLoginCode(code, oAuthMember)) {
            throw new InvalidCodeException("Login Code 에러");
        }
        oAuthMember.setCode("");   //인증 완료후 code는 지워준다.
        return createJwtToken(oAuthMember);
    }

    /**
     * JWT 토큰 생성
     */
    public String createJwtToken(OAuthMember oAuthMember) {
        return jwtTokenProvider.createToken(
                oAuthMember.getOauth2AuthorizedClient().getAccessTokenValue(),
                oAuthMember.getOauth2AuthorizedClient().getId(),
                oAuthMember.getId(),
                Arrays.stream(oAuthMember.getRole().split(",")).map(String::new).collect(Collectors.toList()));
    }

    /**
     * 코드 검증
     */
    private boolean validateLoginCode(String code, OAuthMember oAuthMember) {
        //유저 id와 code를 비교하여 판별
        return UUID.fromString(code).compareTo(UUID.fromString(oAuthMember.getCode())) == VALID_CODE;
    }
}
