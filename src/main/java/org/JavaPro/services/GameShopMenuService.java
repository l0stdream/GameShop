package org.JavaPro.services;

import org.JavaPro.Executor;
import org.JavaPro.enums.GameType;
import org.JavaPro.enums.Messages;
import org.JavaPro.exception.sql.GameExtractionException;
import org.JavaPro.exception.sql.GameFilteringException;
import org.JavaPro.exception.sql.GameNotFoundException;
import org.JavaPro.exception.sql.GameSaveException;
import org.JavaPro.model.Game;
import org.JavaPro.util.GameFormatter;

import java.util.Arrays;
import java.util.Optional;


public class GameShopMenuService {
    private final InputHandlerService inputHandlerService;
    private final OutputHandlerService outputHandlerService;
    private final GameShopService gameShopService;
    private final CreateGameInputService createGameInputService;

    public GameShopMenuService(InputHandlerService inputHandlerService, OutputHandlerService outputHandlerService, GameShopService gameShopService) {
        this.inputHandlerService = inputHandlerService;
        this.outputHandlerService = outputHandlerService;
        this.gameShopService = gameShopService;
        this.createGameInputService = new CreateGameInputService(inputHandlerService, outputHandlerService);
    }


    public Executor getAllGames() {
        return () -> {
            try {
                gameShopService.getAllGames()
                        .forEach(game -> outputHandlerService.printMessage(
                                GameFormatter.formatGame(game)));
            } catch (GameExtractionException e) {
                outputHandlerService.printErrorMessage(e.getMessage());
            }
        };
    }

    public Executor getGameByName() {
        return () -> {
            outputHandlerService.printMessage(Messages.ENTER_GAME_NAME.getMessage());
            String gameName = inputHandlerService.readNextString();
            try {
                Optional<Game> game = gameShopService.getGameByName(gameName);
                if (game.isPresent()) {
                    outputHandlerService.printMessage(GameFormatter.formatGame(game.get()));
                } else {
                    outputHandlerService.printMessage(Messages.GAME_NOT_FOUND.getMessage());
                }
            } catch (GameNotFoundException e) {
                outputHandlerService.printErrorMessage(e.getMessage());
            }
        };
    }

    public Executor getGamesByPrice() {
        return () -> {
            outputHandlerService.printMessage(Messages.ENTER_GAME_PRICE.getMessage());
            try {
                gameShopService
                        .getGamesFilteredByPrice((inputHandlerService.readPositiveDouble()))
                        .forEach(game -> outputHandlerService.printMessage(
                                GameFormatter.formatGame(game)));
            } catch (GameFilteringException e) {
                outputHandlerService.printErrorMessage(e.getMessage());
            }
        };
    }

    public Executor addGame() {
        return () -> {
            try {
                if (gameShopService.addGame(createGameInputService.createGame())) {
                    outputHandlerService.printMessage(Messages.PROCESS_WAS_SUCCESS.getMessage());
                } else {
                    outputHandlerService.printMessage(Messages.GAME_WASNT_ADDED.getMessage());
                }
            } catch (GameSaveException e) {
                outputHandlerService.printErrorMessage(e.getMessage());
            }
        };
    }

    public Executor getGamesByType() {
        return () -> {
            outputHandlerService.printMessage(Messages.INPUT_GAME_TYPE.getMessage() +
                    " " + Arrays.toString(GameType.class.getEnumConstants()));

            try {
                gameShopService
                        .getGamesFilteredByType
                                (inputHandlerService.readGameType())
                        .forEach(game -> outputHandlerService.printMessage(
                                GameFormatter.formatGame(game)));
            } catch (GameFilteringException e) {
                outputHandlerService.printErrorMessage(e.getMessage());
            }
        };
    }

    public Executor getGamesSortedByAddedDate() {
        return () -> {
            try {
                gameShopService.getGamesSortedByCreationDate()
                        .forEach(game -> outputHandlerService.printMessage(
                                GameFormatter.formatGame(game)));
            } catch (GameExtractionException e) {
                outputHandlerService.printErrorMessage(e.getMessage());
            }
        };
    }

    public Executor deleteGame() {
        return () -> {
            outputHandlerService.printMessage(Messages.ENTER_GAME_NAME.getMessage());
            String gameName = inputHandlerService.readNextString();
            try {
                if (gameShopService.deleteGameByName(gameName)) {
                    outputHandlerService.printMessage(Messages.PROCESS_WAS_SUCCESS.getMessage());
                } else {
                    outputHandlerService.printMessage(Messages.GAME_NOT_FOUND.getMessage());
                }
            } catch (GameNotFoundException e) {
                outputHandlerService.printErrorMessage(e.getMessage());
            }
        };
    }

    public Executor exit() {
        return () -> {
            outputHandlerService.printMessage(Messages.EXIT.getMessage());
            System.exit(1);
        };
    }
}
