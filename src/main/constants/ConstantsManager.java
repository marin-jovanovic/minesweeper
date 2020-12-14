package main.constants;

import java.io.BufferedReader;
import java.io.FileReader;

//    TODO initialize
//  TODO auto save setting for window size and location

//  TODO mute sound option


public class ConstantsManager {

    public static Boolean CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN = false;

    //    where this is saved
    public static String SETTINGS_MEMORY_PATH = "src/main/resources/settings_logs/settings.txt";
    public static String DEFAULT_SETTINGS_MEMORY_PATH = "src/main/resources/settings_logs/defaultSettings.txt";

    public static void refresh() {
        System.out.println("ConstantsManager: refresh");

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
}
