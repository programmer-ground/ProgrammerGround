package com.pg.programmerground.exception;

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
