package main.constants;

import java.util.EnumSet;


//  delete constants and and change this enum name to constant
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

    private Constant(int value, String logID) {
        this(value);
        this.logID = logID;
    }

    private int value;
    private String logID;

    public String getLogID() {
        return logID;
    }

    private Constant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

//    @Override
//    public String toString() {
//        return this + " " + value;
//    }


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



//        if (logID.equals("number of columns")) {
//            return NUMBER_OF_COLUMNS;
//        } else if (logID.equals("number of rows")) {
//            return NUMBER_OF_ROWS;
//        } else if (logID.equals("number of mines")) {
//            return NUMBER_OF_MINES;
//        } else if (logID.equals("location x")) {
//            return LOCATION_X;
//        } else if (logID.equals("location y")) {
//            return LOCATION_Y;
//        } else if (logID.equals("width")) {
//            return WIDTH;
//        } else {
//            return HEIGHT;
//        }
    }
}
