package com.pg.auth.service;

import com.pg.auth.entity.Oauth2AuthorizedClient;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final JdbcOAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final UserRepository userRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(JdbcOperations operations, ClientRegistrationRepository registrationRepository, UserRepository userRepository, Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.oauth2AuthorizedClientRepository = oauth2AuthorizedClientRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.oAuth2AuthorizedClientService = new JdbcOAuth2AuthorizedClientService(operations, registrationRepository);
    }

    @Transactional
    public void createUser(OAuth2AuthenticationToken authentication) {
        //OAuth 유저 정보를 가져옴
        OAuth2AuthorizedClient client =
                oAuth2AuthorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        //null인 경우 처리
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository.findById(Long.valueOf(client.getPrincipalName())).get();
        //신규 유저인지 체크
        /*if(userRepository.findByOAuthId(Long.valueOf(client.getPrincipalName())) == null) {
            User user = User.builder()
                    .userName(authentication.getPrincipal().getAttribute("name"))
                    //.OAuthId(Long.valueOf(client.getPrincipalName()))
                    .OAuthName(authentication.getPrincipal().getAttribute("login"))
                    .build();
            userRepository.save(user);
        }*/
    }

    public String createJwtToken() {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient client =
                oAuth2AuthorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return jwtTokenProvider.createToken(
                client.getAccessToken().getTokenValue(),
                Long.valueOf(client.getPrincipalName()),
                roles);
    }
}
