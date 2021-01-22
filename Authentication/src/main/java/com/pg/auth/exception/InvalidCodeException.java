package com.pg.auth.exception;

public class InvalidCodeException extends Exception{
    public InvalidCodeException(String message) {
        super(message);
    }
}
