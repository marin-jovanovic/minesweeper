package main.imageModule;

import java.io.File;

//fixme prev values did not have file.separator at end
public class Config {

    //    formats in which images will be loaded
    private static final String[] ORIGINAL_IMAGES_FORMATS_NAMES = {"png", "jpg"};

    private static final String OPENED_TILES = "opened_tiles";
    private static final String CLOSED_TILES = "closed_tiles";
    private static final String BUTTON_PATH = "button";

    private static final String DOT = ".";
    private static final String IMAGES_FORMAT_NAME = "png";

    private static final String RESIZED_IMAGES_PATH =
        "resources" + File.separator +
        "images" + File.separator +
        "resized_images";
    private static final String ORIGINAL_IMAGES_PATH =
        "resources" + File.separator +
        "images" + File.separator +
        "custom";

    private static final int PICTURE_WIDTH = 50;
    private static final int PICTURE_HEIGHT = 50;

    public static String getResizedImagesPath() {
        return RESIZED_IMAGES_PATH;
    }

    public static String getOriginalImagesPath() {
        return ORIGINAL_IMAGES_PATH;
    }

    public static String getImagesFormatName() {
        return IMAGES_FORMAT_NAME;
    }

    public static String[] getOriginalImagesFormatsNames() {
        return ORIGINAL_IMAGES_FORMATS_NAMES;
    }

    public static String getOpenedTiles() {
        return OPENED_TILES;
    }

    public static String getClosedTiles() {
        return CLOSED_TILES;
    }

    public static String getButtonPath() {
        return BUTTON_PATH;
    }

    public static String getDOT() {
        return DOT;
    }

    public static int getPictureWidth() {
        return PICTURE_WIDTH;
    }

    public static int getPictureHeight() {
        return PICTURE_HEIGHT;
    }
}
