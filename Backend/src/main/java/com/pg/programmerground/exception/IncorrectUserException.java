package com.pg.programmerground.exception;

public class IncorrectUserException extends RuntimeException {
    public IncorrectUserException() {
        super();
    }

    public IncorrectUserException(String message) {
        super(message);
    }
}
