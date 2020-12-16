package main.ConstantModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.EnumSet;

//    TODO initialize
//  TODO auto save setting for window size and location

//  TODO mute sound option


public class ConstantsManager {

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static void refresh() {
        System.out.println("ConstantsManager: refresh");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Config.SETTINGS_MEMORY_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("reading line: " + line);
                String[] raw_data = line.split(" = ");
                String key = raw_data[0];
                String value = raw_data[1];
                System.out.println(key);
                System.out.println(value);

                if (isNumeric(value)) {
                    System.out.println("int");
                    Constant.getConstant(key).setValue(Integer.parseInt(String.valueOf(value)));
//                    return ;
                } else if (value instanceof String) {
                    System.out.println("str");
                    Constant.getConstant(key).setValue(value);
//                    return String.valueOf(value);
                } else if (value.equals("true") || value.equals("false")) {
                    System.out.println("bool");
                    Constant.getConstant(key).setValue(Boolean.valueOf(String.valueOf(value)));
//                    return ;
                } else {
                    Constant.getConstant(key).setValue(value);
                }

//
            }
            System.out.println("end of file");
            System.out.println("printing constants");
            printAll();


        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void printAll() {
        System.out.println("Constant: printAll");

        for (Constant constant : EnumSet.allOf(Constant.class)) {
            System.out.println(constant + " -> " + constant.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        refresh();
    }

}
