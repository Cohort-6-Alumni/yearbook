package com.obsidi.yearbook.exception.domain;

public class ProfileNotUserException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ProfileNotUserException(String message) {
    super(message);
  }
}
