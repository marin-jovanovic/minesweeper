package main.windows.index;

import main.resourceManagers.constants.Constant;

import java.io.*;
import java.util.ArrayList;

public class ResultLogger {

    private static final String PATH = "statistics.txt";

    private static final String currentDate = String.valueOf(java.time.LocalDate.now());

    private static boolean isFirstEntry = true;

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

    private static void writeResult(Result result, String time) {

        if (isFirstEntry) {
            isFirstEntry = false;

            try (FileOutputStream fos = new FileOutputStream(PATH, true)) {
                fos.write((java.time.LocalDate.now() + "\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        writeResult(Result.VICTORY, "12");
        writeResult(Result.DEFEAT, "4");

        ArrayList<String> results = readResults();

        System.out.println("*** results ***");
        results.forEach(System.out::println);


    }


    public enum Result {
        VICTORY,
        DEFEAT
    }

}
