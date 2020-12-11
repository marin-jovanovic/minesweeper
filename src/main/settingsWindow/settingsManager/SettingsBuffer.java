package main.settingsWindow.settingsManager;

import java.util.HashMap;

public class SettingsBuffer {

    private static final HashMap<String, String> buffer = new HashMap<>();


    static void printBufferContent() {
        System.out.println("buffer content");
        buffer.forEach((key, value) -> System.out.println(key + " -> " + value));
        System.out.println();
    }

    static void writeToBuffer(String key, String value) {
        System.out.println("writing to buffer " + key + " =" + value);
        buffer.put(key, value);
        System.out.println(buffer);
    }

    static HashMap<String, String> getBuffer() {
        return buffer;
    }


//    public static void main(String[] args) {
//
//        SettingsBuffer.writeToBuffer("number of columns", "12");
//        SettingsBuffer.writeToBuffer("number of rows", "32");
//        SettingsBuffer.writeToBuffer("number of mines", "12");
//        SettingsBuffer.writeToBuffer("number of columns", "3");
//
//
//
//
//        printBufferContent();
//
//        System.out.println("getter");
//
//        HashMap temp = getBuffer();
//
//        System.out.println(temp);
//
////        SettingsManager.saveSettings();
//
////        printBufferContent();
//    }
}
