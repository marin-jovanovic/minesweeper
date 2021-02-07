package main.constantModule;

import main.imageModule.ImageManager;
import main.settingsWindow.elements.reset.RestartDefaultButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// todo what if default settings file is deleted

// todo handle restart settings with thread wait differently
public class SettingsManager {

    /**
     * sets to default, sends message to restart default button jpanel it is updated
     */
    public static void restartSettings(RestartDefaultButton restartDefaultButton) {
        restartSettingsDriver();
        ImageManager.restartAllImages();

        new Thread(() -> {
            restartDefaultButton.setCheckerText("settings restarted");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            restartDefaultButton.setCheckerText("");
        }).start();

    }

    //    sets to default
    public static void restartSettings() {
        restartSettingsDriver();
    }

    /**
     * reads from default and prints to custom settings
     */
    private static void restartSettingsDriver() {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner myReader = new Scanner(new File(Config.DEFAULT_SETTINGS_MEMORY_PATH))) {
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        writeToCustomSettings(String.join("\n", lines));
    }

    /**
     * writes to custom settings
     *
     * @param newSettings text which will be writen to custom settings
     */
    private static void writeToCustomSettings(String newSettings) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Config.SETTINGS_MEMORY_PATH)) {
            fileOutputStream.write(newSettings.getBytes());
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

}
