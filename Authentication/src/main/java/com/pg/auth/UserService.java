package com.pg.auth;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {
    private final JdbcOAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final UserRepository userRepository;

    public UserService(JdbcOperations operations, ClientRegistrationRepository registrationRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.oAuth2AuthorizedClientService = new JdbcOAuth2AuthorizedClientService(operations, registrationRepository);
    }

    @Transactional
    public void createUser(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient("github", authentication.getName());
        //신규 로그인 유저라면
        Integer test = authentication.getPrincipal().getAttribute("id");

        //System.out.println(client.getPrincipalName());
        //User a = userRepository.findByOAuthId(Long.valueOf(client.getPrincipalName()));
        if(userRepository.findByOAuthId(Long.valueOf(client.getPrincipalName())) == null) {
            User user = User.builder()
                    .userName(authentication.getPrincipal().getAttribute("name"))
                    .OAuthId(Long.valueOf(client.getPrincipalName()))
                    .OAuthName(authentication.getPrincipal().getAttribute("login"))
                    .build();
            userRepository.save(user);
        }


        //System.out.println(client.getAccessToken().getTokenValue());
        /*String accessToken = authentication.*/
    }
}
