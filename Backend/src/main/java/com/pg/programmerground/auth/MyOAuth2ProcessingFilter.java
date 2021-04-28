package com.pg.programmerground.auth;

import com.pg.programmerground.exception.JwtExpiredException;
import com.pg.programmerground.exception.JwtNotFoundException;
import com.pg.programmerground.exception.OAuthUserNotFoundException;
import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import com.pg.programmerground.auth.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
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
@Slf4j
public class MyOAuth2ProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public MyOAuth2ProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, JwtTokenProvider jwtTokenProvider) {
        super(requiresAuthenticationRequestMatcher);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //토큰을 인증되지 않은 Authentication 객체로 만들고 AuthenticationManager.authenticate()실행.
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(jwtTokenProvider.resolveToken(request)));
    }

    /**
     * 인증 성공시 처리
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    /**
     * 인증 실패시 처리
     * AuthenticationException(JwtExpiredException, BadCredentialsException등)의 Exception을 처리
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        //UNAUTHORIZED면 로그인 화면 으로 이동하도록 프론트 설계
        //401 Unauthorized
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        if (failed instanceof JwtExpiredException) {
            response.getWriter().println("Expired Jwt Token");
        } else if (failed instanceof BadCredentialsException) {
            response.getWriter().println("Invalid JWT Token");
        } else if (failed instanceof JwtNotFoundException) {
            response.getWriter().println("Not Found JWT Token");
        } else if (failed instanceof OAuthUserNotFoundException) {
            response.getWriter().println("Not Found OAuth User");
        }
    }
}
