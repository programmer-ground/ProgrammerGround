package com.pg.programmerground.config;

import com.pg.programmerground.MyOAuth2ProcessingFilter;
import com.pg.programmerground.jwt.JwtAuthenticationProvider;
import com.pg.programmerground.jwt.JwtTokenProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pg.programmerground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;


@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
    JwtTokenProvider jwtTokenProvider;
    JwtAuthenticationProvider jwtAuthenticationProvider;

    public Oauth2WebSecurityConfig(JwtTokenProvider jwtTokenProvider, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated();
        //UsernamePasswordAuthenticationFilter를 거치기 전에 Custom필터를 거친다.
        http.addFilterBefore(buildProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private MyOAuth2ProcessingFilter buildProcessingFilter() throws Exception {
        return new MyOAuth2ProcessingFilter(null, jwtTokenProvider);
    }

    //AuthenticationManager는 인증을 담당하는데 인증 로직을 담은 AuthenticationProvider를 가지고 있다.
    //
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}
