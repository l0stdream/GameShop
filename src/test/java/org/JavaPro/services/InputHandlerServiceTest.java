package org.JavaPro.services;

import org.JavaPro.enums.GameType;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class InputHandlerServiceTest {
    private InputHandlerService inputHandlerService;


    public void setUp(String textForIn) {
        ByteArrayInputStream in = new ByteArrayInputStream(textForIn.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        OutputHandlerService outputHandlerService = new OutputHandlerService(System.out, System.err);
        inputHandlerService = new InputHandlerService(scanner, outputHandlerService);
    }

    @Test
    public void readNextStringTest() {
        String input = "text\n";
        setUp(input);
        String result = inputHandlerService.readNextString();
        String expectedOutput = "text";
        assertEquals(expectedOutput, result);
    }

    @Test
    public void readPositiveShortValidTest() {
        String input = "5\n";

        setUp(input);

        int result = inputHandlerService.readPositiveShort();
        int expectedResult = 5;
        assertEquals(expectedResult, result);
    }

    @Test
    public void readPositiveShortInvalidThenValidTest() {
        String input = "-5\n2";

        setUp(input);

        short result = inputHandlerService.readPositiveShort();
        short expectedResult = 2;

        assertEquals(expectedResult, result);
    }

    @Test
    public void readPositiveDoubleValidTTest() {
        String input = "2\n";

        setUp(input);

        double result = inputHandlerService.readPositiveDouble();
        double expectedResult = 2;
        double delta = 0.0001;
        assertEquals(expectedResult, result, delta);
    }

    @Test
    public void readPositiveDoubleInvalidThenValidTest() {
        String input = "-5\ntext\n2";

        setUp(input);


        double result = inputHandlerService.readPositiveDouble();
        double expectedResult = 2;
        double delta = 0.0001;
        assertEquals(expectedResult, result, delta);

    }

    @Test
    public void readGameTypeValidTest() {
        String input = "rpg\n";
        setUp(input);

        GameType result = inputHandlerService.readGameType();
        GameType expectedResult = GameType.RPG;
        assertEquals(expectedResult, result);
    }

    @Test
    public void readGameTypeInvalidThenValidTest() {
        String input = "text\nrpg\n";
        setUp(input);

        GameType result = inputHandlerService.readGameType();
        GameType expectedResult = GameType.RPG;

        assertEquals(expectedResult, result);
    }

    @Test
    public void readLocalDateValidTest() {
        String input = "1111-11-11\n";
        setUp(input);

        LocalDate result = inputHandlerService.readLocalDate();
        LocalDate expectedResult = LocalDate.of(1111, 11, 11);
        assertEquals(expectedResult, result);
    }

    @Test
    public void readLocalDateInvalidThenValidTest() {
        String input = "text\n111-11-11\n1111-11-11\n";
        setUp(input);

        LocalDate result = inputHandlerService.readLocalDate();
        LocalDate expectedResult = LocalDate.of(1111, 11, 11);
        assertEquals(expectedResult, result);
    }
}
