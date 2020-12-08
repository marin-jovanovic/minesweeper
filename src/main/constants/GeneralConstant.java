package main.constants;

public enum GeneralConstant {
    NUMBER_OF_COLUMNS("column number:", "number of columns"),
    NUMBER_OF_ROWS("row number:", "number of rows"),
    NUMBER_OF_MINES("mine number:", "number of mines");

    private int value;
    private final String settingsText;
    private final String logText;

    public static GeneralConstant getConstant(String input) {

        if (input.equals(NUMBER_OF_COLUMNS.getLogText())) {
            return NUMBER_OF_COLUMNS;
        } else if (input.equals(NUMBER_OF_ROWS.getLogText())) {
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

    public String getSettingsText() {
        return settingsText;
    }

    public String getLogText() {
        return logText;
    }

    GeneralConstant(String settingsText, String logText) {
        this.settingsText = settingsText;
        this.logText = logText;
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
