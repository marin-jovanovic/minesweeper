package main.imageModule;

import main.Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

public enum Image {


    VICTORY(ImageConstants.BUTTON_PATH, "victory"),
    DEFEAT(ImageConstants.BUTTON_PATH, "defeat"),
    PLAY_AGAIN(ImageConstants.BUTTON_PATH, "playAgain"),

    CLOSED_CELL(ImageConstants.CLOSED_TILES, "closedCell"),
    FLAG(ImageConstants.CLOSED_TILES, "flag"),
    NOT_SURE(ImageConstants.CLOSED_TILES, "notSure"),

    ZERO(ImageConstants.OPENED_TILES, "0"),
    ONE(ImageConstants.OPENED_TILES, "1"),
    TWO(ImageConstants.OPENED_TILES, "2"),
    THREE(ImageConstants.OPENED_TILES, "3"),
    FOUR(ImageConstants.OPENED_TILES, "4"),
    FIVE(ImageConstants.OPENED_TILES, "5"),
    SIX(ImageConstants.OPENED_TILES, "6"),
    SEVEN(ImageConstants.OPENED_TILES, "7"),
    EIGHT(ImageConstants.OPENED_TILES, "8"),
    MINE(ImageConstants.OPENED_TILES, "-1");

//    public static ImageIcon getImageIcon(String path){
//        try {
//            return new ImageIcon(ImageIO.read(Loader.class.getResource(path)));
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        return null;
//
//    }

    public static class ImageConstants {
        private static final String CUSTOM_IMAGES_PATH =
                //        "src" + File.separator +
//                "main" + File.separator +
                "resources" + File.separator +
                "images" + File.separator +
                "custom" + File.separator;

        private static final String OPENED_TILES = "opened_tiles" + File.separator;
        private static final String CLOSED_TILES = "closed_tiles" + File.separator;
        private static final String BUTTON_PATH = "button" + File.separator;
        private static final String DOT = ".";
        private static final String IMAGES_FORMAT_NAME = "png";

        public static String getImagesFormatName() {
            return IMAGES_FORMAT_NAME;
        }
    }

    //    formats in which images will be loaded
    public static final String[] ORIGINAL_IMAGES_FORMATS_NAMES = {"png", "jpg"};
    public static int PICTURE_WIDTH = 50;
    public static int PICTURE_HEIGHT = 50;

    private final String path;
    private ImageIcon imageIcon;


    Image(String folder, String name) {
        this.path = ImageConstants.CUSTOM_IMAGES_PATH + folder + name + ImageConstants.DOT + ImageConstants.IMAGES_FORMAT_NAME;
        try {
            this.imageIcon = new ImageIcon(ImageIO.read(Loader.class.getResource(this.path)));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public String getPath() {
        return path;
    }
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void flushImageIcon() {
        new ImageIcon(path).getImage().flush();
    }

    @Override
    public String toString() {
        return this.name() + " {" +
                "pathID='" + path + '\'' +
                '}';
    }

    public static void main(String[] args) {
        for (Image image : EnumSet.allOf(Image.class)) {
            System.out.println(image);
        }
    }


}
