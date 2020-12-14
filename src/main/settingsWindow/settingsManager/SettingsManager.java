package main.settingsWindow.settingsManager;

import main.constants.ConstantsManager;
import main.constants.Image;
import main.settingsWindow.elements.reset.RestartDefaultButton;
import main.utils.imagesDrivers.ResizeImages;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;
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


        restartAllImages();
//
//        processNewImage(new File("C:/Users/Pc/Documents/Lightshot/Screenshot_1.png"),
//                Image.FOUR);
    }



    public static void addSettingToBuffer(String key, String value) {
        System.out.println(":::: adding " + key + " " + value);
        SettingsBuffer.writeToBuffer(key, value);
    }

    public static void printCurrentStateOfBuffer() {
        SettingsBuffer.printBufferContent();
    }


    /**
     * checks if buffer is empty, if true returns
     *
     * completes buffer from settingsbuffer so that it contains all constant settings
     *
     * writes to settings changes made
     */
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
    }

    public static class CopyFileVisitor extends SimpleFileVisitor<Path> {
        private final Path targetPath;
        private Path sourcePath = null;

        public CopyFileVisitor(Path targetPath) {
            this.targetPath = targetPath;
        }

        @Override
        public FileVisitResult preVisitDirectory(final Path dir,
                                                 final BasicFileAttributes attrs) throws IOException {
            if (sourcePath == null) {
                sourcePath = dir;
            }
            System.out.println();
            System.out.println(dir);


//            else {
//                Files.createDirectories(targetPath.resolve(sourcePath
//                        .relativize(dir)));
//            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(final Path file,
                                         final BasicFileAttributes attrs) throws IOException {

            System.out.println(file);

            File myObj = new File(String.valueOf(targetPath.resolve(sourcePath.relativize(file))));

            System.out.println("** " + String.valueOf(myObj));
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
//
            Files.copy(file,
                    targetPath.resolve(sourcePath.relativize(file)));
            return FileVisitResult.CONTINUE;
        }
    }
    public static void restartAllImages() {

        Path sourcePath =  Paths.get(new File("src/main/resources/images/resized_images").getAbsolutePath());
        Path targetPath =  Paths.get(new File("src/main/resources/images/custom").getAbsolutePath());

        try {
            Files.walkFileTree(sourcePath, new CopyFileVisitor(targetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        flushaj sve slike
        Image.flushAllImageIcons();
    }

    //    make overload for File
    /**
     * @param source
     * gets image from source
     *
     * @param image
     * from enum Image
     * gets it path and updates image to that image
     *

     */
    public static String processNewImage(File source, Image image, String in) {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        if (source.exists()) {
            System.out.println("source exists");
        }

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




        return "done";
    }


//    make overload for File
    /**
     * @param source
     * gets image from source
     *
     * @param image
     * from enum Image
     * gets it path and updates image to that image
     *

     */
    public static void processNewImage(File source, Image image) {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        if (source.exists()) {
            System.out.println("source exists");
        }

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

        image.flushImageIcon();


    }

    /**
     *  sets to default, sends message to restart default button jpanel it is updated
    */
     public static void restartSettings(RestartDefaultButton restartDefaultButton) {
        restartSettingsDriver();
        restartAllImages();

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
