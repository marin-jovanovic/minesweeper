package main.constants;

import java.io.File;

public class Path {

    public static final String CUSTOM_IMAGES_PATH = "src" + File.separator +
                                                    "main" + File.separator +
                                                    "resources" + File.separator +
                                                    "images" + File.separator +
                                                    "custom" + File.separator;

    public static final String CLOSED_TILES = "closed_tiles" + File.separator;
    public static final String OPENED_TILES = "opened_tiles" + File.separator;
    public static final String BUTTON_PATH = "button" + File.separator;
    public static final String DOT = ".";

    //    formats in which images will be loaded
    public static final String[] ORIGINAL_IMAGES_FORMATS_NAMES = {"png", "jpg"};
    //    format in which images will be changed
    public static final String IMAGES_FORMAT_NAME = "png";
    public static int PICTURE_WIDTH = 50;
    public static int PICTURE_HEIGHT = 50;

    private static String calc(String folder, String name) {
        return CUSTOM_IMAGES_PATH + folder + name +
                DOT +  IMAGES_FORMAT_NAME;
    }


    public static String getButtonPath(String name) {
        return calc(BUTTON_PATH, name);
    }

    public static String getClosedTilePath(String name) {
        return calc(CLOSED_TILES, name);
    }

    public static String getOpenedTilePath(String name) {
        return calc(OPENED_TILES, name);
    }
}
