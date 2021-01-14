package com.pg.auth.exception;

/**
 * Github OAuth 로그인에서 생기는 예외
 */
public class OAuthLoginException extends Exception {
    public OAuthLoginException(String message) {
        super(message);
    }
}
