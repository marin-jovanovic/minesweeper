package main.imagesModule;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.stream.Stream;


//            image.flushImageIcon();
//            image.getImageIcon().getImage().flush();
public class ImageManager {

    public static void main(String[] args) {
        String a = "time";

        restartAllImages();

//        ImageManager.resizeAllImagesInFolder("src/main/resources/images/original_images/" + a,
//                "src/main/resources/images/resized_images/" + a);

//        resizeAndSaveImage("src/main/resources/original_images/time/0.png",
//                "src/main/resources/resized_images/slika.png");
    }

    public static void restartAllImages() {

        // todo write logic which copy from source to target and deletes target

        Path sourcePath = Paths.get(new File(Config.getCustomImagesPath()).getAbsolutePath());
        Path targetPath = Paths.get(new File(Config.getOriginalImagesPath()).getAbsolutePath());

        System.out.println(sourcePath);
        System.out.println(targetPath);

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

//        try {
//            Files.walkFileTree(sourcePath, new CopyFileVisitor(targetPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        flush all images
//        flushAllImageIcons();
    }


    /**
     * resize, save, flush image
     *
     * @param destinationImage gets image from source
     * @param image  from enum Image
     *               gets it path and updates image to that image
     */
    public static void processNewImage(File destinationImage, File sourceImage, Image image) {

        try {

            BufferedImage originalImage = ImageIO.read(sourceImage);
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizedImage = resizeImage(originalImage, type,
                    Config.getPictureWidth(), Config.getPictureHeight());

            System.out.println(destinationImage.exists());
            image.setImageIcon(new ImageIcon(resizedImage));

            ImageIO.write(resizedImage, Config.getImagesFormatName(), destinationImage);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void resizeAllImagesInFolder(String source, String destination) {
        try (Stream<java.nio.file.Path> paths = Files.walk(Paths.get(source))) {
            paths.forEach(path -> {
                System.out.println(path);
                System.out.println(path.getFileName());


                String name = (path.getFileName()).toString().replaceFirst("[.][^.]+$", "");
                System.out.println(name);

//                extension
                String format = path.toString().substring(path.toString().lastIndexOf('.') + 1);

                if (Arrays.asList(Config.getOriginalImagesFormatsNames()).contains(format)) {

//                    resize and save
                    try {
                        BufferedImage originalImage = ImageIO.read(new File(String.valueOf(path)));

                        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB :
                                originalImage.getType();

                        BufferedImage resizeImage = resizeImage(originalImage, type,
                                Config.getPictureWidth(), Config.getPictureHeight());

                        ImageIO.write(resizeImage, Config.getImagesFormatName(),
                                new File(destination + Config.getBackslash() + name +
                                        Config.getDOT() + Config.getImagesFormatName()));

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                     System.out.println(destination + path.getFileName());

                }

                System.out.println();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns resized original image
     *
     * @param originalImage original
     * @param type ex. .jpg
     * @param IMG_WIDTH image width - resized (not original)
     * @param IMG_HEIGHT image height - resized (not original)
     * @return
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, int type,
                                             int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    private static class CopyFileVisitor extends SimpleFileVisitor<Path> {
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
//              fixme
//            System.out.println(file);
//
//            File myObj = new File(String.valueOf(targetPath.resolve(sourcePath.relativize(file))));
//
//
//            System.out.println("** " + myObj);
//            if (myObj.delete()) {
//                System.out.println("Deleted the file: " + myObj.getName());
//            } else {
//                System.out.println("Failed to delete the file.");
//            }
////
//
//            Files.copy(file,
//                    targetPath.resolve(sourcePath.relativize(file)));
            return FileVisitResult.CONTINUE;
        }
    }
}
