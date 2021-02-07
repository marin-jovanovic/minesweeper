package main.constantModule;

public class ConstantCounterManager {

    private static final ConstantCounterManager instance = new ConstantCounterManager();

    private ConstantCounterManager() {}

    public static ConstantCounterManager getInstance() {
        return instance;
    }

    private static int numOfConstants = 0;

    public static int getNumOfConstants() {
        return numOfConstants;
    }

    public static void setNumOfConstants(int numOfConstants) {
        ConstantCounterManager.numOfConstants = numOfConstants;
    }
}
