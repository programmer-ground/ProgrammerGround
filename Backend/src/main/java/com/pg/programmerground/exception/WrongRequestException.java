package com.pg.programmerground.exception;

public class WrongRequestException extends RuntimeException{

    public WrongRequestException() {
        super();
    }

    public WrongRequestException(String message) {
        super(message);
    }
}
