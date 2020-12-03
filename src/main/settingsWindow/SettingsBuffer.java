package main.settingsWindow;

import java.util.HashMap;

public class SettingsBuffer {

    private static final HashMap<String, String> allLines = new HashMap<>();

    public static void printBufferContent() {
        allLines.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public static void writeToBuffer(String key, String value) {
        allLines.put(key, value);
    }

/**
 * settings.txt
 *
 * num of mines = 10
 * num of rows = 5
 * num of columns = 5
 * */

    public static void main(String[] args) {

        writeToBuffer("row", "1");
        writeToBuffer("col", "1");
        writeToBuffer("min", "53");
        writeToBuffer("row", "1");
        writeToBuffer("col", "14");
        writeToBuffer("min", "5");
        writeToBuffer("col", "12");
        writeToBuffer("min", "54");
        writeToBuffer("col", "1");
        writeToBuffer("min", "5");

        printBufferContent();
    }
}
