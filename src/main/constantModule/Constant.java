package main.constantModule;

/**
 * jtext used for gui
 */
public enum Constant {
    NUMBER_OF_COLUMNS(10),
    NUMBER_OF_ROWS(10),
    NUMBER_OF_MINES(10),
    LOCATION_X(20),
    LOCATION_Y(20),
    WIDTH(500.0),
    HEIGHT(500.0),

    IS_VICTORY_SOUND_ACTIVE(true),
    IS_DEFEAT_SOUND_ACTIVE(true),
    IS_ANY_SOUND_ACTIVE(true),

    CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN(false);

    private static class ConstantCounterManager {

        private static int numOfConstants = 0;

        static int getNumOfConstants() {
            return numOfConstants;
        }

        static void setNumOfConstants(int numOfConstants) {
            ConstantCounterManager.numOfConstants = numOfConstants;
        }
    }
    private String id;
    private Object value;

    Constant(Object value) {
        this.id = this.name();
        this.value = value;

        ConstantCounterManager.setNumOfConstants(ConstantCounterManager.getNumOfConstants() + 1);
    }

    public static int getNumOfConstants() {
        return ConstantCounterManager.getNumOfConstants();
    }

    @Override
    public String toString() {
        String type;

        if (value instanceof Integer) {
            type = "Integer";
        } else if (value instanceof Double) {
            type = "Double";
        } else if (value instanceof Boolean) {
            type = "Boolean";
        } else if (value instanceof String){
            type = "String";
        } else {
            type = "error";
            System.exit(-1);
        }

        return "Constant{ id= " + id + ", value= " + value + ", type= " + type + "}";
    }

    public static void main(String[] args) {

        ConstantsManager.initializeConstants();
        ConstantsManager.printAll();

        ConstantsManager.updateConstants(Config.getConstantsMemoryPath());
    }

    public String getId() {
        return id;
    }

    public Object getValue() {

        if (value instanceof Integer) {
            return Integer.parseInt(String.valueOf(value));
        } else if (value instanceof Double) {
            return Double.parseDouble(String.valueOf(value));
        } else if (value instanceof Boolean) {
            return Boolean.parseBoolean(String.valueOf(value));
        } else if (value instanceof String){
            return value;
        } else {
            System.out.println("error; getValue");
            System.exit(-1);
        }

        return value;
    }

    public void setValue(Object value) {

        if (this.value.getClass().equals(value.getClass())) {
            this.value = value;
        } else {
            System.out.println("objects not of same type");
            System.exit(-1);
        }
    }

}
