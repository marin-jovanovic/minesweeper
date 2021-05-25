package com.minesweeper.resourceManagers.images;

//fixme prev values did not have file.separator at end
public class Config {

    private static final String backslash = "/";
    //    formats in which images will be loaded
    private static final String[] ORIGINAL_IMAGES_FORMATS_NAMES = {"png", "jpg"};
    private static final String OPENED_TILES = "opened_tiles";
    private static final String CLOSED_TILES = "closed_tiles";
    private static final String BUTTON_PATH = "button";
    private static final String TIME_TILES = "time";
    private static final String DOT = ".";
    private static final String IMAGES_FORMAT_NAME = "png";
    private static final String CUSTOM_IMAGES_PATH =
           backslash +
                    "images" + backslash +
                    "resized_images";
    private static final String ORIGINAL_IMAGES_PATH =
                     backslash +
                    "images" + backslash +
                    "original_images";
    private static final String REDUCED_CUSTOM_IMAGES_PATH =
             backslash +
                    "images" + backslash +
                    "resized_images";
    private static final String REDUCED_ORIGINAL_IMAGES_PATH =
             backslash +
                    "images" + backslash +
                    "resized_images";
    private static final int PICTURE_WIDTH = 50;
    private static final int PICTURE_HEIGHT = 50;

    public static String getTimeTiles() {
        return TIME_TILES;
    }

    public static String getBackslash() {
        return backslash;
    }

    public static String getOriginalImagesPath() {
        return ORIGINAL_IMAGES_PATH;
    }

    public static String getReducedOriginalImagesPath() {
        return REDUCED_ORIGINAL_IMAGES_PATH;
    }

    public static String getReducedCustomImagesPath() {
        return REDUCED_CUSTOM_IMAGES_PATH;
    }

    public static String getCustomImagesPath() {
        return CUSTOM_IMAGES_PATH;
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
