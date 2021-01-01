package com.pg.programmerground.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {
    @GetMapping("/auth")
    public OAuth2AccessToken auth(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        System.out.println(authorizedClient.getAccessToken().toString());
        System.out.println(authorizedClient.getClientRegistration().getClientSecret());
        return authorizedClient.getAccessToken();
    }

    @GetMapping("/")
    public String index() {
        return "awdawd";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/userInfo")
    public Principal getUser(Principal principal) {
        return principal;
    }
}
