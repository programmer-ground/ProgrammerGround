package com.pg.programmerground.auth.config;

import com.pg.programmerground.auth.MyOAuth2ProcessingFilter;
import com.pg.programmerground.auth.jwt.JwtAuthenticationProvider;
import com.pg.programmerground.auth.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    JwtTokenProvider jwtTokenProvider;
    JwtAuthenticationProvider jwtAuthenticationProvider;

    private static final RequestMatcher PUBLIC_URLS =
            new OrRequestMatcher(
                    new AntPathRequestMatcher("/"),
                    new AntPathRequestMatcher("/favicon.ico/**"),
                    new AntPathRequestMatcher("/error"),
                    new AntPathRequestMatcher("/swagger-ui.html/**"),
                    new AntPathRequestMatcher("/configuration/**"),
                    new AntPathRequestMatcher("/swagger-resources/**"),
                    new AntPathRequestMatcher("/v2/api-docs"),
                    new AntPathRequestMatcher("/images/**"),
                    new AntPathRequestMatcher("/webjars/**"),
                    new AntPathRequestMatcher("/webjars/springfox-swagger-ui/*.{js,css}"));        //인증안할 것들 넣기

    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

    public Oauth2WebSecurityConfig(JwtTokenProvider jwtTokenProvider, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    /**
     * Security 설정
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource());
        http.csrf().disable();
        http.addFilterBefore(buildProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    /**
     * 인증을 진행할 Filter 객체를 생성
     */
    private MyOAuth2ProcessingFilter buildProcessingFilter() throws Exception {
        MyOAuth2ProcessingFilter filter = new MyOAuth2ProcessingFilter(PROTECTED_URLS, jwtTokenProvider);
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }

    /**
     * AuthenticationManager에 Provider를 등록한다.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}
