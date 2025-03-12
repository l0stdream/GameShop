package org.JavaPro.services;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class OutputHandlerServiceTest {
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;
    private OutputHandlerService outputHandlerService;


    public void setUp() {
        out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);
        err = new ByteArrayOutputStream();
        PrintStream printErrStream = new PrintStream(err);
        System.setErr(printErrStream);
        outputHandlerService = new OutputHandlerService(System.out, System.err);
    }


    @Test
    public void printMessageStringTest() {
        String input = "Test";
        setUp();
        outputHandlerService.printMessage("Test");
        String result = out.toString().trim();
        assertEquals(input, result);
    }

    @Test
    public void printMessageObjectTest() {
        Object inputObj = 123;
        setUp();
        outputHandlerService.printMessage(inputObj);
        String result = out.toString().trim();
        assertEquals(String.valueOf(inputObj), result);
    }

    @Test
    public void printMessageFormattedTest() {
        String expectedResult = "Test, text!";
        setUp();
        outputHandlerService.printMessage("Test, %s!", "text");
        String result = out.toString().trim();
        assertEquals(expectedResult, result);
    }


    @Test
    public void printErrorMessageTest() {
        String expectedResult = "Test error text";
        setUp();
        outputHandlerService.printErrorMessage("Test error text");
        String result = err.toString().trim();
        assertEquals(expectedResult, result);
    }

}
