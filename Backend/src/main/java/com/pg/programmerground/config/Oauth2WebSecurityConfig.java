package com.pg.programmerground.config;

import com.pg.programmerground.jwt.JwtAuthenticationProvider;
import com.pg.programmerground.jwt.JwtTokenProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    JwtTokenProvider jwtTokenProvider;
    JwtAuthenticationProvider jwtAuthenticationProvider;

    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(new AntPathRequestMatcher("/admin"));

    public Oauth2WebSecurityConfig(JwtTokenProvider jwtTokenProvider, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    /**
     * Security 설정
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated();

        //UsernamePasswordAuthenticationFilter를 거치기 전에 Custom필터를 거친다.
        http.addFilterBefore(buildProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 인증을 진행할 Filter 객체를 생성
     */
    private MyOAuth2ProcessingFilter buildProcessingFilter() throws Exception {
        MyOAuth2ProcessingFilter filter = new MyOAuth2ProcessingFilter(PUBLIC_URLS, jwtTokenProvider);
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }

    /**
     * AuthenticationManager에 Provider를 등록한다.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}
