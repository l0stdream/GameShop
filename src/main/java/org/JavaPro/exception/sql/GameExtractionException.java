package org.JavaPro.exception.sql;

public class GameExtractionException extends RuntimeException {
    public GameExtractionException(Throwable cause) {
        super("Error with getting games", cause);
    }
}
