package main.utils.minesweeperDrivers;

import main.constantModule.Constant;

import java.util.Random;

public class TableGenerator {

    private static int[][] table;
    private static boolean[] isRowFull;

    public static int[][] getTable() {
        generateTable();

        printTable();

        return table;
    }

    private static void generateTable() {

        table = new int[(int) Constant.NUMBER_OF_ROWS.getValue()][(int) Constant.NUMBER_OF_COLUMNS.getValue()];
        isRowFull = new boolean[(int) Constant.NUMBER_OF_ROWS.getValue()];
//        case: row number of column number == 0


//        case: more mines or equal to size of table
        if ((int) Constant.NUMBER_OF_MINES.getValue() >=
                (int) Constant.NUMBER_OF_ROWS.getValue() * (int) Constant.NUMBER_OF_COLUMNS.getValue()) {


//        if (ConstantsManager.NUMBER_OF_MINES >= ConstantsManager.NUMBER_OF_ROWS * ConstantsManager.NUMBER_OF_COLUMNS) {
            for (int i = 0; i < (Integer) Constant.NUMBER_OF_ROWS.getValue(); i++) {
                for (int j = 0; j < (Integer) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                    table[i][j] = -1;
                }
            }

            return;
        }

//        case: num of mines < num of empty spots

//        case: num of mines > num of empty spots


//        set all to 0
        initializeTable();

//        set all to false
        initializeIsRowFull();

//        printTable();

        for (int i = 0; i < (Integer) Constant.NUMBER_OF_MINES.getValue(); i++) {
            int row = getRow();
            insertMineInRow(row);
            updateIsRowFull(row);
        }

        generateHints();

    }

    //    generates numbers on table aka hints
    private static void generateHints() {
        for (int i = 0; i < (Integer) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (Integer) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {

//              not a bomb selected
                if (table[i][j] != -1) {

                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {

                            if (i + m < 0 || j + n < 0 || i + m >= (Integer) Constant.NUMBER_OF_ROWS.getValue()
                                    || j + n >= (Integer) Constant.NUMBER_OF_COLUMNS.getValue()) {
                                continue;
                            }

                            if (table[i + m][j + n] == -1) {
                                table[i][j]++;
                            }
                        }
                    }
                }
            }
        }
    }

    //    sets all to boolean(false)
    private static void initializeIsRowFull() {
        for (int i = 0; i < (Integer) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            isRowFull[i] = false;
        }
    }

    //    returns random empty row
    private static int getRow() {
        Random rand = new Random();
        int row = -1;

        do {
//                row in which mine will be placed
            row = rand.nextInt((Integer) Constant.NUMBER_OF_ROWS.getValue());

        } while (isRowFull[row]);

        return row;
    }

    //    in @table in int(row) selects random empty spot
//    and places bomb -> int(-1)
    private static void insertMineInRow(int row) {

        Random rand = new Random();

        while (true) {
            int column = rand.nextInt((Integer) Constant.NUMBER_OF_COLUMNS.getValue());

            if (table[row][column] != -1) {
                table[row][column] = -1;
                break;
            }
        }
    }

    //    checks if int(row) in @table contains empty spot
//    if true: pass
//    if false: isRowFull[row] = true
    private static void updateIsRowFull(int row) {

        for (int i = 0; i < (Integer) Constant.NUMBER_OF_COLUMNS.getValue(); i++) {
            if (table[row][i] != -1) {
                return;
            }
        }

        isRowFull[row] = true;
    }

    //    formatted print of @table
    private static void printTable() {
        System.out.println("board:");
        for (int i = 0; i < (Integer) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (Integer) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {

//                mark for bomb
                if (table[i][j] == -1) {
                    System.out.print("_ ");
                }

//                not bomb
                else {
                    System.out.print(table[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //    set all to int(0) in @table
    private static void initializeTable() {
        for (int i = 0; i < (Integer) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (Integer) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                table[i][j] = 0;
            }
        }
    }
}

