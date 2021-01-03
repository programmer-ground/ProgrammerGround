package com.pg.programmerground.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pg.programmerground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class Oauth2AuthConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth2/**").authenticated()
                .anyRequest().authenticated();
        http.oauth2Login().userInfoEndpoint().userService(userService);
                //.and().loginPage("/awd");

    }
}
