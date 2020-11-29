package main.utils.imagesDrivers;

import main.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ResizeImages {

    public static void main(String[] args) {

        resizeAllImagesInFolder("src/main/resources/original_images/time",
                "src/main/resources/resized_images/time");

    }

    public static void resizeAllImagesInFolder(String source, String destination) {
        try (Stream<Path> paths = Files.walk(Paths.get(source))) {
            paths.forEach(path -> {
                System.out.println(path);
                System.out.println(path.getFileName());

                String name = (path.getFileName()).toString().replaceFirst("[.][^.]+$", "");

                if (String.valueOf(path).endsWith(".png") || String.valueOf(path).endsWith(".jpg")) {
                    resizeAndSaveImage(String.valueOf(path), (destination + "/" + name + ".png"));

                    System.out.println(destination + path.getFileName());
                }

                System.out.println();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void resizeAndSaveImage(String source, String destination) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(source));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImage = resizeImage(originalImage, type,
                    Constants.PICTURE_WIDTH, Constants.PICTURE_HEIGHT);
            ImageIO.write(resizeImage, Constants.IMAGES_FORMAT, new File(destination));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
}
