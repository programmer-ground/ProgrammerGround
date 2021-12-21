package com.pg.pgp.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtExpiredException extends AuthenticationException {
    public JwtExpiredException(String msg) {
        super(msg);
    }
}
