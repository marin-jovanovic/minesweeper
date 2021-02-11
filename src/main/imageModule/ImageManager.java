package main.imageModule;

import main.Loader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Stream;

public class ImageManager {

    public static void main(String[] args) {
        String a = "time";

        ImageManager.resizeAllImagesInFolder("src/main/resources/images/original_images/" + a,
                "src/main/resources/images/resized_images/" + a);

//        resizeAndSaveImage("src/main/resources/original_images/time/0.png",
//                "src/main/resources/resized_images/slika.png");
    }

    public static void restartAllImages() {

        // todo write logic which copy from source to target and deletes target

        Path sourcePath = (Path) Paths.get(new File(Config.getImagesSourcePath()).getAbsolutePath());
        Path targetPath = (Path) Paths.get(new File(Config.getImagesDestinationPath()).getAbsolutePath());
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
        flushAllImageIcons();
    }

    /**
     * resize, save, flush image
     *
     * @param source gets image from source
     * @param image  from enum Image
     *               gets it path and updates image to that image
     */
    public static void processNewImage(File source, Image image) {

        System.out.println(source.exists());

        String name = image.getPath();

        File destination = new File(name);

        resizeAndSaveImage(source, destination);

        image.flushImageIcon();


    }

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;

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

                if (Arrays.asList(Image.ORIGINAL_IMAGES_FORMATS_NAMES).contains(format)) {
                    resizeAndSaveImage(String.valueOf(path), (destination + "/" + name + ".png"));
                    System.out.println(destination + path.getFileName());
                }

                System.out.println();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resizeAndSaveImage(File source, File destination) {
        try {

            BufferedImage originalImage = ImageIO.read(source);
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImage = resizeImage(originalImage, type,
                    Image.PICTURE_WIDTH, Image.PICTURE_HEIGHT);

            ImageIO.write(resizeImage, Image.ImageConstants.getImagesFormatName(), destination);
//            ImageIO.write(resizeImage, Image.IMAGES_FORMAT_NAME, destination);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void resizeAndSaveImage(String source, String destination) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(source));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImage = resizeImage(originalImage, type,
                    Image.PICTURE_WIDTH, Image.PICTURE_HEIGHT);

            ImageIO.write(resizeImage, Image.ImageConstants.getImagesFormatName(), new File(destination));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type,
                                             int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public static void flushAllImageIcons() {

        for (Image image : EnumSet.allOf(Image.class)) {
            image.flushImageIcon();
//            image.getImageIcon().getImage().flush();
        }

        System.out.println("flushed all images");

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
