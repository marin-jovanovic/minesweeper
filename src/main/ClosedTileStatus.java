package main;

import main.constants.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;

public enum ClosedTileStatus {
    CLOSED_CELL("closedCell"),
    FLAG("flag"),
    NOT_SURE("notSure");

    private String path;

    public ImageIcon getImageIcon() {
        return new ImageIcon(Constants.RESIZED_IMAGES_PATH + Constants.CLOSED_TILES + this.path + Constants.DOT +
                Constants.IMAGES_FORMAT_NAME);
    }

    ClosedTileStatus(String path) {
        this.path = path;
    }

}
