package com.pg.pgp.exceptions;

import org.springframework.security.core.AuthenticationException;

public class OAuthUserNotFoundException extends AuthenticationException {
    public OAuthUserNotFoundException(String msg) {
        super(msg);
    }
}
