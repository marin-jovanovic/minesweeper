package com.minesweeper.minesweeperDrivers;

import java.util.EnumSet;

public class Cell {
//    covered, question mark, flag
    private CellStatus cellStatus;

    private CellValue cellValue;

    public Cell() {
        this.cellStatus = CellStatus.COVERED;
    }

    public CellStatus getCellStatus() {
        return this.cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public CellValue getCellValue() {
        return this.cellValue;
    }

    public void setCellValue(CellValue cellValue) {
        this.cellValue = cellValue;
    }

    @Override
    public String toString() {
        return String.valueOf(cellValue);
    }

    public enum CellStatus {
        COVERED("c"),
        FLAG("f"),
        UNKNOWN("u"),
        OPENED("O");

        private final String string;

        CellStatus(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    public enum CellValue {
//        for auto solver
        UNKNOWN("?"),
        MINE("_"),
        ZERO("0"),
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
//        what user pressed
//      todo for testing purposes
        USER("u");

        private final String string;

        CellValue(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }

        public static CellValue getBasedOnInt(int num) {


            for (CellValue cellValue :
                 EnumSet.allOf(CellValue.class)) {

                if (String.valueOf(num).equals(
                        cellValue.string
                ))  {
                    return cellValue;
                }
            }

            return null;
        }

        public static void main(String[] args) {
            getBasedOnInt(2);
        }

    }
}
