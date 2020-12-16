package main.constantModule;

import java.util.EnumSet;

/**
 * logID
 * this is left side in settings.txt
 * it is used to identify variable
 * <p>
 * jText
 * used for swing GUI
 * it is string that will be placed in GUI
 * element will be identified by this string
 */

//  TODO value : Object

public enum Constant {
    NUMBER_OF_COLUMNS(10, "number of columns", "columns number:"),
    NUMBER_OF_ROWS(10, "number of rows", "rows number:"),
    NUMBER_OF_MINES(10, "number of mines", "mine number"),
    LOCATION_X(20, "location x"),
    LOCATION_Y(20, "location y"),
    WIDTH(500, "width"),
    HEIGHT(500, "height"),

    IS_VICTORY_SOUND_ACTIVE(true, "victory sound"),
    IS_DEFEAT_SOUND_ACTIVE(true, "defeat sound"),
    IS_ANY_SOUND_ACTIVE(true, "all sounds"),

    CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN(false, "flag1");


    private Object value;
    private final String logID;
    private String jText;


    Constant(Object value, String logID, String jText) {
        this(value, logID);
        this.jText = jText;
    }

    Constant(Object value, String logID) {
        this.value = value;
        this.logID = logID;
    }

    public String getLogID() {
        return logID;
    }

    public String getJText() {
        return jText;
    }

    public Object getValue() {
//        return value;
        if (value instanceof Integer) {
            System.out.println("int");
            return Integer.parseInt(String.valueOf(value));
        } else if (value instanceof String) {
            System.out.println("str");
            return String.valueOf(value);
        } else if (value instanceof Boolean) {
            System.out.println("bool");
            return Boolean.valueOf(String.valueOf(value));
        } else {
            return value;
        }
    }

    public void setValue(Object v) {
        this.value = v;
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
        ConstantsManager.printAll();

        for (Constant constant : EnumSet.allOf(Constant.class)) {
            System.out.println(constant);
            System.out.println(constant.getValue());
            System.out.println();
        }

    }
}
