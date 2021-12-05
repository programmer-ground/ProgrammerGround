package com.pg.pgp.exceptions;

public class PlaygroundNotFoundException extends RuntimeException {

  public PlaygroundNotFoundException() {
    super();
  }

  public PlaygroundNotFoundException(String message) {
    super(message);
  }

  public PlaygroundNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
