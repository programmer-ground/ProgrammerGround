package com.pg.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JdbcOperations operations;
    private final ClientRegistrationRepository registrationRepository;
    @GetMapping("/accessToken")
    public ResponseEntity<OAuth2AuthorizedClient> index(Authentication authentication) {
        JdbcOAuth2AuthorizedClientService oAuth2AuthorizedClientService = new JdbcOAuth2AuthorizedClientService(operations, registrationRepository);
        System.out.println(authentication.getName());
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient("github", authentication.getName());
        System.out.println(client.getAccessToken());
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
