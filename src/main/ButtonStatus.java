package main;

import main.constants.Constants;

public enum ButtonStatus {
    VICTORY("victory"),
    DEFEAT("defeat"),
    PLAY_AGAIN("playAgain"),
    INIT("playAgain");

    private String path;

    public String getPath() {
        return Constants.RESIZED_IMAGES_PATH + Constants.BUTTON_PATH + this.path + Constants.DOT +
                Constants.IMAGES_FORMAT_NAME;
    }

    ButtonStatus(String path) {
        this.path = path;
    }
}
