package main.imageModule;

import main.utils.imagesDrivers.ResizeImages;

import java.io.File;
import java.nio.file.Paths;

public class ImageManager {
    public static void restartAllImages() {

        // todo write logic which copy from source to target and deletes target

        Path sourcePath = (Path) Paths.get(new File(Config.IMAGES_SOURCE_PATH).getAbsolutePath());
        Path targetPath = (Path) Paths.get(new File(Config.IMAGES_DESTINATION_PATH).getAbsolutePath());
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
        Image.flushAllImageIcons();
    }

    /**
     * dont know
     *
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
}
