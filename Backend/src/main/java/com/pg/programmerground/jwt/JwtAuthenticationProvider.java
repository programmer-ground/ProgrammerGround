package com.pg.programmerground.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 인증 처리 로직 담은 Provider
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwtToken = (String) authentication.getCredentials();
        //굳이 체크할 이유가 있나 getOAuthId를 통해 expire를 exception 해줄텐데
        Long OAuthId = jwtTokenProvider.getOAuthId(jwtToken);

        //DB에서 데이터를 가져와서 비교해야함 -> 굳이? JWT가 애초에 stateless
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
