package main.constantModule;

//  TODO auto save setting for window size and location
//  TODO mute sound option

import java.io.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Scanner;

// todo what if default settings file is deleted

// todo handle restart settings with thread wait differently
public class ConstantsManager {

    /**
     * reads from constants.txt
     * sets constants values to values from constants instead of defined in this
     *
     * use this at the start of program to get latest constants
     *
     * handling errors:
     *  if constants.txt is modified there is rollback mechanism
     *  returns list of constants that could not be initialized from constants.txt
     *
     * if file is empty:
     *  use constants defined in this file
     *
     * if file contains modified line (two words, first word is not constant):
     *  this constant uses preassigned value from this file
     *
     * if file contains modified line (two words, second word wrong type):
     *  this constant uses preassigned value from this file
     *
     * if file contains modified line (two words, first word is not constant, second word wrong type):
     *  this constant uses preassigned value from this file
     *
     * if file contains modified line (no words (empty line), one word, multiple words):
     *  this constant uses preassigned value from this file
     */
    public static LinkedHashMap<Integer, String> initializeConstants() {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        File f = new File(Config.getConstantsMemoryPath());

        LinkedHashMap<Integer, String> error_log = new LinkedHashMap<>();

        if(!f.exists()){
            try {
                System.out.println("file does not exist");
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }else{
            System.out.println("File already exists");

            Constant[] backup_states = new Constant[Constant.getNumOfConstants()];

            int i = 0;

            for (Constant constant : EnumSet.allOf(Constant.class)) {
                backup_states[i++] = constant;
            }

            try(FileReader fr = new FileReader(Config.getConstantsMemoryPath());
                BufferedReader bw = new BufferedReader(fr)) {

                String line;

//            which token is being processed
                int index = 0;
                for (Constant constant : EnumSet.allOf(Constant.class)) {
                    System.out.println(constant);
/*

                  constants.txt contains less lines than sum of constants is

                  breaks assignment
                  constants use predefined values
                 */

                    if ((line = bw.readLine()) == null) {
                        error_log.put(index, "not enough lines from this line");
                        break;
                    }

                    index++;


                    if ((line.split(" ")).length != 2) {

                        if (line.equals("")) {
                            error_log.put(index, "empty line");
                        } else {
                            if ((line.split(" "))[0].equals(constant.getId())) {

//                                if ((line.split(" "))[0].equals(constant.id)) {

                                if (line.split(" ").length > 2) {

                                    handleValue(error_log, line, index, constant, "too much tokens");

                                } else {
                                    error_log.put(index, "not enough tokens");
                                }

                            } else {
                                error_log.put(index, "id mismatch");
                            }
                        }

                    } else {

                        handleValue(error_log, line, index, constant, "");

                    }

                    System.out.println(constant);
                    System.out.println();

                }



            } catch (IOException e) {
                System.out.println("IOException");
                System.out.println(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Exception");
                System.out.println(e.getMessage());
                printAll();

                i = 0;
                for (Constant constant : EnumSet.allOf(Constant.class)) {
                    constant = backup_states[i++];
                }

                for (Constant constant : EnumSet.allOf(Constant.class)) {
                    System.out.println(constant);
                }

            }

        }



        error_log.forEach((key, value) -> System.out.println(key + ":" + value));

        return error_log;
    }

    /**
     * Assigns new value to constants if that can be done.
     * If that can not be done; appends new error to log and does not change value (it uses predefined value).
     *
     * @param error_log (k, v) -> (index, error message)
     * @param line line in .txt
     * @param index index of constant
     * @param constant current constant that we try to assign new value
     * @param errorMessagePrefix if line in .txt contains more than 2 tokens append message, else ""
     */
    private static void handleValue(LinkedHashMap<Integer, String> error_log, String line,
                                    int index, Constant constant, String errorMessagePrefix) {

        Object value = (line.split(" "))[1];

        if (constant.getValue() instanceof Integer) {
            if (value.toString().matches("[1-9][0-9]*")) {
                constant.setValue(Integer.parseInt(String.valueOf(value)));

                if (! errorMessagePrefix.equals("")) {
                    error_log.put(index, errorMessagePrefix);
                }

            } else {
                error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                        "error with integer " + value);
            }
        } else if (constant.getValue() instanceof Double) {
            if (value.toString().matches("[0-9]+\\.[0-9]+")) {
                constant.setValue(Double.parseDouble(String.valueOf(value)));
                if (! errorMessagePrefix.equals("")) {
                    error_log.put(index, errorMessagePrefix);
                }

            } else {
                error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                        "error with double " + value);
            }
        } else if (constant.getValue() instanceof Boolean) {

            if (value.toString().equals("true") || value.toString().equals("false")) {
                constant.setValue(Boolean.parseBoolean(String.valueOf(value)));

                if (! errorMessagePrefix.equals("")) {
                    error_log.put(index, errorMessagePrefix);
                }

            } else {
                error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                        "error with boolean " + value);
            }

        } else if (constant.getValue() instanceof String) {
            constant.setValue(value);

            if (! errorMessagePrefix.equals("")) {
                error_log.put(index, errorMessagePrefix);
            }

        } else {
            error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                    "error while paring value, constant.value error, " + value);
        }
    }

    /**
     * prints all constants
     */
    public static void printAll() {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        for (Constant constant : EnumSet.allOf(Constant.class)) {
            System.out.println(constant);
        }

        System.out.println();
    }

    /**
     * writes to path
     * writes constant values to constants.txt
     *
     * use this at the end of program to save new settings
     */
    public static void updateConstants(String path) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {

                for(Constant constant : EnumSet.allOf(Constant.class)) {
                    out.println(constant.getId() + " " + constant.getValue());
                }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * sets to default constants
     *
     *
     */
    public static void restartConstants() {
        ArrayList<String> lines = new ArrayList<>();

        File f = new File(Config.getDefaultConstantsMemoryPath());

        if (!f.exists()) {
            try {
                f.createNewFile();

//                copy current to default and custom
                updateConstants(Config.getDefaultConstantsMemoryPath());
                updateConstants(Config.getConstantsMemoryPath());

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

//            read from default
            try (Scanner myReader = new Scanner(f)) {
                while (myReader.hasNextLine()) {
                    lines.add(myReader.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

//            copy from default to custom
            try (FileOutputStream fileOutputStream = new FileOutputStream(Config.getConstantsMemoryPath())) {
                fileOutputStream.write(String.join("\n", lines).getBytes());
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        }
    }

}
