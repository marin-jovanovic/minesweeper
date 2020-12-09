package main.constants;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConstantsManager {

    public static Boolean CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN = false;

//    public static int NUMBER_OF_COLUMNS = GeneralConstant.NUMBER_OF_COLUMNS.getValue();
    public static int NUMBER_OF_COLUMNS =10;
    public static int NUMBER_OF_ROWS = 10;
    public static int NUMBER_OF_MINES = 15;

    //    where this is saved
    public static String SETTINGS_MEMORY_PATH = "src/main/resources/settings.txt";
    public static String DEFAULT_SETTINGS_MEMORY_PATH = "src/main/resources/defaultSettings.txt";

    public static void refresh() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SETTINGS_MEMORY_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] raw_data = line.split(" = ");
                String key = raw_data[0];
                String value = raw_data[1];

                if (key.equals(mineEnum)) {
                    NUMBER_OF_MINES = Integer.parseInt(value);
                }

            }
            System.out.println("end of file");

        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    public static void initializeVariables() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SETTINGS_MEMORY_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);

//                raw_data[0] = key
//                raw_data[1] = value
                String[] raw_data = line.split(" = ");


                GeneralConstant generalConstant = GeneralConstant.getConstant(raw_data[0]);
                generalConstant.setValue(Integer.parseInt(raw_data[1]));

                //                String key = raw_data[0];
//                String value = raw_data[1];





//                switch (key) {
//                    case GeneralConstant.NUMBER_OF_COLUMNS.getSettingsText() ->
//                            GeneralConstant.NUMBER_OF_COLUMNS.setValue(value);
//
//                }
//
//                if (key.equals(mineEnum)) {
//                    NUMBER_OF_MINES = Integer.parseInt(value);
//                }

            }
            System.out.println("end of file");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


//  TODO auto save setting for window size and location
    public static void main(String[] args) {
        initializeVariables();
        System.out.println(NUMBER_OF_COLUMNS);
    }

    public static final String mineEnum = "mine number:";
}
