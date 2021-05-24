package com.minesweeper.windows.settings.elements.textField;

import com.minesweeper.resourceManagers.constants.Constant;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextFieldActionListener implements DocumentListener {

    private final Constant constant;
    //    private final String key;
    private final String oldValue;
    private final TextFieldElement source;

    public TextFieldActionListener(TextFieldElement source) {
        this.source = source;
        this.constant = source.getConstant();
//        this.key = source.getConstant().getId();
        this.oldValue = String.valueOf(source.getConstant().getValue());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        processData();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        processData();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        processData();
    }

    private void processData() {
        String value = source.getTextField().getText();

        value = reformatValue(value);

        if (value.matches("[1-9][0-9]*")) {
            constant.setValue(Integer.parseInt(value));
        } else if (value.matches("[0-9]+\\.[0-9]+")) {
            constant.setValue(Double.parseDouble(value));
        } else if (value.equals("true") || value.equals("false")) {
            constant.setValue(Boolean.parseBoolean(value));
        } else {
            constant.setValue(value);
        }

        source.setCheckerText("new value: " + value);

    }

    //    checks for errors and formats it to int type
    private String reformatValue(String value) {
        if (value.equals("")) {
            value = oldValue;
        } else {
            if (!value.matches("[1-9][0-9]*")) {
                value = Arrays
                        .stream(value.split(""))
                        .filter("0123456789"::contains)
                        .collect(Collectors.joining());
            }

            if (value.startsWith("0")) {
                while (value.startsWith("0")) {
                    value = value.substring(1);
                }
            }
        }

        if (value.equals("")) {
            value = oldValue;
        }
        return value;
    }

}
