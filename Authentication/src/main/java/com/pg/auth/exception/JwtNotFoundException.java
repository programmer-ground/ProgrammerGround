package com.pg.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtNotFoundException extends AuthenticationException {
    public JwtNotFoundException(String msg) {
        super(msg);
    }
}
