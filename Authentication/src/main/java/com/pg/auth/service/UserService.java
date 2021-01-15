package com.pg.auth.service;

import com.pg.auth.entity.Oauth2AuthorizedClient;
import com.pg.auth.entity.User;
import com.pg.auth.exception.InvalidCodeException;
import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.jwtConfig.JwtTokenProvider;
import com.pg.auth.repository.Oauth2AuthorizedClientRepository;
import com.pg.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private static final int VALID_CODE = 0;
    private final UserRepository userRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private JdbcOAuth2AuthorizedClientService jdbcService;

    //jdbc service에서 load하여 가져온다.
    public UserService(UserRepository userRepository, Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository, JwtTokenProvider jwtTokenProvider, JdbcOperations operations, ClientRegistrationRepository registrationRepository) {
        this.userRepository = userRepository;
        this.oauth2AuthorizedClientRepository = oauth2AuthorizedClientRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jdbcService = new JdbcOAuth2AuthorizedClientService(operations, registrationRepository);
    }

    /**
     * OAuth 인증 성공후 유저 생성
     */
    @Transactional
    public User createUser(OAuth2AuthenticationToken authentication) throws OAuthLoginException {
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
                .findById(Long.valueOf(authentication.getName())).orElseThrow(() -> new OAuthLoginException("OAuth 로그인 에러"));

        UUID loginCode = UUID.randomUUID();
        User user = userRepository.findByOauth2AuthorizedClient(authorizedClient);

        //신규 유저 체크
        if (user == null) {
            OAuth2User oAuth2User = authentication.getPrincipal();
            User createUser = User.builder()
                    .userName(oAuth2User.getAttribute("name"))
                    .oauth2AuthorizedClient(authorizedClient)
                    .OAuthName(oAuth2User.getAttribute("login"))
                    .code(loginCode.toString())
                    .Role(oAuth2User.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority).
                            collect(Collectors.joining(",")))
                    .build();
            return userRepository.save(createUser);
        }
        user.setCode(loginCode.toString());
        return user;
    }

    /**
     * OAuth 인증 후 발급된 Code를 확인해 Jwt 발급
     */
    @Transactional
    public String jwtLogin(String code, Long id) throws InvalidCodeException {
        User user = userRepository.findByCodeAndOauth2AuthorizedClient(code, oauth2AuthorizedClientRepository.findById(id).orElseThrow());
        if(user == null || !validateLoginCode(code, user)) {
            throw new InvalidCodeException("Login Code 에러");
        }
        user.setCode("");   //인증 완료후 code는 지워준다.
        return createJwtToken(user);
    }

    /**
     * 인증 된 AuthenticationToken을 통해 JWT 토큰 생성
     */
    public String createJwtToken(User user) {
        return jwtTokenProvider.createToken(
                user.getOauth2AuthorizedClient().getAccessTokenValue(),
                user.getOauth2AuthorizedClient().getId(),
                user.getId(),
                Arrays.stream(user.getRole().split(",")).map(String::new).collect(Collectors.toList()));
    }

    /**
     * 코드 검증
     */
    private boolean validateLoginCode(String code, User user) {
        //유저 id와 code를 비교하여 판별
        return UUID.fromString(code).compareTo(UUID.fromString(user.getCode())) == VALID_CODE;
    }
}
