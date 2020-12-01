package main;

import main.constants.Constants;

public enum ClosedTileStatus {
    CLOSED_CELL("closedCell"),
    FLAG("flag"),
    NOT_SURE("notSure");

    private String path;

    public String getPath() {
        return Constants.RESIZED_IMAGES_PATH + Constants.CLOSED_TILES + this.path + Constants.DOT +
                Constants.IMAGES_FORMAT_NAME;
    }

    ClosedTileStatus(String path) {
        this.path = path;
    }
}
