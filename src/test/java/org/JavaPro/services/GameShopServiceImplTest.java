package org.JavaPro.services;

import org.JavaPro.enums.GameType;
import org.JavaPro.mock.GameRepositoryMock;
import org.JavaPro.model.Game;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class GameShopServiceImplTest {
    GameRepositoryMock gameRepositoryMock;
    private GameShopService gameShopService;
    private List<Game> games;

    @Before
    public void setUp() {

        this.games = new ArrayList<>(List.of(Game.builder()
                        .id(1)
                        .name("game")
                        .releaseDate(LocalDate.of(1111, 11, 11))
                        .rating((short) 1)
                        .price(11)
                        .description("description")
                        .type(GameType.RPG)
                        .creationDate(LocalDateTime.now())
                        .build(),
                Game.builder()
                        .id(1)
                        .name("game2")
                        .releaseDate(LocalDate.of(2222, 11, 22))
                        .rating((short) 2)
                        .price(22)
                        .description("description2")
                        .type(GameType.RACING)
                        .creationDate(LocalDateTime.of(2222, 12, 22, 22, 22, 22))
                        .build()));
         gameRepositoryMock = new GameRepositoryMock(games);
        gameShopService = new GameShopServiceImpl(gameRepositoryMock);
    }

    @Test
    public void addGameTest() {
        Game game = Game.builder()
                .id(1)
                .name("game")
                .releaseDate(LocalDate.of(1111, 11, 11))
                .rating((short) 1)
                .price(11)
                .description("description")
                .type(GameType.RPG)
                .creationDate(LocalDateTime.now())
                .build();



        assertTrue(gameShopService.addGame(game));
        assertTrue(gameRepositoryMock.getAll().contains(game));
    }

    @Test
    public void getGameByNameTest() {

        Optional<Game> result = gameShopService.getGameByName("game");
        String expectedResult = "game";
        assertNotNull(result);
        assertEquals(expectedResult, result.orElseThrow().getName());
    }

    @Test
    public void getGameByNameNoGameTest() {

        Optional<Game> result = gameShopService.getGameByName("noGame");

        assertFalse(result.isPresent());

    }

    @Test
    public void getGamesFilteredByPriceTest() {
        List<Game> result = gameShopService.getGamesFilteredByPrice(22);

        String expectedGameName = "game2";

        assertEquals(1, result.size());
        assertEquals(expectedGameName, result.getFirst().getName());
    }

    @Test
    public void getGamesFilteredByPriceNoGamesTest() {
        List<Game> result = gameShopService.getGamesFilteredByPrice(100);

        assertEquals(0, result.size());
    }

    @Test
    public void deleteGameByNameTest() {
        String gameName = "game";

        boolean result = gameShopService.deleteGameByName(gameName);

        assertTrue(result);
        assertFalse(gameRepositoryMock.getAll().stream().anyMatch(game -> game.getName().equals(gameName)));
    }

    @Test
    public void deleteGameByNameNoGameTest() {
        String gameName = "text";

        boolean result = gameShopService.deleteGameByName(gameName);

        assertFalse(result);
    }
}
