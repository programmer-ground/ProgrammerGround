package com.pg.programmerground.config;

import com.pg.programmerground.MyOAuth2AuthenticationManager;
import com.pg.programmerground.MyOAuth2AuthorizedClientService;
import com.pg.programmerground.MyOAuth2ProcessingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pg.programmerground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated();
        http.oauth2Login().userInfoEndpoint()
                .userService(userService);
        //http.oauth2Login().authorizedClientService(authorizedClientService());
                //.and().loginPage("/awd");

    }

    /*@Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new MyOAuth2AuthenticationManager();
    }*/

    //@Bean
    protected OAuth2AuthorizedClientService authorizedClientService() {
        return new MyOAuth2AuthorizedClientService();
    }

    public MyOAuth2ProcessingFilter myOAuth2ProcessingFilter(AuthenticationManager authenticationManager) throws Exception {
        return new MyOAuth2ProcessingFilter(authenticationManager);
    }
}
