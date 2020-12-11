package main.constants;

public class Path {

    public static final String CUSTOM_IMAGES_PATH = "src/main/resources/images/custom/";
    public static final String RESIZED_IMAGES_PATH = "src/main/resources/images/resized_images/";
    public static final String CLOSED_TILES = "closed_tiles/";
    public static final String OPENED_TILES = "opened_tiles/";
    public static final String BUTTON_PATH = "button/";
    public static final String DOT = ".";
    public static final String ORIGINAL_IMAGES_PATH = "src/main/resources/original_images/";
    //    formats in which images will be loaded
    public static final String[] ORIGINAL_IMAGES_FORMATS_NAMES = {"png", "jpg"};
    //    format in which images will be changed
    public static final String IMAGES_FORMAT_NAME = "png";
    public static int PICTURE_WIDTH = 50;
    public static int PICTURE_HEIGHT = 50;
    private static int rowNumber = 0;

    private static String calc(String folder, String name) {
        return CUSTOM_IMAGES_PATH + folder + name +
                DOT +  IMAGES_FORMAT_NAME;
    }

    public static int getRowNumber() {
        rowNumber++;
        return rowNumber;
    }

    public static String getButtonPath(String name) {
        return  calc(BUTTON_PATH, name);
    }

    public static String getClosedTilePath(String name) {
        return calc(CLOSED_TILES, name);
    }

    public static String getOpenedTilePath(String name) {
        return calc(OPENED_TILES, name);
    }
}
