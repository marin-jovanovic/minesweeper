package main.constants;

import main.constants.imageDrivers.ImagesConstants;

public class Path {

    private static int rowNumber = 0;

    private static String calc(String a, String name) {
        return ImagesConstants.RESIZED_IMAGES_PATH + a + name +
                ImagesConstants.DOT +  ImagesConstants.IMAGES_FORMAT_NAME;
    }

    public static int getRowNumber() {
        rowNumber++;
        return rowNumber;
    }

    public static String getButtonPath(String name) {
        return  calc(ImagesConstants.BUTTON_PATH, name);
    }

    public static String getClosedTilePath(String name) {
        return calc(ImagesConstants.CLOSED_TILES, name);
    }

    public static String getOpenedTilePath(String name) {
        return calc(ImagesConstants.OPENED_TILES, name);
    }
}
