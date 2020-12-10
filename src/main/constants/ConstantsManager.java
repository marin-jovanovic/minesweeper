package main.constants;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConstantsManager {

    public static Boolean CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN = false;

    //    where this is saved
    public static String SETTINGS_MEMORY_PATH = "src/main/resources/settings_logs/settings.txt";
    public static String DEFAULT_SETTINGS_MEMORY_PATH = "src/main/resources/settings_logs/defaultSettings.txt";

    public static void refresh() {
        System.out.println("refreshing constants");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SETTINGS_MEMORY_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("reading line: " + line);
                String[] raw_data = line.split(" = ");
                String key = raw_data[0];
                String value = raw_data[1];
                System.out.println(key);
                System.out.println(value);

                Constant.getConstant(key).setValue(Integer.parseInt(value));
            }
            System.out.println("end of file");
            System.out.println("printing constants");
            Constant.printAll();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

//    TODO initialize

    /**
     *
     * @param args
     */
//    public static void initializeVariables() {
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SETTINGS_MEMORY_PATH))) {
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//
////                raw_data[0] = key
////                raw_data[1] = value
//                String[] raw_data = line.split(" = ");
//
//
////                GeneralConstant generalConstant = GeneralConstant.getConstant(raw_data[0]);
////                generalConstant.setValue(Integer.parseInt(raw_data[1]));
//
//                //                String key = raw_data[0];
////                String value = raw_data[1];
//
//
//
//
//
////                switch (key) {
////                    case GeneralConstant.NUMBER_OF_COLUMNS.getSettingsText() ->
////                            GeneralConstant.NUMBER_OF_COLUMNS.setValue(value);
////
////                }
////
////                if (key.equals(mineEnum)) {
////                    NUMBER_OF_MINES = Integer.parseInt(value);
////                }
//
//            }
//            System.out.println("end of file");
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }


//  TODO auto save setting for window size and location
    public static void main(String[] args) {
//        initializeVariables();
//        System.out.println(NUMBER_OF_COLUMNS);
    }

    public static final String mineEnum = "mine number:";
}
