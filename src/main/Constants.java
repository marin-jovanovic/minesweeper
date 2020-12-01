package main;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Constants {

    public static int NUMBER_OF_COLUMNS =10;
    public static int NUMBER_OF_ROWS = 10;
    public static int NUMBER_OF_MINES = 15;

//    TODO auto determinate, if user resizes window it must log values auto
//    from top left of the screen select where window will start
    public static int LOCATION_X = 20;
    public static int LOCATION_Y = 20;

//    default window size (MainFrame)
    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    public static int PICTURE_WIDTH = 50;
    public static int PICTURE_HEIGHT = 50;

//    formats in which images will be loaded
    public static String[] ORIGINAL_IMAGES_FORMATS_NAMES = {"png", "jpg"};

//    format in which images will be changed
    public static String IMAGES_FORMAT_NAME = "png";

//    private static String ORIGINAL_IMAGES_PATH = "src/main/resources/original_images/";
    private static final String RESIZED_IMAGES_PATH = "src/main/resources/resized_images/";



//    where this is saved
    public static String SETTINGS_MEMORY_PATH = "src/main/resources/settings.txt";


    public static String getPathImageClosedTiles(ClosedTileStatus closedTileStatus)
            throws InvalidClosedTileStatusException {

        switch(closedTileStatus) {
            case CLOSED_CELL:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "closedCell" + "." + IMAGES_FORMAT_NAME;
            case FLAG:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "flag" + "." + IMAGES_FORMAT_NAME;
            case NOT_SURE:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "notSure" + "." + IMAGES_FORMAT_NAME;
            default:
                throw new InvalidClosedTileStatusException("exception occurred in " +
                        "getPathImageClosedTiles for: "
                        + closedTileStatus.toString());
        }
    }

    public static String getPathImageOpenedTiles(OpenedTileStatus openedTileStatus)
            throws InvalidClosedTileStatusException {

        switch(openedTileStatus) {
            case ZERO:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "0" + "." + IMAGES_FORMAT_NAME;
            case ONE:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "1" + "." + IMAGES_FORMAT_NAME;
            case TWO:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "2" + "." + IMAGES_FORMAT_NAME;
            case THREE:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "3" + "." + IMAGES_FORMAT_NAME;
            case FOUR:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "4" + "." + IMAGES_FORMAT_NAME;
            case FIVE:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "5" + "." + IMAGES_FORMAT_NAME;
            case SIX:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "6" + "." + IMAGES_FORMAT_NAME;
            case SEVEN:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "7" + "." + IMAGES_FORMAT_NAME;
            case EIGHT:
                return RESIZED_IMAGES_PATH + "closed_tiles/" + "8" + "." + IMAGES_FORMAT_NAME;
            default:
                throw new InvalidClosedTileStatusException("exception occurred in " +
                        "getPathImageOpenedTiles for: "
                        + openedTileStatus.toString());
        }
    }

    public static String getPathImageButton(ButtonStatus buttonStatus)
            throws InvalidButtonStatusException {
        String path = "button/";

        switch(buttonStatus) {

            case VICTORY:
                return RESIZED_IMAGES_PATH + path + "victory" + "." + IMAGES_FORMAT_NAME;
            case DEFEAT:
                return RESIZED_IMAGES_PATH + path + "defeat" + "." + IMAGES_FORMAT_NAME;
            case PLAY_AGAIN:
            case INIT:
                return RESIZED_IMAGES_PATH + path + "playAgain" + "." + IMAGES_FORMAT_NAME;
            default:
                throw new InvalidButtonStatusException("exception occurred in " +
                        "getPathImageButton for: "
                        + buttonStatus.toString());
        }
    }








//    public static String getPathImageClosedTiles(String value) {
//
//        if (Arrays.asList("closedCell", "flag", "notSure").contains(value)) {
//            return RESIZED_IMAGES_PATH + "closed_tiles/" + value + "." + IMAGES_FORMAT_NAME;
//        } else {
//            throw new IndexOutOfBoundsException();
//        }
//
//    }

////    button
//    public static String getPathImageButton(String value) {
//
//        if (Arrays.asList("victory", "defeat", "playAgain", "init").contains(value)) {
//            return RESIZED_IMAGES_PATH + "button/" + value + "." + IMAGES_FORMAT_NAME;
//        } else {
//            throw new IndexOutOfBoundsException();
//        }
//
//    }
//
////    opened tiles
//    public static String getPathImageOpenedTiles(String value) {
//        if (Integer.parseInt(value) >= -1 && Integer.parseInt(value) <= 8) {
//            return RESIZED_IMAGES_PATH + "opened_tiles/" + value + "." + IMAGES_FORMAT_NAME;
//        } else {
//            throw new IndexOutOfBoundsException();
//        }
//    }
//
////    image path
//    public static String getPathImageTime(String time) {
//        if (Integer.parseInt(time) >= 0 && Integer.parseInt(time) <= 9) {
//            return RESIZED_IMAGES_PATH + "time/" + time + "." + IMAGES_FORMAT_NAME;
//        } else {
//            throw new IndexOutOfBoundsException();
//        }
//    }

    public static void refresh() {

        try {
            BufferedReader file = new BufferedReader(new FileReader(SETTINGS_MEMORY_PATH));

            NUMBER_OF_ROWS = Integer.parseInt(((file.readLine().split(" "))[4]));
            NUMBER_OF_COLUMNS = Integer.parseInt(((file.readLine().split(" "))[4]));
            NUMBER_OF_MINES = Integer.parseInt(((file.readLine().split(" "))[4]));

            WIDTH = NUMBER_OF_COLUMNS * PICTURE_WIDTH;
            HEIGHT = NUMBER_OF_ROWS * PICTURE_HEIGHT;

            System.out.println("new constants: ");
            System.out.println(NUMBER_OF_COLUMNS);
            System.out.println(NUMBER_OF_ROWS);
            System.out.println(NUMBER_OF_MINES);

            file.close();

        } catch (Exception er) {
            er.printStackTrace();
        }
    }
}
