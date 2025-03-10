package org.JavaPro.util;

import org.JavaPro.model.Game;

import java.time.format.DateTimeFormatter;

public final class GameFormatter {
    public static String formatGame(Game game) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return "======================================" + "\n" +
                "Game ID: " + game.getId() + "\n" +
                "Name: " + game.getName() + "\n" +
                "Release Date: " + (game.getReleaseDate().format(dateFormatter)) + "\n" +
                "Rating: " + game.getRating() + "\n" +
                "Price: " + game.getPrice() + "\n" +
                "Description: " + game.getDescription() + "\n" +
                "Type: " + game.getType().toString() + "\n" +
                "Creation Date: " + (game.getCreationDate().format(dateTimeFormatter)) + "\n" +
                "======================================";
    }
}
