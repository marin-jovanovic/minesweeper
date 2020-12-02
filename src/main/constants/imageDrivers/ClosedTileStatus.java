package main.constants.imageDrivers;

import main.constants.imageDrivers.ImagesConstants;

import javax.swing.*;

public enum ClosedTileStatus {
    CLOSED_CELL("closedCell"),
    FLAG("flag"),
    NOT_SURE("notSure");

    private String path;

    public ImageIcon getImageIcon() {
        return new ImageIcon(ImagesConstants.RESIZED_IMAGES_PATH + ImagesConstants.CLOSED_TILES + this.path + ImagesConstants.DOT +
                ImagesConstants.IMAGES_FORMAT_NAME);
    }

    ClosedTileStatus(String path) {
        this.path = path;
    }

}
