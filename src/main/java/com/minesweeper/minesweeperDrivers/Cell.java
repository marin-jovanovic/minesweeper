package com.minesweeper.minesweeperDrivers;

import java.util.EnumSet;

public class Cell {
//    covered, question mark, flag
    private CellVisibility cellVisibility;
//
    private CellStatus cellStatus;

    public Cell() {
        this.cellVisibility = CellVisibility.COVERED;
    }

    public CellVisibility getCellModifier() {
        return cellVisibility;
    }

    public void setCellModifier(CellVisibility cellVisibility) {
        this.cellVisibility = cellVisibility;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    @Override
    public String toString() {
        return String.valueOf(cellStatus);
    }

    public enum CellVisibility {
        COVERED("0"),
        FLAG("f"),
        UNKNOWN("?"),
        OPENED("_");

        private final String string;

        CellVisibility(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    public enum CellStatus {
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
        ;

        private final String string;

        CellStatus(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }

        public static CellStatus getBasedOnInt(int num) {


            for (CellStatus cellStatus:
                 EnumSet.allOf(CellStatus.class)) {

                if (String.valueOf(num).equals(
                        cellStatus.string
                ))  {
                    return cellStatus;
                }
            }

            return null;
        }

        public static void main(String[] args) {
            getBasedOnInt(2);
        }

    }
}
