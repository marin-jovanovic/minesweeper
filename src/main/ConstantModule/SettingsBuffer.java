package main.ConstantModule;

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

}
