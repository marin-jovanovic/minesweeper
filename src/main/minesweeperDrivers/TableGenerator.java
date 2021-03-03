package main.minesweeperDrivers;

import main.resourceManagers.constants.Constant;

import java.util.Random;

/**
 * generates table with mines and hints
 */
public class TableGenerator {



//    private class Cell {
//        private CellModifier cellModifier;
//        private CellStatus cellStatus;
//
//        public CellModifier getCellModifier() {
//            return cellModifier;
//        }
//
//        public void setCellModifier(CellModifier cellModifier) {
//            this.cellModifier = cellModifier;
//        }
//
//        public CellStatus getCellStatus() {
//            return cellStatus;
//        }
//
//        public void setCellStatus(CellStatus cellStatus) {
//            this.cellStatus = cellStatus;
//        }
//
//
//    }

    private static CellStatus[][] table;
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

    public static CellStatus[][] getTable() {
        generateTable();

        printTable();

        return table;
    }

    private static void generateTable() {

        table = new CellStatus[getNumberOfRows()][getNumberOfColumns()];
        isRowFull = new boolean[getNumberOfRows()];

//        case: more mines or equal to size of table
        if (getNumberOfMines() >= getNumberOfRows() * getNumberOfColumns()) {

            for (int i = 0; i < getNumberOfRows(); i++) {
                for (int j = 0; j < getNumberOfColumns(); j++) {
                    table[i][j] = CellStatus.MINE;
                }
            }

            return;
        }

//        all cells in table set to {CellStatus.ZERO}
        setAllCellsToZero();

//        sets all isRowFull to false
        initializeIsRowFull();

        for (int i = 0; i < getNumberOfMines(); i++) {
            int row = getRow();
            insertMineInRow(row);
            updateIsRowFull(row);
        }

        generateHints();

    }

    /**
     * generates numbers on table
     */
    private static void generateHints() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {

                if (table[i][j] != CellStatus.MINE) {

                    int sum = 0;

                    for (int m = -1; m < 2; m++) {
                        for (int n = -1; n < 2; n++) {

                            if (i + m < 0 || j + n < 0 || i + m >= getNumberOfRows()
                                    || j + n >= getNumberOfColumns()) {
                                continue;
                            }

                            if (table[i + m][j + n] == CellStatus.MINE) {
                                sum++;
                            }

                        }
                    }

                    switch (sum) {
                        case 0:
                            table[i][j] = CellStatus.ZERO;
                            break;
                        case 1:
                            table[i][j] = CellStatus.ONE;
                            break;
                        case 2:
                            table[i][j] = CellStatus.TWO;
                            break;
                        case 3:
                            table[i][j] = CellStatus.THREE;
                            break;
                        case 4:
                            table[i][j] = CellStatus.FOUR;
                            break;
                        case 5:
                            table[i][j] = CellStatus.FIVE;
                            break;
                        case 6:
                            table[i][j] = CellStatus.SIX;
                            break;
                        case 7:
                            table[i][j] = CellStatus.SEVEN;
                            break;
                        case 8:
                            table[i][j] = CellStatus.EIGHT;
                            break;
                    }

                }

            }
        }
    }

    /**
     * sets all values to false
     */
    private static void initializeIsRowFull() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            isRowFull[i] = false;
        }
    }

    /**
     * gets random non-full row
     *
     * @return random non-full row
     */
    private static int getRow() {
        Random rand = new Random();

        int row;
        do {
//                row in which mine will be placed
            row = rand.nextInt(getNumberOfRows());

        } while (isRowFull[row]);

        return row;
    }

    /**
     * inserts mine in {@code row} at random position (checks if position is occupied)
     *
     * @param row row in which mine is to be inserted
     */
    private static void insertMineInRow(int row) {

        Random rand = new Random();

        while (true) {
            int column = rand.nextInt(getNumberOfColumns());

            if (table[row][column] != CellStatus.MINE) {
                table[row][column] = CellStatus.MINE;
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
    private static void updateIsRowFull(int row) {

        for (int i = 0; i < getNumberOfColumns(); i++) {
            if (table[row][i] != CellStatus.MINE) {
                return;
            }
        }

        isRowFull[row] = true;
    }

    /**
     * formatted print of {@code table}
     */
    private static void printTable() {
        System.out.println("table:");

        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * sets all cells in {@code table} to {@code CellStatus.ZERO}
     */
    private static void setAllCellsToZero() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                table[i][j] = CellStatus.ZERO;
            }
        }
    }
}

