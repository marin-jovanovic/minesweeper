package main.settingsWindow;

import main.constants.ConstantsManager;
import main.settingsWindow.elements.reset.RestartDefaultButton;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


// TODO
//


/**
 * images need to be copied to resources/images/usersImages/b
 */
public class SettingsManager {


    public static void main(String[] args) {
//        ArrayList<String> temp = new ArrayList<>();
//        temp.add("a");
//        temp.add("c");
//        temp.add("f");
//        temp.add("k");
//        temp.add("b");
//
//        System.out.println(temp);

//        key, value
        SettingsBuffer.writeToBuffer("number of columns", "12");
        SettingsBuffer.writeToBuffer("number of rows", "32");
        SettingsBuffer.writeToBuffer("number of mines", "12");
        SettingsBuffer.writeToBuffer("number of columns", "3");


//        restartSettings();
        saveSettings();
    }

    public static void saveSettings() {

//        check if settings.txt exist

//        check if settings list is complete
//        if not add them
//        FIXME
//          if no setting changed -> empty buffer event
//          all settings changed
//
//        ArrayList<String> bufferList = completeSettings();
//
//        SettingsBuffer.writeToBuffer("number of columns", "12");
//        SettingsBuffer.writeToBuffer("number of rows", "32");
//        SettingsBuffer.writeToBuffer("number of mines", "12");
//        SettingsBuffer.writeToBuffer("number of columns", "3");


        complete();
//        ArrayList<String> bufferList = complete(); //complete()
        System.out.println("SettingsFrame saveSettings");
//        System.out.println(bufferList);

        SettingsBuffer.printBufferContent();
        writeToSettings();

//        writeToSettings(String.join("\n", bufferList));
    }

    public static void complete() {
//        ArrayList<String> bufferList = new ArrayList<>();
        System.out.println("complete method");

        System.out.println(SettingsBuffer.getBuffer());



        try (BufferedReader file = new BufferedReader(new FileReader(ConstantsManager.SETTINGS_MEMORY_PATH))){

            System.out.println("*** try");
            String line;

            while ((line = file.readLine()) != null) {
                System.out.println(line);

                String[] raw = line.split(" = ");

                String key = raw[0];
                String value = raw[1];

                if (! SettingsBuffer.getBuffer().containsKey(key)) {
                    SettingsBuffer.getBuffer().put(key, value);
                }



            }

//            String line = file.readLine();
//
//            ////            check if settings.txt is empty
//
//            //            adds settings&config that are not targeted by settingsFrame elements
//
//
//
//            do {
//                int i = line.lastIndexOf(" ");
//
//                String key = line.substring(0, i);
//                String value = line.substring(i + 1);
//
//                if (! SettingsBuffer.getBuffer().containsKey(key)) {
//                    SettingsBuffer.getBuffer().put(key, value);
//                }
//
//            } while (! (line = file.readLine()).isBlank());

//            adds new settings
//            SettingsBuffer.getBuffer().forEach((key, value) -> bufferList.add(key + " " + value));
//
//            bufferList.add("");
//
//            while ((line = file.readLine()) != null) {
//                bufferList.add(line);
//            }

        } catch (IOException e) {
            System.out.println("ioexception");
            e.printStackTrace();
        } catch (Exception exception) {
            System.out.println("exception");
            exception.printStackTrace();
        }

//        return bufferList;
//        return SettingsBuffer.getBuffer();
//        return SettingsBuffer.getBuffer().entrySet()
//    return SettingsBuffer.getBuffer()ArrayList<Entry<String, Integer> >(SettingsBuffer.getBuffer().entrySet());


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
//        writeToSettings(String.join("\n", lines));
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

    private static void writeToSettings() {
        String newSettings = "";
//        SettingsBuffer.getBuffer().forEach((key, value) -> {
//            newSettings += key + " = " + value + "\n";
//        });

        newSettings = SettingsBuffer.getBuffer().keySet().stream().map(
                key -> key + " = " + SettingsBuffer.getBuffer().get(key) + "\n"
        ).collect(Collectors.joining());

        try (FileOutputStream fileOutputStream = new FileOutputStream(ConstantsManager.SETTINGS_MEMORY_PATH)) {
//            buffer.forEach((key, value) -> System.out.println(key + " -> " + value));


            fileOutputStream.write(newSettings.getBytes());
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }


}
