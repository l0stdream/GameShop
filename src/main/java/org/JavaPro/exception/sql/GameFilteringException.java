package org.JavaPro.exception.sql;

public class GameFilteringException extends RuntimeException {
  public GameFilteringException(String filter, Throwable cause) {
    super("Error filtering games by " + filter, cause);
  }
}
