package main.settingsWindow.settingsManager;

import main.constants.Constant;
import main.constants.ConstantsManager;
import main.constants.Image;
import main.mainWindow.MainFrame;
import main.settingsWindow.elements.reset.RestartDefaultButton;
import main.utils.imagesDrivers.ResizeImages;

import javax.swing.plaf.metal.MetalIconFactory;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * images need to be copied to resources/images/usersImages/b
 */
public class SettingsManager {

    public static void main(String[] args) {

//        key, value
//        SettingsBuffer.writeToBuffer("number of columns", "12");
//        SettingsBuffer.writeToBuffer("number of rows", "32");
//        SettingsBuffer.writeToBuffer("number of mines", "12");
//        SettingsBuffer.writeToBuffer("number of columns", "3");


//        saveSettings();
//        restartSettings();


        processNewImage(new File("C:/Users/Pc/Documents/Lightshot/Screenshot_1.png"),
                Image.FOUR);
    }



    public static void addSettingToBuffer(String key, String value) {
        System.out.println(":::: addding " + key + " " + value);
        SettingsBuffer.writeToBuffer(key, value);
    }

    public static void printCurrentStateOfBuffer() {
        SettingsBuffer.printBufferContent();
    }

    public static void saveSettings() {
        if (SettingsBuffer.getBuffer().isEmpty()) {
            System.out.println("buffer is empty");
            return;
        }

        System.out.println("SettingsFrame saveSettings");

        System.out.println(SettingsBuffer.getBuffer());

        try (BufferedReader file = new BufferedReader(new FileReader(ConstantsManager.SETTINGS_MEMORY_PATH))){

            System.out.println("*** try");
            String line;

            while ((line = file.readLine()) != null) {
                System.out.println(line);

//                key and value
                String[] raw = line.split(" = ");


                if (! SettingsBuffer.getBuffer().containsKey(raw[0])) {
                    SettingsBuffer.getBuffer().put(raw[0], raw[1]);
                }

            }
        } catch (IOException e) {
            System.out.println("ioexception");
            e.printStackTrace();
        } catch (Exception exception) {
            System.out.println("exception");
            exception.printStackTrace();
        }
        SettingsBuffer.printBufferContent();

        writeToSettings();

//        MainFrame.restartSequence();
    }


//    make overload for File
    public static void processNewImage(File source, Image image) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        System.out.println(source.exists());

        String folder = image.getGroup();
        String name = image.getPathID();

        System.out.println("***\n" + folder);
        System.out.println(name);


        File destination = new File(name);
//        File destination = new File(
//                System.getProperty("user.dir")+ File.separator +
//                "src" + File.separator +
//                "main" + File.separator +
//                "resources" + File.separator +
//                "images" + File.separator +
//                "custom" +File.separator +
//                folder + File.separator +
//                name + ".png"
//        );

        System.out.println(destination);
        System.out.println(destination.exists());

        File f = new File("src/main/resources/images/resized_images/");

        System.out.println(f.exists());

        ResizeImages.resizeAndSaveImage(source, destination);
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
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner myReader = new Scanner(new File(ConstantsManager.DEFAULT_SETTINGS_MEMORY_PATH))) {
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        writeToSettings(String.join("\n", lines));
    }

    private static void writeToSettings(String newSettings) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(ConstantsManager.SETTINGS_MEMORY_PATH)) {
            fileOutputStream.write(newSettings.getBytes());
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    //    using settingsbuffer
    private static void writeToSettings() {
        String newSettings = SettingsBuffer.getBuffer().keySet().stream().map(
                key -> key + " = " + SettingsBuffer.getBuffer().get(key) + "\n"
        ).collect(Collectors.joining());
        writeToSettings(newSettings);
    }


}
