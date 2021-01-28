package com.pg.programmerground.auth.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Jwt를 Authentication객체로 만든다.
 * processingFilter에서 AuthenticationMangager로 인증이 되지 않은 Authentication 객체로 만들어 보내고
 * 이 Authentication을 이용해 인증(JWT유효성, 만료)확인후 인증 된 Authentication객체로 다시 만들어 반환한다.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private Object credential;
    private Object principal;

    /**
     * 인증되지 않은 Authentication 만드는 생성자
     */
    public JwtAuthenticationToken(String token) {
        super(null);
        this.credential = token;
        this.setAuthenticated(false);
    }

    /**
     * 인증된 Authentication객체를 만드는 생성자
     * 인증 완료후 생성
     */
    public JwtAuthenticationToken(Object principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credential = token;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credential;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
