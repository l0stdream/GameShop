package org.JavaPro.mock;

import org.JavaPro.enums.GameType;
import org.JavaPro.model.Game;
import org.JavaPro.repository.dao.GameRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameRepositoryMock implements GameRepository {
    private List<Game> games;

    public GameRepositoryMock() {

    }

    public GameRepositoryMock(List<Game> games) {
        this.games = games;
    }

    @Override
    public boolean save(Game game) {
        if (games == null) {
            games = new ArrayList<>();
        }
        return games.add(game);
    }

    @Override
    public boolean deleteGameByName(String name) {
        if (games == null) {
            return false;
        }
        return games.removeIf(game -> game.getName().equals(name));
    }

    @Override
    public Optional<Game> getGameByName(String name) {

        return games.stream().anyMatch(game -> game.getName().equals(name))
                ? games.stream().findFirst()
                : Optional.empty();
    }

    @Override
    public List<Game> getGamesFilteredByPrice(double price) {
        return this.games.stream()
                .filter(g -> g.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> getGamesFilteredByType(GameType type) {
        return this.games.stream()
                .filter(g -> g.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> getAllGamesSortedByCreationDate() {
        return this.games.stream()
                .sorted(Comparator.comparing(Game::getCreationDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> getAll() {
        return this.games;
    }
}
