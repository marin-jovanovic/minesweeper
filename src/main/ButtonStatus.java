package main;

import main.constants.Constants;

import javax.swing.*;

public enum ButtonStatus {
    VICTORY("victory"),
    DEFEAT("defeat"),
    PLAY_AGAIN("playAgain"),
    INIT("playAgain");

    private String path;

    public ImageIcon getImageIcon() {
        return new ImageIcon(Constants.RESIZED_IMAGES_PATH + Constants.BUTTON_PATH + this.path + Constants.DOT +
                Constants.IMAGES_FORMAT_NAME);
    }

    ButtonStatus(String path) {
        this.path = path;
    }
}
