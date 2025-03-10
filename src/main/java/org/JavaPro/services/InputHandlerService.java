package org.JavaPro.services;

import org.JavaPro.enums.GameType;
import org.JavaPro.enums.Messages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class InputHandlerService {
    private final Scanner scanner;
    private final OutputHandlerService outputHandlerService;

    public InputHandlerService(Scanner scanner, OutputHandlerService outputHandlerService) {
        this.scanner = scanner;
        this.outputHandlerService = outputHandlerService;
    }

    public String readNextString() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            outputHandlerService.printMessage(Messages.INPUT_BLANK.getMessage());
        }
    }

    public short readPositiveShort() {
        String inputText;
        while (true) {
            inputText = scanner.nextLine().trim();
            try {
                if (Short.parseShort(inputText) > 0)
                    return Short.parseShort(inputText);
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                outputHandlerService.printMessage(Messages.INPUT_SHORT_POSITIVE_NUMBERS_ONLY.getMessage());
            }
        }
    }

    public double readPositiveDouble() {
        String inputText;
        while (true) {
            inputText = scanner.nextLine().trim();
            try {
                if (Double.parseDouble(inputText) > 0)
                    return Double.parseDouble(inputText);
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                outputHandlerService.printMessage(Messages.INPUT_POSITIVE_NUMBERS_ONLY.getMessage());
            }
        }
    }

    public GameType readGameType() {
        while (true) {
            String inputText = scanner.nextLine().trim().toUpperCase();
            try {
                return Enum.valueOf(GameType.class, inputText);
            } catch (IllegalArgumentException e) {
                outputHandlerService.printMessage(Messages.INPUT_GAME_TYPE.getMessage()
                        + " " + Arrays.toString(GameType.class.getEnumConstants()));
            }
        }
    }

    public LocalDate readLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                outputHandlerService.printMessage(Messages.INPUT_DATE.getMessage());
            }
        }
    }
}
