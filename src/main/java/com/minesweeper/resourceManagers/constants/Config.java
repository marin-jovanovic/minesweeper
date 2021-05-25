package com.minesweeper.resourceManagers.constants;

public class Config {

    private static final String CONSTANTS_MEMORY_PATH = "/constants.txt";
    private static final String DEFAULT_CONSTANTS_MEMORY_PATH = "/defaultConstants.txt";


//    public static final String CONSTANTS_MEMORY_PATH = "src/main/resources/settings_logs/settings.txt";
//    public static final String DEFAULT_CONSTANTS_MEMORY_PATH = "src/main/resources/settings_logs/defaultSettings.txt";

    public static String getConstantsMemoryPath() {
        return CONSTANTS_MEMORY_PATH;
    }

    public static String getDefaultConstantsMemoryPath() {
        return DEFAULT_CONSTANTS_MEMORY_PATH;
    }
}
