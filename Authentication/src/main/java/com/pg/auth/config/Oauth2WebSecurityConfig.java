package com.pg.auth.config;


import com.pg.auth.domain.OAuthUser;
import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.service.OAuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JdbcOperations operations;
    private final ClientRegistrationRepository registrationRepository;
    private final OAuthUserService OAuthUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/jwtLogin", "/reissued").permitAll()
                .mvcMatchers("/oauth2/**", "/err", "/loginCode", "/test-token").permitAll()
                .anyRequest().authenticated();
        http.httpBasic().disable();
        //OAuthLogin 설정
        http.oauth2Login()
                .authorizedClientService(new JdbcOAuth2AuthorizedClientService(operations, registrationRepository))
                .successHandler(this.successHandler())
                .failureHandler(failureHandler());
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * OAUTH 인증 성공 후 처리
     * 유저 정보가 존재하지 않으면(최초 로그인) DB에 유저 정보 저장
     * /getToken으로 보내 JWT 토큰을 생성해 보냄
     *
     * @return
     * @throws OAuthLoginException
     */
    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            try {
                OAuthUser user = OAuthUserService.createUser((OAuth2AuthenticationToken) authentication);
                //response.sendRedirect("/loginCode?code=" + user.getCode() + "&oauthId=" + user.getOauth2AuthorizedClient().getId()); //code와 id를 같이 보내준다.
                response.sendRedirect("http://localhost:3000/login?code=" + user.getCode() + "&oauthId=" + user.getOauth2AuthorizedClient().getId()); //code와 id를 같이 보내준다.
            } catch (OAuthLoginException | InterruptedException | ExecutionException loginException) {
                loginException.printStackTrace();
                //프론트 오류로 넘길 예정
                response.sendRedirect("/err");
            }
        };
    }

    /**
     * 인증 실패시
     */
    private AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
            //프론트 에러 페이지로 넘김
            response.sendRedirect("localhost:3000");
        };
    }

}
