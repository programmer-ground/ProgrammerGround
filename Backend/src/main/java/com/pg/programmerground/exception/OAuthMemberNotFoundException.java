package com.pg.programmerground.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuthMemberNotFoundException extends AuthenticationException {
    public OAuthMemberNotFoundException(String msg) {
        super(msg);
    }
}
