package main.constants;

import main.constants.imageDrivers.ImagesConstants;

import javax.swing.*;

public enum GeneralConstant {
    NUMBER_OF_COLUMNS("column number:", "constant__number_of_columns"),
    NUMBER_OF_ROWS("row number:", "constant__number_of_rows"),
    NUMBER_OF_MINES("mine number:", "constant__number_of_mines");

    private final String pathID;
    private final String jText;
    private final String logID;

    public ImageIcon getImageIcon() {
        return new ImageIcon(ImagesConstants.RESIZED_IMAGES_PATH +
                ImagesConstants.BUTTON_PATH + this.pathID + ImagesConstants.DOT +
                ImagesConstants.IMAGES_FORMAT_NAME);
    }

    GeneralConstant(String pathID, String logID) {
        this.jText = pathID;
        this.pathID = pathID;
        this.logID = logID;
    }

    GeneralConstant(String pathID) {
        this.jText = pathID;
        this.pathID = pathID;
        this.logID = pathID;

    }
    String getJText() {
        return "change " + this.jText + " image";
    }
    String getLogID() {
        return logID;
    }


//
//    NUMBER_OF_COLUMNS("column number:", "constant__number_of_columns"),
//    NUMBER_OF_ROWS("row number:", "constant__number_of_rows"),
//    NUMBER_OF_MINES("mine number:", "constant__number_of_mines");

    private int value;
//
//    private String pathID;
//    private final String jText;
//    private final String logID;
//
//

//    GeneralConstant(String pathID, String jText, String logID) {
//        this.pathID = pathID;
//        this.jText = jText;
//        this.logID =
//    }

    public static GeneralConstant getConstant(String input) {

        if (input.equals(NUMBER_OF_COLUMNS.getLogID())) {
            return NUMBER_OF_COLUMNS;
        } else if (input.equals(NUMBER_OF_ROWS.getLogID())) {
            return NUMBER_OF_ROWS;
        } else {
            return NUMBER_OF_MINES;
        }

    }


    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getjText() {
        return jText;
    }

    public String getLogID() {
        return logID;
    }

    GeneralConstant(String jText, String logID) {
        this.jText = jText;
        this.logID = logID;
    }


    /**
     * @value
     * value
     *
     * @settingsText
     * text which will be placed in settings window
     *
     * @logText
     * text which will be in settings log
     */
}
