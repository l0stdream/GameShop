package org.JavaPro.exception.sql;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String gameName, Throwable cause) {
        super("Game with name '" + gameName + "' wasn't found.", cause);
    }
}
