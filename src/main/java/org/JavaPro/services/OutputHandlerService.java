package org.JavaPro.services;

import java.io.PrintStream;

public class OutputHandlerService {
    private final PrintStream out;
    private final PrintStream err;

    public OutputHandlerService(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }


    public void printMessage(String message) {
        out.println(message);
    }

    public void printMessage(Object message) {
        out.println(message);
    }

    public void printMessage(String format, Object... args) {
        out.printf(format, args);
    }

    public void printErrorMessage(String message) {
        err.println(message);
    }
}
