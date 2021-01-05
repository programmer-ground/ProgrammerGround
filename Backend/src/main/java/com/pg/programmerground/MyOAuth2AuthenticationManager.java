package com.pg.programmerground;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;

/**
 * 토큰 인증 처리
 *
 */
public class MyOAuth2AuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //authorizationExchange -> request, response 들어감
        OAuth2LoginAuthenticationToken token = new OAuth2LoginAuthenticationToken(null, null);
        //default에서는 AuthenticationManager에서 OAuth2LoginAuthentcationProvider를 호출해서 인증 -> Provider 만들필요 없이 바로 여기서 인증 구현
        //OAuth2LoginAuthentcationProvider에서는
        return null;


        /* 예시
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getPrincipal());
        // 각종 처리를 구현
        // 비번이 일치하는지
        // 아이디로 회원을 조회 했을 때 존재하는 회원인지
        // 기타 등등과 적절한 예외 처리
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername()
                , userDetails.getPassword()
                , userDetails.getAuthorities()))
    }
         */
    }
}
