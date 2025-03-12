package org.JavaPro.services;

import org.JavaPro.enums.GameType;
import org.JavaPro.enums.Messages;
import org.JavaPro.model.Game;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CreateGameInputServiceTest {
    private ByteArrayOutputStream out;
    CreateGameInputService createGameInputService;

    public void setUp(String textForIn) {
        ByteArrayInputStream in = new ByteArrayInputStream(textForIn.getBytes());
        System.setIn(in);
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Scanner scanner = new Scanner(System.in);
        OutputHandlerService outputHandlerService = new OutputHandlerService(System.out, System.err);
        InputHandlerService inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
        createGameInputService = new CreateGameInputService(inputHandlerService, outputHandlerService);
    }

    @Test
    public void createGameTest() {
        String input = "name\n1111-11-11\n1\n11\ndescription\nRPG";

        setUp(input);

        double delta = 0.0001;
        Game game = createGameInputService.createGame();
        assertEquals("name", game.getName());
        assertEquals(LocalDate.of(1111, 11, 11), game.getReleaseDate());
        assertEquals(1, game.getRating());
        assertEquals(11, game.getPrice(), delta);
        assertEquals("description", game.getDescription());
        assertEquals(GameType.RPG.name(), game.getType().toString());
    }

    @Test
    public void createGameConsoleInteractionAndOutputTest() {
        String input = "\nname\ninvalid-date\n2023-10-27\ninvalidRating\n1\nprice\n11\n \ndescription\ntext\nRPG";

        setUp(input);
        createGameInputService.createGame();
        String result = out.toString();
        String expectedOutput = Messages.ENTER_GAME_NAME.getMessage() + System.lineSeparator() +
                Messages.INPUT_BLANK.getMessage() + System.lineSeparator() +
                Messages.INPUT_DATE.getMessage() + System.lineSeparator() +
                Messages.INPUT_DATE.getMessage() + System.lineSeparator() +
                Messages.ADD_GAME_RATING.getMessage() + System.lineSeparator() +
                Messages.INPUT_SHORT_POSITIVE_NUMBERS_ONLY.getMessage() + System.lineSeparator() +
                Messages.ENTER_GAME_PRICE.getMessage() + System.lineSeparator() +
                Messages.INPUT_POSITIVE_NUMBERS_ONLY.getMessage() + System.lineSeparator() +
                Messages.ADD_GAME_DESCRIPTION.getMessage() + System.lineSeparator() +
                Messages.INPUT_BLANK.getMessage() + System.lineSeparator() +
                Messages.INPUT_GAME_TYPE.getMessage() +
                " " + Arrays.toString(GameType.class.getEnumConstants()) + System.lineSeparator() +
                Messages.INPUT_GAME_TYPE.getMessage() +
                " " + Arrays.toString(GameType.class.getEnumConstants()) + System.lineSeparator();
        assertEquals(expectedOutput, result);
    }
}
