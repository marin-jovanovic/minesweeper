package main;

import main.constants.Constants;

public enum OpenedTileStatus {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    MINE("-1");

    private String path;

    public String getPath() {
        return Constants.RESIZED_IMAGES_PATH + Constants.OPENED_TILES + this.path + Constants.DOT +
                Constants.IMAGES_FORMAT_NAME;
    }

    OpenedTileStatus(String path) {
        this.path = path;
    }
}
