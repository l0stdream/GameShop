package org.JavaPro.services;

import org.JavaPro.enums.GameType;
import org.JavaPro.enums.Messages;
import org.JavaPro.model.Game;

import java.util.Arrays;

public class CreateGameInputService {
    private final InputHandlerService inputHandlerService;
    private final OutputHandlerService outputHandlerService;

    public CreateGameInputService(InputHandlerService inputHandlerService, OutputHandlerService outputHandlerService) {
        this.inputHandlerService = inputHandlerService;
        this.outputHandlerService = outputHandlerService;
    }

    public Game createGame() {
        Game.GameBuilder builder = Game.builder();

        outputHandlerService.printMessage(Messages.ENTER_GAME_NAME.getMessage());
        builder.name(inputHandlerService.readNextString());

        outputHandlerService.printMessage(Messages.INPUT_DATE.getMessage());
        builder.releaseDate(inputHandlerService.readLocalDate());

        outputHandlerService.printMessage(Messages.ADD_GAME_RATING.getMessage());
        builder.rating(inputHandlerService.readPositiveShort());

        outputHandlerService.printMessage(Messages.ENTER_GAME_PRICE.getMessage());
        builder.price(inputHandlerService.readPositiveDouble());

        outputHandlerService.printMessage(Messages.ADD_GAME_DESCRIPTION.getMessage());
        builder.description(inputHandlerService.readNextString());

        outputHandlerService.printMessage(Messages.INPUT_GAME_TYPE.getMessage() +
                " " + Arrays.toString(GameType.class.getEnumConstants()));
        builder.type(inputHandlerService.readGameType());
        return builder.build();
    }
}
