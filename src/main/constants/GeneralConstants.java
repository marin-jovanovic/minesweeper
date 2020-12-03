package main.constants;

import main.constants.imageDrivers.ImagesConstants;

import java.io.BufferedReader;
import java.io.FileReader;

public class GeneralConstants {

    public static Boolean CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN = false;

    public static int NUMBER_OF_COLUMNS =10;
    public static int NUMBER_OF_ROWS = 10;
    public static int NUMBER_OF_MINES = 15;

    //    where this is saved
    public static String SETTINGS_MEMORY_PATH = "src/main/resources/settings.txt";
    public static String DEFAULT_SETTINGS_MEMORY_PATH = "src/main/resources/defaultSettings.txt";

    public static void refresh() {

        try {
            BufferedReader file = new BufferedReader(new FileReader(SETTINGS_MEMORY_PATH));

            NUMBER_OF_ROWS = Integer.parseInt(((file.readLine().split(" "))[4]));
            NUMBER_OF_COLUMNS = Integer.parseInt(((file.readLine().split(" "))[4]));
            NUMBER_OF_MINES = Integer.parseInt(((file.readLine().split(" "))[4]));

            LayoutConstants.WIDTH = NUMBER_OF_COLUMNS * ImagesConstants.PICTURE_WIDTH;
            LayoutConstants.HEIGHT = NUMBER_OF_ROWS * ImagesConstants.PICTURE_HEIGHT;

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
