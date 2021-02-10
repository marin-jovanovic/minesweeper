package main.constantModule;

public class Config {

    public static final String CONSTANTS_MEMORY_PATH = "src/main/resources/settings_logs/settings.txt";
    public static final String DEFAULT_CONSTANTS_MEMORY_PATH = "src/main/resources/settings_logs/defaultSettings.txt";

    public static String getConstantsMemoryPath() {
        return CONSTANTS_MEMORY_PATH;
    }

    public static String getDefaultConstantsMemoryPath() {
        return DEFAULT_CONSTANTS_MEMORY_PATH;
    }
}
