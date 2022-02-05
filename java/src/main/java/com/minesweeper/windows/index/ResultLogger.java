package com.minesweeper.windows.index;

import com.minesweeper.resourceManagers.constants.Constant;

import java.io.*;
import java.util.ArrayList;

public class ResultLogger {

//    private static final String PATH = "statistics.txt";
    private static final String PATH = Config.getResultLogPath();

    private static final String currentDate = String.valueOf(java.time.LocalDate.now());

    public static void processResult(Result result, String time) {
//        increment result constants
//        update gui result
        if (result == Result.VICTORY) {
            Constant.NUMBER_OF_WINS.setValue((int) Constant.NUMBER_OF_WINS.getValue() + 1);
            ResultComponent.getInstance().refreshWinLabel();

        } else if (result == Result.DEFEAT) {
            Constant.NUMBER_OF_LOSSES.setValue((int) Constant.NUMBER_OF_LOSSES.getValue() + 1);
            ResultComponent.getInstance().refreshLoseLabel();

        } else {
            System.out.println("error: processResult");
        }
//        log result
        writeResult(result, time);

    }

    /**
     * appends results to file {@code PATH} in following format:
     * D;1;2021-2-15
     * <p>
     * first token is "D" or "V" as in "victory" and "defeat"
     * "E" means tie
     * <p>
     * second token:
     * time in seconds
     * <p>
     * third token:
     * date in format year-month-day
     *
     * @param result
     * @param time
     */
    private static void writeResult(Result result, String time) {

        if (!Constant.LAST_LOGGED_DAY.getValue().equals(currentDate)) {
            System.out.println("new day");
            Constant.LAST_LOGGED_DAY.setValue(currentDate);

            try (FileOutputStream fos = new FileOutputStream(PATH, true)) {
                fos.write((currentDate + "\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("day already logged");
        }

        try (FileOutputStream fos = new FileOutputStream(PATH, true)) {
            String resultCompressed;
            if (result == Result.VICTORY) {
                resultCompressed = "V";
            } else if (result == Result.DEFEAT) {
                resultCompressed = "D";
            } else {
                resultCompressed = "E";
            }

            fos.write((resultCompressed + ";" + time + "\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //    todo handle errors
    public static ArrayList<String> readResults() {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        ArrayList<String> result = new ArrayList<>();

        File f = new File(PATH);

        if (!f.exists()) {
            System.out.println("file does not exist");

            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;

        } else {

            try (FileReader fr = new FileReader(PATH);
                 BufferedReader bw = new BufferedReader(fr)) {

                String currentLine;

                while ((currentLine = bw.readLine()) != null) {
                    System.out.println(currentLine);
                    result.add(currentLine);
                }


            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }

        return result;

    }

    public static void main(String[] args) {

//        writeResult(Result.VICTORY, "12");
//        writeResult(Result.DEFEAT, "4");

        ArrayList<String> results = readResults();

        System.out.println("*** results ***");
        results.forEach(System.out::println);


    }


    public enum Result {
        VICTORY,
        DEFEAT
    }

}
