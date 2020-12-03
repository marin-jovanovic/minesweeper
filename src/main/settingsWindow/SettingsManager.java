package main.settingsWindow;

import main.constants.GeneralConstants;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SettingsManager {
    public static void saveSettings() {

//        check if settings.txt exist

//        check if settings list is complete
//        if not add them
        ArrayList<String> bufferList = completeSettings();

        writeToSettings(String.join("\n", bufferList));
    }

    //    completes new settings that will be written in settings.txt
        private static ArrayList<String> completeSettings() {
            ArrayList<String> bufferList = new ArrayList<>();

            try (BufferedReader file = new BufferedReader(new FileReader(GeneralConstants.SETTINGS_MEMORY_PATH))){

                String line = file.readLine();

    ////            check if settings.txt is empty
    //            if (line.isBlank()) {
    //                return;
    //            }

    //            adds settings&config that are not targeted by settingsFrame elements
                do {
                    int i = line.lastIndexOf(" ");

                    String key = line.substring(0, i);
                    String value = line.substring(i + 1);

                    if (! SettingsBuffer.getBuffer().containsKey(key)) {
                        SettingsBuffer.getBuffer().put(key, value);
                    }

                } while (! (line = file.readLine()).isBlank());

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
            try (FileOutputStream fileOutputStream = new FileOutputStream(GeneralConstants.SETTINGS_MEMORY_PATH)) {
                fileOutputStream.write(newSettings.getBytes());
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
}
