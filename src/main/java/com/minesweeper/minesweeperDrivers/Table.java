package com.minesweeper.minesweeperDrivers;

import java.util.Random;

/**
 * generates table with mines and hints
 */
public class Table {

//    todo open cells correctly while under flag or unknown
//    todo optimise openings
//    todo make solver
//    todo hints

    private final Cell[][] table;
    private final boolean[] isRowFull;

    private final int numberOfRows;
    private final int numberOfColumns;
    private final int numberOfMines;

    //  fixme edge cases
    public Table(int numberOfRows, int numberOfColumns, int numberOfMines, int userX, int userY) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfMines = numberOfMines;

        isRowFull = new boolean[numberOfRows];

//       initialization
        table = new Cell[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j] = new Cell();
            }
        }

        if (numberOfMines >= numberOfRows * numberOfColumns) {
//        case: more mines or equal to size of table

            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    table[i][j].setCellStatus(Cell.CellStatus.MINE);
                }
            }

        } else if (numberOfMines + 1 == numberOfRows * numberOfColumns) {
//            open only user selected cell
//            place mines on all other cells

        } else {
            //        all cells in table set to {CellStatus.ZERO}
            setAllCellsToZero();
            table[userX][userY].setCellStatus(Cell.CellStatus.USER);

            int numOfPlacedMines = 0;

            Random rand = new Random();

            this.printTable();

            while (numOfPlacedMines != numberOfMines) {
                int row = rand.nextInt(numberOfRows);
                int column = rand.nextInt(numberOfColumns);


                if (table[row][column].getCellStatus() != Cell.CellStatus.MINE &&
                        table[row][column].getCellStatus() != Cell.CellStatus.USER) {

                    table[row][column].setCellStatus(Cell.CellStatus.MINE);
                    numOfPlacedMines++;

                }

            }

        }


        table[userX][userY].setCellStatus(Cell.CellStatus.ZERO);

    }

    /**
     * generates randomly and does not care what is being pressed first
     *
     * @param numberOfRows
     * @param numberOfColumns
     * @param numberOfMines
     */
    public Table(int numberOfRows, int numberOfColumns, int numberOfMines) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfMines = numberOfMines;

        table = new Cell[numberOfRows][numberOfColumns];
        isRowFull = new boolean[numberOfRows];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j] = new Cell();
            }
        }

//        case: more mines or equal to size of table
        if (numberOfMines >= numberOfRows * numberOfColumns) {

            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    table[i][j].setCellStatus(Cell.CellStatus.MINE);
                }
            }

        } else {
            //        all cells in table set to {CellStatus.ZERO}
            setAllCellsToZero();
//        sets all isRowFull to false
            initializeIsRowFull();

            for (int i = 0; i < numberOfMines; i++) {
                int row = getRow();
                insertMineInRow(row);
                updateIsRowFull(row);
            }

            generateHints();

        }
    }

    public static void main(String[] args) {
        Table t = new Table(10, 10, 3);
        t = new Table(10, 10, 30, 2, 2);

        t.printTable();

        System.out.println(t.isSolvable());
    }

    public Cell getCell(int x, int y) {
        return table[x][y];
    }

    private boolean isSolvable() {
        return false;
    }

    /**
     * generates numbers on table
     */
    private void generateHints() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                if (table[i][j].getCellStatus() != Cell.CellStatus.MINE) {

                    int sum = 0;

                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {

                            if (i + m < 0 || j + n < 0 || i + m >= numberOfRows
                                    || j + n >= numberOfColumns) {
                                continue;
                            }

                            if (table[i + m][j + n].getCellStatus() == Cell.CellStatus.MINE) {
                                sum++;
                            }

                        }
                    }

                    switch (sum) {
                        case 0 -> table[i][j].setCellStatus(Cell.CellStatus.ZERO);
                        case 1 -> table[i][j].setCellStatus(Cell.CellStatus.ONE);
                        case 2 -> table[i][j].setCellStatus(Cell.CellStatus.TWO);
                        case 3 -> table[i][j].setCellStatus(Cell.CellStatus.THREE);
                        case 4 -> table[i][j].setCellStatus(Cell.CellStatus.FOUR);
                        case 5 -> table[i][j].setCellStatus(Cell.CellStatus.FIVE);
                        case 6 -> table[i][j].setCellStatus(Cell.CellStatus.SIX);
                        case 7 -> table[i][j].setCellStatus(Cell.CellStatus.SEVEN);
                        case 8 -> table[i][j].setCellStatus(Cell.CellStatus.EIGHT);
                    }

                }

            }
        }
    }

    /**
     * sets all values to false
     */
    private void initializeIsRowFull() {
        for (int i = 0; i < numberOfRows; i++) {
            isRowFull[i] = false;
        }
    }

    /**
     * gets random non-full row
     *
     * @return random non-full row
     */
    private int getRow() {
        Random rand = new Random();

        int row;
        do {
//                row in which mine will be placed
            row = rand.nextInt(numberOfRows);

        } while (isRowFull[row]);

        return row;
    }

    /**
     * inserts mine in {@code row} at random position (checks if position is occupied)
     *
     * @param row row in which mine is to be inserted
     */
    private void insertMineInRow(int row) {

        Random rand = new Random();

        while (true) {
            int column = rand.nextInt(numberOfColumns);

            if (table[row][column].getCellStatus() != Cell.CellStatus.MINE) {
                table[row][column].setCellStatus(Cell.CellStatus.MINE);
                break;
            }
        }
    }

    /**
     * if row contains only mines sets {@code isRowFull[row]} to true,
     * else returns
     *
     * @param row row which is being checked
     */
    private void updateIsRowFull(int row) {

        for (int i = 0; i < numberOfColumns; i++) {
            if (table[row][i].getCellStatus() != Cell.CellStatus.MINE) {
                return;
            }
        }

        isRowFull[row] = true;
    }

    /**
     * formatted print of {@code table}
     */
    private void printTable() {
        System.out.println("table:");

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(table[i][j].getCellStatus() + " ");
            }

            System.out.print(", ");

            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(table[i][j].getCellModifier() + " ");
            }

            System.out.println();
        }
        System.out.println();
    }

    /**
     * sets all cells in {@code table} to {@code CellStatus.ZERO}
     */
    private void setAllCellsToZero() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j].setCellStatus(Cell.CellStatus.ZERO);
            }
        }
    }
}

