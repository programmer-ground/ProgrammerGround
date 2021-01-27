package com.pg.programmerground.auth.jwt;

import com.pg.programmerground.exception.JwtExpiredException;
import com.pg.programmerground.exception.OAuthMemberNotFoundException;
import com.pg.programmerground.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 인증 처리 로직 담은 Provider
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    /**
     * 실질적인 인증 로직 처리
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String jwtToken = (String) authentication.getCredentials();
            Long OAuthId = jwtTokenProvider.getOAuthId(jwtToken);
            UserDetails userDetails = userService.loadUserByOAuthId(OAuthId);
            return new JwtAuthenticationToken(userDetails, jwtToken, userDetails.getAuthorities());
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("토큰 만료");
        } catch (OAuthMemberNotFoundException OAuthMemberNotFoundException) {
            throw OAuthMemberNotFoundException;
        } catch (Exception e) {
            throw new BadCredentialsException("토큰 불량");
        }
    }

    /**
     Spring Security에서 AuthenticationFilter는 인증을 담당하는데
     이 Filter는 AuthenticationManager를 가지고 있고
     AuthenticationManager는 여러개의 AuthenticationProvider를 통해 인증 로직을 실행한다.
     여러 인증에서 어떤 Provider를 사용할지는 Authentication객체에 따라 달라진다.
     밑에 어떤 Authentication객체가 사용될 때 이 Provider를 사용할지 결정하는 함수이다.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
