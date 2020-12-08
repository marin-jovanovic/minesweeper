package main.settingsWindow;

import java.util.HashMap;

public class SettingsBuffer {

    private static final HashMap<String, String> buffer = new HashMap<>();


    public static void printBufferContent() {
        System.out.println("buffer content");
        buffer.forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println();
    }

    public static void writeToBuffer(String key, String value) {
        buffer.put(key + " =", value);
    }

    public static HashMap<String, String> getBuffer() {
        return buffer;
    }

    public static void main(String[] args) {

        writeToBuffer("row", "1");
        writeToBuffer("col", "1");
        writeToBuffer("min", "53");
        writeToBuffer("row number:", "14");
        writeToBuffer("min", "5");
        writeToBuffer("col", "12");
        writeToBuffer("min", "54");
        writeToBuffer("col", "1");
        writeToBuffer("min", "5");
        writeToBuffer("mine number:", "91203");

        printBufferContent();

//        SettingsManager.saveSettings();

//        printBufferContent();
    }
}
