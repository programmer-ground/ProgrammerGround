package com.pg.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

@Service
public class MyOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        System.out.println("ddddddddd");
        System.out.println("ssssssssss");
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {

    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

    }
}