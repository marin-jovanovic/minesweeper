package main.settingsWindow;

import main.constants.ConstantsManager;
import main.settingsWindow.elements.reset.RestartDefaultButton;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


// TODO
//






public class SettingsManager {


    public static void main(String[] args) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add("a");
        temp.add("c");
        temp.add("f");
        temp.add("k");
        temp.add("b");

        System.out.println(temp);
    }

    public static void saveSettings() {

//        check if settings.txt exist

//        check if settings list is complete
//        if not add them
        ArrayList<String> bufferList = completeSettings();
        System.out.println("SettingsFrame saveSettings");
        System.out.println(bufferList);
        writeToSettings(String.join("\n", bufferList));
    }

//    sets to default, sends message to restart default button jpanel it is updated
    public static void restartSettings(RestartDefaultButton restartDefaultButton) {
        restartSettingsDriver();

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
        //            read settings from @defaultsettings.txt
        restartSettingsDriver();
    }


    private static void restartSettingsDriver() {
        //            read settings from @defaultsettings.txt
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner myReader = new Scanner(new File(ConstantsManager.DEFAULT_SETTINGS_MEMORY_PATH))) {
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

//            write settings to @settings.txt
        writeToSettings(String.join("\n", lines));
    }

    //    completes new settings that will be written in settings.txt
    private static ArrayList<String> completeSettings() {
        ArrayList<String> bufferList = new ArrayList<>();

        System.out.println("complete settings");
        System.out.println(SettingsBuffer.getBuffer());

        try (BufferedReader file = new BufferedReader(new FileReader(ConstantsManager.SETTINGS_MEMORY_PATH))){

            String line = file.readLine();

            ////            check if settings.txt is empty

            //            adds settings&config that are not targeted by settingsFrame elements
            do {
                int i = line.lastIndexOf(" ");

                String key = line.substring(0, i);
                String value = line.substring(i + 1);

                if (! SettingsBuffer.getBuffer().containsKey(key)) {
                    SettingsBuffer.getBuffer().put(key, value);
                }

            } while (! (line = file.readLine()).isBlank());

//            adds new settings
            SettingsBuffer.getBuffer().forEach((key, value) -> bufferList.add(key + " " + value));

            bufferList.add("");

            while ((line = file.readLine()) != null) {
                bufferList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferList;
    }

    private static void writeToSettings(String newSettings) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(ConstantsManager.SETTINGS_MEMORY_PATH)) {
            fileOutputStream.write(newSettings.getBytes());
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }


}
