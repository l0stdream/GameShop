package org.JavaPro.exception.sql;

import org.JavaPro.model.Game;

public class GameSaveException extends RuntimeException {
    public GameSaveException(Game game, Throwable cause) {
        super("Error saving game: " + game.getName(), cause);
    }
}
