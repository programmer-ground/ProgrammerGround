package com.pg.pgp.exceptions;

public class FileExtractException extends RuntimeException{
    public FileExtractException() {
        super();
    }

    public FileExtractException(String message) {
        super(message);
    }
}
