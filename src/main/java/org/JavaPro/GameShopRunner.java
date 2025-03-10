package org.JavaPro;

import org.JavaPro.enums.Messages;
import org.JavaPro.repository.GameRepositoryImpl;
import org.JavaPro.services.*;
import org.JavaPro.util.ConnectionManager;

import java.util.Map;
import java.util.Scanner;


public class GameShopRunner {
    public static void main(String[] args) {

        try (var connection = ConnectionManager.open();
             var scanner = new Scanner(System.in);
        ) {
            var outputHandlerService = new OutputHandlerService(System.out, System.err);
            var inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
            var gameRepository = new GameRepositoryImpl(connection);
            var gameShopService = new GameShopServiceImpl(gameRepository);
            Map<String, Executor> orchestrator = init(inputHandlerService, outputHandlerService, gameShopService);

            String choice;
            outputHandlerService.printMessage(Messages.HI.getMessage());
            do {
                outputHandlerService.printMessage(Messages.MAIN_TEXT.getMessage());
                choice = inputHandlerService.readNextString();
                orchestrator
                        .getOrDefault(choice, () -> outputHandlerService.printMessage(Messages.INPUT_SHOWN_COMMANDS_ONLY.getMessage()))
                        .execute();

            } while (!choice.equals("exit"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static Map<String, Executor> init(InputHandlerService inputHandlerService, OutputHandlerService outputHandlerService, GameShopService gameShopService) {
        var gameShopMenuService = new GameShopMenuService(inputHandlerService, outputHandlerService, gameShopService);
        return Map.of("show game by name", gameShopMenuService.getGameByName(),
                "show all", gameShopMenuService.getAllGames(),
                "add game", gameShopMenuService.addGame(),
                "delete game", gameShopMenuService.deleteGame(),
                "show games by price", gameShopMenuService.getGamesByPrice(),
                "show games by type",gameShopMenuService.getGamesByType(),
                "show games by added date", gameShopMenuService.getGamesSortedByAddedDate(),
                "exit", gameShopMenuService.exit());
    }
}
