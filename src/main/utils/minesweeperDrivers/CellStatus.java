package main.utils.minesweeperDrivers;

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
    EIGHT("8");

    private String string;

    CellStatus(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
