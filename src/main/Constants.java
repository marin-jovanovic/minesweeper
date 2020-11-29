package main;

import javax.swing.*;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    public static String ORIGINAL_IMAGES_PATH = "src/main/resources/original_images/";
    public static String RESIZED_IMAGES_PATH = "src/main/resources/resized_images/";
    public static String IMAGES_FORMAT = ".png";

    public static String BUTTON_IMAGES_PATH = "button/";
    public static String CLOSED_TILES_IMAGES_PATH = "closed_tiles/";
    public static String OPENED_TILES_IMAGES_PATH = "opened_tiles/";

    public static String TIME_IMAGES_PATH = "time/";



    public static int PICTURE_WIDTH = 50;
    public static int PICTURE_HEIGHT = 50;

//    where this is saved
    public static String SETTINGS_MEMORY_PATH = "src/main/resources/settings.txt";

//    TODO add mouse listeners

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
