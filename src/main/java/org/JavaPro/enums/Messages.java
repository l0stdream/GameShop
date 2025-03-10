package org.JavaPro.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Messages {
    INPUT_SHOWN_COMMANDS_ONLY("Please choose one of shown variants and type the command."),
    MAIN_TEXT("Type one of command below without single quotes: \n" +
            "'show game by name' - for showing some specific game by its name;\n" +
            "'show all' - for showing all games in the shop;\n" +
            "'add game' - for adding the game to the shop;\n" +
            "'delete game' - for deleting specific game by its name;\n" +
            "'show games by price' - for showing some specific game by their name;\n" +
            "'show games by type' - for showing some specific game by their type;\n" +
            "'show games by added date' - for showing some specific game by their added to shop date;\n" +
            "'exit' - for exit."),
    EXIT("Thank you for visiting us!"),
    HI("Hi and welcome to GameShop v0.01!"),
    INPUT_POSITIVE_NUMBERS_ONLY("Please enter positive numbers only."),
    ENTER_GAME_NAME("Enter game's name:"),
    ADD_GAME_RELEASE_DATE("Enter game's release date in format like yyyy-MM-dd:"),
    ADD_GAME_RATING("Enter game's rating:"),
    ENTER_GAME_PRICE("Enter game's price:"),
    ADD_GAME_DESCRIPTION("Enter game's description:"),
    INPUT_GAME_TYPE("Please enter one of shown type:"),
    INPUT_DATE("Please enter date only in format like - yyyy-MM-dd:"),
    INPUT_SHORT_POSITIVE_NUMBERS_ONLY("Please enter positive integers between 0 and 32767 only."),
    PROCESS_WAS_SUCCESS("Process was success."),
    GAME_WASNT_ADDED("Game wasn't added."),
    GAME_NOT_FOUND("Game with that name wasn't found."),
    INPUT_BLANK("Please write something.");


    private final String message;

    Messages(String message) {
        this.message = message;
    }

}
