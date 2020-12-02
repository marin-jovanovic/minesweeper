package main.constants;

import java.io.BufferedReader;
import java.io.FileReader;

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



//    where this is saved
    public static String SETTINGS_MEMORY_PATH = "src/main/resources/settings.txt";


    private static final String ORIGINAL_IMAGES_PATH = "src/main/resources/original_images/";

    public static final String RESIZED_IMAGES_PATH = "src/main/resources/resized_images/";

    public static final String CLOSED_TILES = "closed_tiles/";
    public static final String OPENED_TILES = "opened_tiles/";
    public static final String BUTTON_PATH = "button/";

    public static final String DOT = ".";

    //    format in which images will be changed
    public static String IMAGES_FORMAT_NAME = "png";

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
