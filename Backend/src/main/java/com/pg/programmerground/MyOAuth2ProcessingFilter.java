package com.pg.programmerground;

import com.pg.programmerground.jwt.JwtAuthenticationToken;
import com.pg.programmerground.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT를 인증하는 필터
 * 인증의 가장 첫단이라 생각하면 된다.
 * 필터의 attemptAuthentication()을 통해 인증시 시도 되고 attemptAuthentication() -> AuthenticationManager.authenticate() -> AuthenticationProvider.authenticate() 이 순서대로 인증이 시도 된다.
 */
public class MyOAuth2ProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private JwtTokenProvider jwtTokenProvider;
    public MyOAuth2ProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, JwtTokenProvider jwtTokenProvider) {
        super(requiresAuthenticationRequestMatcher);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //토큰을 인증되지 않은 Authentication 객체로 만들고 AuthenticationManager.authenticate()실행.
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(jwtTokenProvider.resolveToken(request)));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
