package main.minesweeperDrivers;

public class CoverTable {

    public enum  CellStatus {

        COVERED("0"),
        FLAG("f"),
        UNKNOWN("?");

        private final String string;

        CellStatus(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    public CellStatus[][] talbe;

    public CellStatus[][] getTalbe() {
        return talbe;
    }

    public void setCell(int x, int y, CellStatus cellStatus) {

    }

    private CellStatus getCell(int x, int y) {
        return talbe[x][y];
    }
}
