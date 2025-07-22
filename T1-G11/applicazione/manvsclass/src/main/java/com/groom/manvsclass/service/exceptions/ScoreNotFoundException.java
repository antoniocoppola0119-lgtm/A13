package com.groom.manvsclass.service.exceptions;

public class ScoreNotFoundException extends RuntimeException {
  private final String field;

  public ScoreNotFoundException(String field) {
    this.field = field;
  }

  public ScoreNotFoundException() {
    this.field = "none";
  }
}
