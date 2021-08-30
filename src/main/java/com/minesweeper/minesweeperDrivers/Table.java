package com.minesweeper.minesweeperDrivers;

import java.util.*;

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
    public Table(int numberOfRows, int numberOfColumns, int numberOfMines, int userX, int userY) {
        this.numberOfRows = numberOfRows;

        this.numberOfColumns = numberOfColumns;
        this.numberOfMines = numberOfMines;

        isRowFull = new boolean[numberOfRows];

        this.userX = userX;
        this.userY = userY;

//       initialization
        table = new Cell[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j] = new Cell();
            }
        }

//        mine placing

        if (numberOfMines >= numberOfRows * numberOfColumns) {
//        case: more mines or equal to size of table

            System.out.println("mine overflow");

            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    table[i][j].setCellStatus(Cell.CellStatus.MINE);
                }
            }

        } else if (numberOfMines + 1 == numberOfRows * numberOfColumns) {

            System.out.println("only one spot empty");

            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    table[i][j].setCellStatus(Cell.CellStatus.MINE);
                }
            }

            table[userX][userY].setCellStatus(Cell.CellStatus.USER);

//            open only user selected cell
//            place mines on all other cells

        } else {

            if (numberOfMines > numberOfRows * numberOfColumns - numberOfMines) {
                System.out.println("more empty");
            } else {
                System.out.println("more mines");
            }

            System.out.println("mines " + numberOfMines);

//        all cells in table set to {CellStatus.ZERO}

//            set value
            setAllCells(Cell.CellStatus.ZERO);

            int numOfPlacedMines = 0;

            Random rand = new Random();

//            this.printTable();

            while (numOfPlacedMines != numberOfMines) {
                int row = rand.nextInt(numberOfRows);
                int column = rand.nextInt(numberOfColumns);

                boolean isUsersCellOrSurroundingCell = false;
                int i = userX;
                int j = userY;
                for (int m = -1; m < 2; m++) {
                    for (int n = -1; n < 2; n++) {

                        if (row == i + m && column == j + n) {
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
                        table[row][column].getCellStatus() != Cell.CellStatus.MINE
                ) {

                    table[row][column].setCellStatus(Cell.CellStatus.MINE);
                    numOfPlacedMines++;

                }

            }

        }

        generateHints();

//        visibility

        openBlanks(userX, userY);
    }

    //    opens all blank that are NEWS, ne, ns, ...,  of targeted cell
    public void openBlanks(int x, int y) {

        Cell cell = table[x][y];

        if (cell.getCellModifier() == Cell.CellVisibility.COVERED) {
            cell.setCellModifier(Cell.CellVisibility.OPENED);
        } else {
//           handles:
//            if opened
//            if under flag or questionmark
            return;
        }

        if (cell.getCellStatus() == Cell.CellStatus.ZERO) {
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

    public static void main(String[] args) {
//        Table t = new Table(10, 10, 3);
        Table t = new Table(5, 5, 12, 2, 2);

        t.printTable();

        printNumOfMines(t);

//        System.out.println(t.isSolvable());

    }

    private static void printNumOfMines(Table t) {
        int mineCount = 0;
        for (int i = 0; i < t.numberOfRows; i++) {
            for (int j = 0; j < t.numberOfColumns; j++) {
                if (t.table[i][j].getCellStatus() == Cell.CellStatus.MINE) {
                    mineCount++;
                }
            }
        }

        System.out.println("num of mines " + mineCount);
    }

    public Cell getCell(int x, int y) {
        return table[x][y];
    }

    private class TableCellTuple {

        private int x,y;
        public TableCellTuple(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "TableCellTuple{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private void isSolvableDriver(int x, int y) {

        TableCellTuple thisCell = new TableCellTuple(x,y);

//        iterate over all surrounding cells
        for (int m = -1; m < 2; m++) {
            for (int n = -1; n < 2; n++) {

                Cell current = solvingTable[x + m][y + n];

//                current cell
                if (m == 0 && n == 0) {
                    current.setCellModifier(Cell.CellVisibility.OPENED);
                    current.setCellStatus(table[x+m][y+n].getCellStatus());

                    continue;
                }

//                skip cases where "i + m" or "j + n" are out of board
                if (x + m < 0 || y + n < 0 || x + m >= numberOfRows
                        || y + n >= numberOfColumns) {
                    continue;
                }

//                if (cc.getCellModifier() != Cell.CellVisibility.OPENED) {
//                    continue;
//                }

//                TableCellTuple currentCell = new TableCellTuple(i + m, j + n);

                current.setCellModifier(Cell.CellVisibility.OPENED);
                current.setCellStatus(table[x+m][y+n].getCellStatus());

//                if (table[x+m][y+n])

//
//                if (cc.getCellStatus() == Cell.CellStatus.MINE) {
////                    this cell is mine
//                    setOfFoundMines.add(currentCell);
//                    this.howManyHaveIFound++;
//                } else {
//                    this.setOfToProcess.add(currentCell);
//                }

            }
        }

//        setOfOpened.add(thisCell);
//
//        System.out.println("around this is " + this.setOfFoundMines.size());
//        System.out.println("mines");
//        this.setOfFoundMines.forEach(cell -> System.out.println(cell));
//        System.out.println("to process");
//        this.setOfToProcess.forEach(cell -> System.out.println(cell));
//        System.out.println("processed");
//        this.setOfOpened.forEach(cell -> System.out.println(cell));


//        System.out.println("solving table:");
//
//        for (int i = 0; i < numberOfRows; i++) {
//            for (int j = 0; j < numberOfColumns; j++) {
//                System.out.print(solvingTable[i][j].getCellStatus() + " ");
//            }
//
//            System.out.print(", ");
//
//            for (int j = 0; j < numberOfColumns; j++) {
//                System.out.print(solvingTable[i][j].getCellModifier() + " ");
//            }
//
//            System.out.println();
//        }
//        System.out.println();
    }

    private Set<TableCellTuple> setOfFoundMines;
    private Set<TableCellTuple> setOfOpened;
    private Set<TableCellTuple> setOfToProcess;
    private int howManyHaveIFound;
    private Cell[][] solvingTable;


    private Set<TableCellTuple> setOfVisibleTiles;

    private boolean isSolvable() {

//        this.setOfVisibleTiles = new HashSet<>();
//        this.setOfVisibleTiles.add(
//                new TableCellTuple(this.userX,
//                        this.userY)
//        );

//        this.setOfFoundMines = new HashSet<>();
//        this.setOfOpened = new HashSet<>();
//        this.setOfToProcess = new HashSet<>();

//        this.howManyHaveIFound = 0;

//        int i = userX;
//        int j = userY;

        solvingTable = new Cell[numberOfRows][numberOfColumns];

        for (int i = 0; i < this.numberOfRows; i++) {
            for (int j = 0; j < this.numberOfColumns; j++) {
                solvingTable[i][j] = new Cell();
                solvingTable[i][j].setCellModifier(Cell.CellVisibility.COVERED);
                solvingTable[i][j].setCellStatus(Cell.CellStatus.UNKNOWN);

//                if (this.table[i][j].getCellModifier() == Cell.CellVisibility.OPENED) {
//                    solvingTable[i][j] = new Cell();
//                    solvingTable[i][j].setCellModifier(Cell.CellVisibility.OPENED);
//                    solvingTable[i][j].setCellStatus(this.table[i][j].getCellStatus());
//                } else {
//                    solvingTable[i][j] = new Cell();
//                    solvingTable[i][j].setCellModifier(Cell.CellVisibility.COVERED);
//                    solvingTable[i][j].setCellStatus(Cell.CellStatus.UNKNOWN);
//                }
            }
        }


        this.isSolvableDriver(userX, userY);

        System.out.println("solving table:");

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(solvingTable[i][j].getCellStatus() + " ");
            }

            System.out.print(", ");

            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(solvingTable[i][j].getCellModifier() + " ");
            }

            System.out.println();
        }
        System.out.println();


        return false;
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

                if (table[i][j].getCellStatus() != Cell.CellStatus.MINE) {

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
                            if (table[i + m][j + n].getCellStatus() == Cell.CellStatus.MINE) {
                                sum++;
                            }

                        }
                    }

                    table[i][j].setCellStatus(
                            Cell.CellStatus.getBasedOnInt(sum)
                    );

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
     * sets all cells in {@code table}
     * @param cellStatus
     */
    private void setAllCells(Cell.CellStatus cellStatus) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j].setCellStatus(cellStatus);
            }
        }
    }
}

