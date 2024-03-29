package com.minesweeper.minesweeperDrivers;

import java.util.*;

/**
 * generates table with mines and hints
 */
public class Table {

//    todo open cells correctly while under flag or unknown
//    todo make solver
//    todo hints

    private final Cell[][] table;

    private final int numberOfRows;
    private final int numberOfColumns;

    private final int numberOfMines;


//  users opened this tile
    private final int userX;
    private final int userY;

    //  fixme edge cases

    /**
     *
     * place mines on board and respect users clicked tile
     * see if game is solvable
     *
     * @param numberOfRows
     * @param numberOfColumns
     * @param numberOfMines
     * @param userX first pressed cell by user
     * @param userY first pressed cell by user
     */
    public Table(
        int numberOfRows,
        int numberOfColumns,
        int numberOfMines,
        int userX,
        int userY
    ) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfMines = numberOfMines;

        this.userX = userX;
        this.userY = userY;

//       initialization
        table = new Cell[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j] = new Cell();
            }
        }

        placeMines(numberOfRows, numberOfColumns, numberOfMines, userX, userY);

        generateHints();

//        visibility

        openBlanks(userX, userY);
    }

    /**
     * generates randomly and does not care what is being pressed first
     *
     * @param numberOfRows
     * @param numberOfColumns
     * @param numberOfMines
     */
    public Table(int numberOfRows, int numberOfColumns, int numberOfMines) {

        this(numberOfRows, numberOfColumns, numberOfMines, 0, 0);
    }

    //    opens all blank that are NEWS, ne, ns, ...,  of targeted cell
    public void openBlanks(int x, int y) {

        Cell cell = table[x][y];

        if (cell.getCellStatus() == Cell.CellStatus.COVERED) {
            cell.setCellStatus(Cell.CellStatus.OPENED);
        } else {
//           handles:
//            if opened
//            if under flag or questionmark
            return;
        }

        if (cell.getCellValue() == Cell.CellValue.ZERO) {
            //        table[x][y] already opened but first if statement in openCell handles that
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {

//                skip cases where "i + m" or "j + n" are out of board
                    if (i + x < 0 || j + y < 0 || i + x >= numberOfRows
                            || j + y >= numberOfColumns) {
                        continue;
                    }

                    openBlanks(x + i, y + j);
                }
            }
        }

    }

    public void openCell(int x, int y) {

    }

    public static void main(String[] args) {

        Table t = new Table(5, 5, 12, 2, 2);

        t.printTable();

        printNumOfMines(t);

    }


    public Cell getCell(int x, int y) {
        return table[x][y];
    }




    /**
     * generates mines on the table, used for initialization
     *
     * @param numberOfRows
     * @param numberOfColumns
     * @param numberOfMines
     * @param userX
     * @param userY
     */
    private void placeMines(int numberOfRows, int numberOfColumns, int numberOfMines, int userX, int userY) {
        if (numberOfMines >= numberOfRows * numberOfColumns) {
//        case: more mines or equal to size of table

            System.out.println("mine overflow");

            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    table[i][j].setCellValue(Cell.CellValue.MINE);
                }
            }

        } else if (numberOfMines + 1 == numberOfRows * numberOfColumns) {
//        case: only one empty cell, all other cells are mines

//            open only user selected cell
//            place mines on all other cells

            System.out.println("only one spot empty");

            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    table[i][j].setCellValue(Cell.CellValue.MINE);
                }
            }

            table[userX][userY].setCellStatus(Cell.CellValue.USER);


        } else {

            if (numberOfMines > numberOfRows * numberOfColumns - numberOfMines) {
                System.out.println("more empty");
            } else {
                System.out.println("more mines");
            }

            System.out.println("mines " + numberOfMines);

//        all cells in table set to {CellStatus.ZERO}

//            set value
            setAllCells(Cell.CellValue.ZERO);

            int numOfPlacedMines = 0;

            Random rand = new Random();

            while (numOfPlacedMines != numberOfMines) {
                int row = rand.nextInt(numberOfRows);
                int column = rand.nextInt(numberOfColumns);

                boolean isUsersCellOrSurroundingCell = false;

                for (int m = -1; m < 2; m++) {
                    for (int n = -1; n < 2; n++) {

                        if (row == userX + m && column == userY + n) {
//                            it is this cell or around this cell
                            isUsersCellOrSurroundingCell = true;
                            break;
                        }

                    }
                }

                if (
//                        skip user selected cell
//                        !(row == userX && column == userY) &&
                        !isUsersCellOrSurroundingCell &&
                                table[row][column].getCellValue() != Cell.CellValue.MINE
                ) {

                    table[row][column].getCellValue(Cell.CellValue.MINE);
                    numOfPlacedMines++;

                }

            }

        }
    }


    /**
     * generates numbers on table based on position of mines
     *
     * algorithm iterates over cells in board and counts for current cell
     * how many surrounding cells are mines
     *
     * that count is then written on that cell
     */
    private void generateHints() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                if (table[i][j].getCellValue() != Cell.CellValue.MINE) {

                    int sum = 0;

//                    iterate over all surrounding cells
                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {

//                            skip cases where "i + m" or "j + n" are out of board
                            if (i + m < 0 || j + n < 0 || i + m >= numberOfRows
                                    || j + n >= numberOfColumns) {
                                continue;
                            }

//                            this cell is mine
                            if (table[i + m][j + n].getCellValue() == Cell.CellValue.MINE) {
                                sum++;
                            }

                        }
                    }

                    table[i][j].getCellValue(
                            Cell.CellValue.getBasedOnInt(sum)
                    );

                }

            }
        }
    }


    private static void printNumOfMines(Table t) {
        int mineCount = 0;
        for (int i = 0; i < t.numberOfRows; i++) {
            for (int j = 0; j < t.numberOfColumns; j++) {
                if (t.table[i][j].getCellValue() == Cell.CellValue.MINE) {
                    mineCount++;
                }
            }
        }

        System.out.println("num of mines " + mineCount);
    }


    /**
     * formatted print of {@code table}
     */
    private void printTable() {
        System.out.println("table:");

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(table[i][j].getCellValue() + " ");
            }

            System.out.print(", ");

            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(table[i][j].getCellStatus() + " ");
            }

            System.out.println();
        }
        System.out.println();
    }


    /**
     * sets all cells in {@code table}
     * @param cellValue
     */
    private void setAllCells(Cell.CellValue cellValue) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j].setCellValue(cellValue);
            }
        }
    }
}

