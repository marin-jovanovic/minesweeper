package main.constantModule;

import main.settingsWindow.elements.reset.RestartDefaultButton;
import main.utils.imagesDrivers.ResizeImages;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SettingsManager {

    public static void main(String[] args) {
        restartAllImages();
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
     * <p>
     * completes buffer from settingsbuffer so that it contains all constant settings
     * <p>
     * writes to settings changes made
     */
    public static void saveSettings() {
        if (SettingsBuffer.getBuffer().isEmpty()) {
            System.out.println("buffer is empty");
            return;
        }

        System.out.println("SettingsFrame saveSettings");

        System.out.println(SettingsBuffer.getBuffer());

        try (BufferedReader file = new BufferedReader(new FileReader(Config.SETTINGS_MEMORY_PATH))) {

            System.out.println("*** try");
            String line;

            while ((line = file.readLine()) != null) {
                System.out.println(line);

//                key and value
                String[] raw = line.split(" = ");


                if (!SettingsBuffer.getBuffer().containsKey(raw[0])) {
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

//    private static class CopyFileVisitor extends SimpleFileVisitor<Path> implements FileVisitor<java.nio.file.Path> {
//        private final Path targetPath;
//        private Path sourcePath = null;
//
//        public CopyFileVisitor(Path targetPath) {
//            this.targetPath = targetPath;
//        }
//
//        @Override
//        public FileVisitResult preVisitDirectory(final Path dir,
//                                                 final BasicFileAttributes attrs) throws IOException {
//            if (sourcePath == null) {
//                sourcePath = dir;
//            }
//            System.out.println();
//            System.out.println(dir);
//
//
////            else {
////                Files.createDirectories(targetPath.resolve(sourcePath
////                        .relativize(dir)));
////            }
//            return FileVisitResult.CONTINUE;
//        }
//
//        @Override
//        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) {
//
//            System.out.println(file);
//
//            File myObj = new File(String.valueOf(targetPath.resolve(sourcePath.relativize(file))));
//
//            System.out.println("** " + myObj);
//            if (myObj.delete()) {
//                System.out.println("Deleted the file: " + myObj.getName());
//            } else {
//                System.out.println("Failed to delete the file.");
//            }
////
//            Files.copy(file, targetPath.resolve(sourcePath.relativize(file)));
//            return FileVisitResult.CONTINUE;
//        }
//    }

    public static void restartAllImages() {

        Path sourcePath = (Path) Paths.get(new File(Config.IMAGES_SOURCE_PATH).getAbsolutePath());
        Path targetPath = (Path) Paths.get(new File(Config.IMAGES_DESTINATION_PATH).getAbsolutePath());

//        try {
//            Files.walkFileTree(sourcePath, new CopyFileVisitor(targetPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        flushaj sve slike
        Image.flushAllImageIcons();
    }

    /**
     * @param source gets image from source
     * @param image  from enum Image
     *               gets it path and updates image to that image
     */
    public static void processNewImage(File source, Image image) {

        System.out.println(source.exists());

        String folder = image.getGroup();
        String name = image.getPathID();

        System.out.println("***\n" + folder);
        System.out.println(name);


        File destination = new File(name);

        System.out.println(destination);
        System.out.println(destination.exists());

        ResizeImages.resizeAndSaveImage(source, destination);

        image.flushImageIcon();


    }

    /**
     * sets to default, sends message to restart default button jpanel it is updated
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
        restartSettingsDriver();
    }

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

        writeToSettings(String.join("\n", lines));
    }

    private static void writeToSettings(String newSettings) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Config.SETTINGS_MEMORY_PATH)) {
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
