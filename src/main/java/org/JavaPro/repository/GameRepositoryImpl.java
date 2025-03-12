package org.JavaPro.repository;

import static org.JavaPro.repository.sql.GamePostgreSQL.*;

import org.JavaPro.enums.GameType;
import org.JavaPro.exception.sql.GameExtractionException;
import org.JavaPro.exception.sql.GameFilteringException;
import org.JavaPro.exception.sql.GameNotFoundException;
import org.JavaPro.exception.sql.GameSaveException;
import org.JavaPro.model.Game;
import org.JavaPro.repository.dao.GameRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {
    private final Connection connection;


    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private List<Game> executeQueryAndMapToList(PreparedStatement preparedStatement) throws SQLException {
        List<Game> result = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.add(mapResultSetToGame(resultSet));
            }
        }
        return result;
    }

    private Game mapResultSetToGame(ResultSet resultSet) throws SQLException {
        return Game.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .releaseDate(resultSet.getObject("release_date", LocalDate.class))
                .rating(resultSet.getShort("rating"))
                .price(resultSet.getDouble("price"))
                .description(resultSet.getString("description"))
                .type(GameType.valueOf(resultSet.getString("type")))
                .creationDate(resultSet.getObject("creation_date", LocalDateTime.class))
                .build();
    }

    @Override
    public boolean save(Game game) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setString(1, game.getName());
            preparedStatement.setObject(2, game.getReleaseDate());
            preparedStatement.setShort(3, game.getRating());
            preparedStatement.setDouble(4, game.getPrice());
            preparedStatement.setString(5, game.getDescription());
            preparedStatement.setString(6, game.getType().toString().toUpperCase());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new GameSaveException(game, e);
        }
    }

    @Override
    public Optional<Game> getGameByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToGame(resultSet));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new GameNotFoundException(name, e);
        }
    }

    @Override
    public boolean deleteGameByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME)) {
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new GameNotFoundException(name, e);
        }
    }

    @Override
    public List<Game> getAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            return executeQueryAndMapToList(preparedStatement);
        } catch (SQLException e) {
            throw new GameExtractionException(e);
        }
    }

    @Override
    public List<Game> getGamesFilteredByPrice(double price) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FILTERED_BY_PRICE)) {
            preparedStatement.setDouble(1, price);
            return executeQueryAndMapToList(preparedStatement);
        } catch (SQLException e) {
            throw new GameFilteringException("price", e);
        }

    }

    @Override
    public List<Game> getGamesFilteredByType(GameType type) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FILTERED_BY_TYPE)) {
            preparedStatement.setString(1, type.toString());
            return executeQueryAndMapToList(preparedStatement);
        } catch (SQLException e) {
            throw new GameFilteringException("type", e);
        }
    }

    @Override
    public List<Game> getAllGamesSortedByCreationDate() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SORTED_BY_CREATION_DATE);) {
            return executeQueryAndMapToList(preparedStatement);
        } catch (SQLException e) {
            throw new GameExtractionException(e);
        }
    }
}
