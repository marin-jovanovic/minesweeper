package main.constants;

import com.sun.jdi.PrimitiveValue;
import main.constants.imageDrivers.ImagesConstants;

public class Paths {
//    private static final String startButtonPath = ImagesConstants.RESIZED_IMAGES_PATH +
//            ImagesConstants.BUTTON_PATH;
//    private static final String endButtonPath = ImagesConstants.DOT +
//            ImagesConstants.IMAGES_FORMAT_NAME;
//
//    public static String getStartButtonPath() {
//        return startButtonPath;
//    }
//
//    public static String getEndButtonPath() {
//        return endButtonPath;
//    }

    private static String start = ImagesConstants.RESIZED_IMAGES_PATH;
    private static String end =          ImagesConstants.DOT +
            ImagesConstants.IMAGES_FORMAT_NAME;

    private static String calc(String a, String name) {
        return start + a + name + end;
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

//    public static
//
//
//       return new ImageIcon(ImagesConstants.RESIZED_IMAGES_PATH +
//            ImagesConstants.CLOSED_TILES + this.pathID + ImagesConstants.DOT +
//            ImagesConstants.IMAGES_FORMAT_NAME)
//
//    private static final String startClosedTilePath =
}
