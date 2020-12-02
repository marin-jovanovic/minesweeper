package main.constants.imageDrivers;

import main.constants.imageDrivers.ImagesConstants;

import javax.swing.*;

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

    public ImageIcon getImageIcon() {
        return new ImageIcon(ImagesConstants.RESIZED_IMAGES_PATH + ImagesConstants.OPENED_TILES
                + this.path + ImagesConstants.DOT + ImagesConstants.IMAGES_FORMAT_NAME);
    }

    OpenedTileStatus(String path) {
        this.path = path;
    }
}
