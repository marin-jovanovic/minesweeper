package main.constants.imageDrivers;

import main.constants.imageDrivers.ImagesConstants;

import javax.swing.*;

public enum ButtonStatus {
    VICTORY("victory"),
    DEFEAT("defeat"),
    PLAY_AGAIN("playAgain"),
    INIT("playAgain");

    private String path;

    public ImageIcon getImageIcon() {
        return new ImageIcon(ImagesConstants.RESIZED_IMAGES_PATH + ImagesConstants.BUTTON_PATH + this.path + ImagesConstants.DOT +
                ImagesConstants.IMAGES_FORMAT_NAME);
    }

    ButtonStatus(String path) {
        this.path = path;
    }
}
