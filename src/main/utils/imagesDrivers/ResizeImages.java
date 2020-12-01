package main.utils.imagesDrivers;

import main.Constants;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ResizeImages {

    public static void main(String[] args) {

//        resizeAllImagesInFolder("src/main/resources/original_images/time",
//                "src/main/resources/resized_images/time");
//

//        List<String> list = new ArrayList<String>();
//        lis

        String a = "button";

        resizeAllImagesInFolder("src/main/resources/original_images/" + a,
                "src/main/resources/resized_images/" + a);
//        button
//                closed_tiles
//                Opened_tiles
//                        time
//        resizeAndSaveImage("src/main/resources/original_images/time/0.png",
//                "src/main/resources/resized_images/slika.png");
    }

    public static void resizeAllImagesInFolder(String source, String destination) {
        try (Stream<Path> paths = Files.walk(Paths.get(source))) {
            paths.forEach(path -> {
                System.out.println(path);
                System.out.println(path.getFileName());


                String name = (path.getFileName()).toString().replaceFirst("[.][^.]+$", "");
                System.out.println(name);

//                extension
                String format = path.toString().substring(path.toString().lastIndexOf('.')+1);

//                String format = "";
//
//                int i = path.toString().lastIndexOf('.');
//                if (i > 0) {
//                    format = path.toString().substring(i+1);
//                }


                if (Arrays.asList(Constants.ORIGINAL_IMAGES_FORMATS_NAMES).contains(format)) {

//                }

//                if (Arrays.asList(Constants.ORIGINAL_IMAGES_FORMATS_NAMES).contains(String.valueOf(path))) {
//                if (false) {
//                }
//                if (String.valueOf(path).endsWith(".png") || String.valueOf(path).endsWith(".jpg")) {
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

//            if (!(new File(source).exists())) {
//                System.out.println("source does not exist");
//            }
//            else {
//                System.out.println("source exists");
//            }
//            try
//            {
//                Image picture = ImageIO.read(new File(source));
//            }
//            catch (IOException e)
//            {
//                String workingDir = System.getProperty("user.dir");
//                System.out.println("Current working directory : " + workingDir);
//                e.printStackTrace();
//            }

            BufferedImage originalImage = ImageIO.read(new File(source));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImage = resizeImage(originalImage, type,
                    Constants.PICTURE_WIDTH, Constants.PICTURE_HEIGHT);


            ImageIO.write(resizeImage, Constants.IMAGES_FORMAT_NAME, new File(destination));

//            if (!(new File(destination).exists())) {
//                System.out.println("destination does not exist");
//            }
//            else {
//                System.out.println("destination exists");
//            }
//
//            try
//            {
//                Image picture = ImageIO.read(new File(destination));
//            }
//            catch (IOException e)
//            {
//                String workingDir = System.getProperty("user.dir");
//                System.out.println("Current working directory : " + workingDir);
//                e.printStackTrace();
//            }

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
