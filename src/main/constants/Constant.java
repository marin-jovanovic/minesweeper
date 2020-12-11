package main.constants;

import java.util.EnumSet;

/**
 * @value
 *
 * @logID
 * this is left side in settings.txt
 * it is used to identify variable
 *
 * @jText
 * used for swing GUI
 * it is string that will be placed in GUI
 * element will be identifed by this String
 */
public enum Constant {
    NUMBER_OF_COLUMNS(10, "number of columns", "columns number:"),
    NUMBER_OF_ROWS(10, "number of rows", "rows number:"),
    NUMBER_OF_MINES(10, "number of mines", "mine number"),
    LOCATION_X(20, "location x"),
    LOCATION_Y(20, "location y"),
    WIDTH(500, "width"),
    HEIGHT(500, "height");


    private String jText;

    public String getJText() {
        return jText;
    }

    Constant(int value, String logID, String jText) {
        this(value, logID);

        this.jText = jText;
    }

    Constant(int value, String logID) {
        this(value);
        this.logID = logID;
    }

    private int value;
    private String logID;

    public String getLogID() {
        return logID;
    }

    Constant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void printAll() {
        System.out.println("printing constant enum");
        // Note: enum name changed to comply with Java naming conventions
        for (Constant constant : EnumSet.allOf(Constant.class)) {
            System.out.println(constant + " -> " + constant.value);
        }
        System.out.println();
    }

    public static Constant getConstant(String logID) {
        for (Constant constant : EnumSet.allOf(Constant.class)) {
            if (constant.logID.equals(logID)) {
                return constant;
            }
        }

        return null;
    }
}
