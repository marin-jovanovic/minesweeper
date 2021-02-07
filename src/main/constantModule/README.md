Config.java
    
    setup paths
    
Constant
    
    
use this for checking type

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