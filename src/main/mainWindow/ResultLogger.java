package main.mainWindow;

import main.constantsModule.Constant;

import java.io.*;
import java.util.ArrayList;

public class ResultLogger {

    public enum Result {
        VICTORY,
        DEFEAT;
    };

    private static final String PATH = "statistics.txt";
    // todo result constants

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

        try (FileOutputStream fos = new FileOutputStream(PATH, true)) {
            fos.write((result + ";" + time + "\n").getBytes());
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

            int i = 0;

            try (FileReader fr = new FileReader(PATH);
                 BufferedReader bw = new BufferedReader(fr)) {

                String currentLine;

                while ((currentLine = bw.readLine()) != null) {
                    System.out.println(currentLine);
                    result.add(currentLine);
                }


            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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







    public ResultLogger() {

    }

}
