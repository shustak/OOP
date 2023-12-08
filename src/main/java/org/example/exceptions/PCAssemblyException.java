package org.example.exceptions;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class PCAssemblyException extends Exception {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public PCAssemblyException(String message, Object o) {
        super(String.format("Assembly Error: %s\tfrom Object: %s", message, o.getClass()));
    }

    public void printMessage() {
        System.out.println(ANSI_RED + this.getMessage() + ANSI_RESET);
    }

    public void logException() {
        Logger logger = Logger.getLogger("PCAssemblyLog");
        FileHandler fh;

        try {
            fh = new FileHandler("PCAssemblyLogFile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info(this.getMessage());
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
