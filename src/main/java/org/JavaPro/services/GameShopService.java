package org.JavaPro.services;

import org.JavaPro.enums.GameType;
import org.JavaPro.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameShopService {
    boolean addGame(Game game);
    boolean deleteGameByName(String name);
    Optional<Game> getGameByName(String name);
    List<Game> getGamesFilteredByPrice(double price);
    List<Game> getGamesFilteredByType(GameType type);
    List<Game> getGamesSortedByCreationDate();
    List<Game> getAllGames();
}