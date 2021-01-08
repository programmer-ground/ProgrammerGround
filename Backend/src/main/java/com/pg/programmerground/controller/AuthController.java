package com.pg.programmerground.controller;

import com.pg.programmerground.service.GithubApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final GithubApiService githubApiService;

    @GetMapping("/auth")
    public OAuth2AccessToken auth(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        System.out.println(authorizedClient.getAccessToken().toString());
        System.out.println(authorizedClient.getClientRegistration().getClientSecret());
        return authorizedClient.getAccessToken();
    }

    @GetMapping("/")
    public String index() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "awdawd";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    //프론트에서 api 요청할 때 인증이 되있지 않으면 이 URL로 리다이렉트
    @GetMapping("/loginRequest")
    public String loginRequest() {
        return "awd";
    }

    @GetMapping("/userInfo")
    public Principal getUser(Principal principal, Authentication authentication) throws Exception {
        //githubApiService.getGithubTotalData();
        return principal;
    }
}
