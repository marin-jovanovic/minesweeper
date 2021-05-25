This package allows storing constants in memory.

Config.java

    setup paths  

use this for checking type in Constant.java

        String value;
        if (value.matches("[1-9][0-9]*")) {
            constant.setValue(Integer.parseInt(value));
        } else if (value.matches("[0-9]+\\.[0-9]+")) {
            constant.setValue(Double.parseDouble(value));
        } else if (value.equals("true") || value.equals("false")) {
            constant.setValue(Boolean.parseBoolean(value));
        } else {
            constant.setValue(value);
        }

public methods

    ConstantsManager.restartSettings()
    ConstantsManager.LinkedHashMap<Integer, String> initializeConstants()
    ConstantsManager.printAll()
    ConstantsManager.updateConstants()
    
    Constant.getId()
    Constant.getValue()
    Constant.setValue(Object value)  
    