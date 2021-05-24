package com.minesweeper.minesweeperDrivers;

public class Cell {
    private CellModifier cellModifier;
    private CellStatus cellStatus;

    public Cell() {
        this.cellModifier = CellModifier.COVERED;
    }

    public CellModifier getCellModifier() {
        return cellModifier;
    }

    public void setCellModifier(CellModifier cellModifier) {
        this.cellModifier = cellModifier;
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

    public enum CellModifier {
        COVERED("0"),
        FLAG("f"),
        UNKNOWN("?");

        private final String string;

        CellModifier(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    public enum CellStatus {
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
        USER("u");

        private final String string;

        CellStatus(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }
}
