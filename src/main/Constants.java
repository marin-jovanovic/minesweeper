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

    public static int WIDTH = 500;
    public static int HEIGHT = 500;

//    this is for RESIZED pictures
    public static String PICTURES_PATH = "src/main/resources/resized_images/";
    public static String PICTURES_FORMAT = ".png";

//    add mouse listeners

    public static int PICTURE_WIDTH = 50;
    public static int PICTURE_HEIGHT = 50;

    public static void refresh() {

        try {
            BufferedReader file = new BufferedReader(
                    new FileReader("src/main/resources/settings.txt")
            );

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
            System.out.println("Problem refreshing constants.");
        }
    }
}
