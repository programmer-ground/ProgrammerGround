package com.pg.auth.config;


import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JdbcOperations operations;
    private final ClientRegistrationRepository registrationRepository;
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth2/**", "/errors").permitAll()
                .anyRequest().authenticated();
        http.oauth2Login()
                .authorizedClientService(new JdbcOAuth2AuthorizedClientService(operations, registrationRepository))
                .successHandler(this.successHandler());
    }

    /**
     * OAUTH 인증 성공 후 처리
     * 유저 정보가 존재하지 않으면(최초 로그인) DB에 유저 정보 저장
     * /getToken으로 보내 JWT 토큰을 생성해 보냄
     * @return
     * @throws OAuthLoginException
     */
    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            try {
                userService.createUser((OAuth2AuthenticationToken) authentication);
                response.sendRedirect("/getToken");
            } catch (OAuthLoginException loginException) {
                log.info(loginException.getMessage());
                response.sendRedirect("/err");
            }
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return null;
    }

}
