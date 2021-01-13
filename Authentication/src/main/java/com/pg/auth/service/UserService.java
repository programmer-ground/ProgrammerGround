package com.pg.auth.service;

import com.pg.auth.entity.Oauth2AuthorizedClient;
import com.pg.auth.entity.User;
import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.jwtConfig.JwtTokenProvider;
import com.pg.auth.repository.Oauth2AuthorizedClientRepository;
import com.pg.auth.repository.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.oauth2AuthorizedClientRepository = oauth2AuthorizedClientRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public void createUser(OAuth2AuthenticationToken authentication) throws OAuthLoginException {
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
                .findById(Long.valueOf(authentication.getName())).orElseThrow(() -> new OAuthLoginException("OAuth 로그인 에러"));

        //신규 유저인지 체크
        if(userRepository.findByOauth2AuthorizedClient(authorizedClient) == null) {
            OAuth2User oAuth2User = authentication.getPrincipal();
            User user = User.builder()
                    .userName(oAuth2User.getAttribute("name"))
                    .oauth2AuthorizedClient(authorizedClient)
                    .OAuthName(oAuth2User.getAttribute("login"))
                    .Role(oAuth2User.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority).
                            collect(Collectors.joining(",")))
                    .build();
            userRepository.save(user);
        }
    }

    public String createJwtToken() throws OAuthLoginException {
        //인증 객체 가져옴
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        //인증 객체의 OAuthId로 DB에서 OAUTH 데이터 가져옴
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
                .findById(Long.valueOf(authentication.getName())).orElseThrow(() -> new OAuthLoginException("OAuth 로그인 에러"));

        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return jwtTokenProvider.createToken(
                authorizedClient.getAccessTokenValue(),
                authorizedClient.getId(),
                roles);
    }
}
