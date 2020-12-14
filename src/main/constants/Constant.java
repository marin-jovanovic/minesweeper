package main.constants;

import java.util.EnumSet;

/**
 * logID
 * this is left side in settings.txt
 * it is used to identify variable
 *
 * jText
 * used for swing GUI
 * it is string that will be placed in GUI
 * element will be identified by this string
 */
public enum Constant {
    NUMBER_OF_COLUMNS(10, "number of columns", "columns number:"),
    NUMBER_OF_ROWS(10, "number of rows", "rows number:"),
    NUMBER_OF_MINES(10, "number of mines", "mine number"),
    LOCATION_X(20, "location x"),
    LOCATION_Y(20, "location y"),
    WIDTH(500, "width"),
    HEIGHT(500, "height");


    private int value;
    private String logID;
    private String jText;


    Constant(int value, String logID, String jText) {
        this(value, logID);
        this.jText = jText;
    }

    Constant(int value, String logID) {
        this.value = value;
        this.logID = logID;
    }


    public String getLogID() {
        return logID;
    }

    public String getJText() {
        return jText;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     *  prints all constants
     *  constant + -> + constant.value
     */
    public static void printAll() {
        System.out.println("Constant: printAll");

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


    public static void main(String[] args) {
        printAll();
    }
}
