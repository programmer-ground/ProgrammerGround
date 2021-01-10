package com.pg.auth.service;

import com.pg.auth.entity.User;
import com.pg.auth.repository.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //신규 유저인지 체크
        if(userRepository.findByOAuthId(Long.valueOf(client.getPrincipalName())) == null) {
            User user = User.builder()
                    .userName(authentication.getPrincipal().getAttribute("name"))
                    .OAuthId(Long.valueOf(client.getPrincipalName()))
                    .OAuthName(authentication.getPrincipal().getAttribute("login"))
                    .build();
            userRepository.save(user);
        }
    }
}
