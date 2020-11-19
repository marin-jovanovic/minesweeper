package main;

import java.util.Random;

public class TableGenerator  {

    private static int[][] table = new int[Constants.NUMBER_OF_ROWS][Constants.NUMBER_OF_COLUMNS];
    private static boolean[] isRowFull = new boolean[Constants.NUMBER_OF_ROWS];


    public static void main(String[] args) {

//        generateTable();
//
//        printTable();
//
//        generateTable();
//
//        printTable();

//        generateTable();
//        printTable();
//
//        int x = 1;
//        int y = 1;
//
//        floodFill(x,y,0,9);
//
//        printTable();

    }

//    public static void floodFill(int x, int y, int target, int replacement){
////        if (target == replacement) {
////            return;
////        }
//        if (table[x][y] != target) {
//            return;
//        }
//        table[x][y] = replacement;
//        if (!(x+1 >= Constants.NUMBER_OF_ROWS))
//            floodFill(x+1, y, target, replacement);
//        if (!(x-1 < 0))
//            floodFill(x-1, y, target, replacement);
//        if (!(y+1 >= Constants.NUMBER_OF_COLUMNS))
//            floodFill(x, y+1, target, replacement);
//        if (!(y-1 < 0))
//            floodFill(x, y-1, target, replacement);
//
//        return;
//    }

    public static int[][] getTable() {
        generateTable();
        return table;
    }

    private static void generateTable() {

//        case: broj redova ili kolona je nula


//        case: more mines or equal to size of table
        if (Constants.NUMBER_OF_MINES >= Constants.NUMBER_OF_ROWS * Constants.NUMBER_OF_COLUMNS) {
            for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
                for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
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

        for (int i = 0; i < Constants.NUMBER_OF_MINES; i++) {
            int row = getRow();
            insertMineInRow(row);
            updateIsRowFull(row);
        }

        generateHints();

    }

//    generates numbers on table aka hints
    private static void generateHints() {
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {

//              not a bomb selected
                if(table[i][j] != -1) {

                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {

                            if (i+m < 0 || j+n < 0 || i+m >= Constants.NUMBER_OF_ROWS
                                    || j+n >= Constants.NUMBER_OF_COLUMNS) {
                                continue;
                            }

                            if (table[i+m][j+n] == -1) {
                                table[i][j]++;
                            }
                        }
                    }
                }
            }
        }
    }

//    private static void printIsRowFull() {
//        for (int i = 0; i < Constants.NUMBER_OF_COLUMNS; i++) {
//            System.out.println(isRowFull[i]);
//        }
//    }

//    private static boolean isSomeRowNotFull() {
//        boolean isSomeRowNotFull = false;
//        for (int i = 0; i < Constants.NUMBER_OF_COLUMNS; i++) {
//            if (!isRowFull[i]) {
//
//                isSomeRowNotFull = true;
//                break;
//            }
//        }
//
//        if (isSomeRowNotFull) {
//            System.out.println("some row is not full");
//        }
//        else {
//            System.out.println("all rows are full");
//        }
//
//        return isSomeRowNotFull;
//    }

//    sets all to boolean(false)
    private static void initializeIsRowFull() {
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            isRowFull[i] = false;
        }
    }

//    returns random empty row
    private static int getRow() {
        Random rand = new Random();
        int row = -1;

        while (true) {
//                row in which mine will be placed
            row = rand.nextInt(Constants.NUMBER_OF_ROWS);

            if (!isRowFull[row]) {
                break;
            }

        }
        return row;
    }

//    in @table in int(row) selects random empty spot
//    and places bomb -> int(-1)
    private static void insertMineInRow(int row) {

        Random rand = new Random();

        while (true) {
            int column = rand.nextInt(Constants.NUMBER_OF_COLUMNS);

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

        for (int i = 0; i < Constants.NUMBER_OF_COLUMNS; i++) {
            if (table[row][i] != -1){
                return;
            }
        }

        isRowFull[row] = true;
    }

//    formatted print of @table
    private static void printTable() {
        System.out.println("board:");
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {

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
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                table[i][j] = 0;
            }
        }
    }
}

