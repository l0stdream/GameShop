package org.JavaPro.services;

import org.JavaPro.enums.GameType;
import org.JavaPro.model.Game;
import org.JavaPro.repository.dao.GameRepository;

import java.util.List;
import java.util.Optional;

public class GameShopServiceImpl implements GameShopService {
    private final GameRepository gameRepository;

    public GameShopServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public boolean addGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Optional<Game> getGameByName(String name) {
        return gameRepository.getGameByName(name);
    }

    @Override
    public boolean deleteGameByName(String name) {
        return gameRepository.deleteGameByName(name);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.getAll();
    }

    @Override
    public List<Game> getGamesFilteredByPrice(double price) {
        return gameRepository.getGamesFilteredByPrice(price);
    }

    @Override
    public List<Game> getGamesFilteredByType(GameType type) {
        return gameRepository.getGamesFilteredByType(type);
    }

    @Override
    public List<Game> getGamesSortedByCreationDate() {
        return gameRepository.getAllGamesSortedByCreationDate();
    }
}
