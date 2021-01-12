package com.pg.auth.config;


import com.pg.auth.service.UserService;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JdbcOperations operations;
    private final ClientRegistrationRepository registrationRepository;
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated();
        http.oauth2Login()
                .authorizedClientService(new JdbcOAuth2AuthorizedClientService(operations, registrationRepository))
                .successHandler(this.successHandler());
    }

    private AuthenticationSuccessHandler successHandler() {
        //인증 성공시
        return (request, response, authentication) -> {
            userService.createUser((OAuth2AuthenticationToken) authentication);
            response.sendRedirect("/getToken");
        };
    }

}
