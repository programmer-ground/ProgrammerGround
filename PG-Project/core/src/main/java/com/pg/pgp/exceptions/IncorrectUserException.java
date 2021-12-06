package com.pg.pgp.exceptions;

public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException() {
        super();
    }

    public IncorrectUserException(String message) {
        super(message);
    }
}
