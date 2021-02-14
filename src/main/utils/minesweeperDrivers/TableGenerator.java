package main.utils.minesweeperDrivers;

import main.constantsModule.Constant;

import java.util.Random;

public class TableGenerator {

    private enum CellStatus {
        MINE,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT;


    }



    private static int[][] table;
    private static boolean[] isRowFull;

    public static void main(String[] args) {
        generateTable();

        printTable();

    }

    private static int getNumberOfRows() {
        return (int) Constant.NUMBER_OF_ROWS.getValue();
    }

    private static int getNumberOfColumns() {
        return (int) Constant.NUMBER_OF_COLUMNS.getValue();
    }

    private static int getNumberOfMines() {
        return (int) Constant.NUMBER_OF_MINES.getValue();
    }

    public static int[][] getTable() {
        generateTable();

        printTable();

        return table;
    }

    private static void generateTable() {

        table = new int[getNumberOfRows()][getNumberOfColumns()];
        isRowFull = new boolean[getNumberOfRows()];

//
//        case: more mines or equal to size of table
        if (getNumberOfMines() >= getNumberOfRows() * getNumberOfColumns()) {

            for (int i = 0; i < getNumberOfRows(); i++) {
                for (int j = 0; j < getNumberOfColumns(); j++) {
                    table[i][j] = -1;
                }
            }

            return;
        }

//        set all to 0
        initializeTable();

//        set all to false
        initializeIsRowFull();

        for (int i = 0; i < getNumberOfMines(); i++) {
            int row = getRow();
            insertMineInRow(row);
            updateIsRowFull(row);
        }

        generateHints();

    }

    //    generates numbers on table aka hints
    private static void generateHints() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {

//              not a bomb selected
                if (table[i][j] != -1) {

                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {

                            if (i + m < 0 || j + n < 0 || i + m >= getNumberOfRows()
                                    || j + n >= getNumberOfColumns()) {
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
        for (int i = 0; i < getNumberOfRows(); i++) {
            isRowFull[i] = false;
        }
    }

    //    returns random empty row
    private static int getRow() {
        Random rand = new Random();
//        int row = -1;
        int row;
        do {
//                row in which mine will be placed
            row = rand.nextInt(getNumberOfRows());

        } while (isRowFull[row]);

        return row;
    }

    //    in @table in int(row) selects random empty spot
//    and places bomb -> int(-1)
    private static void insertMineInRow(int row) {

        Random rand = new Random();

        while (true) {
            int column = rand.nextInt(getNumberOfColumns());

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

        for (int i = 0; i < getNumberOfColumns(); i++) {
            if (table[row][i] != -1) {
                return;
            }
        }

        isRowFull[row] = true;
    }

    //    formatted print of @table
    private static void printTable() {
        System.out.println("board:");
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {

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
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                table[i][j] = 0;
            }
        }
    }
}

