package main.settingsWindow.elements.textField;

import main.ConstantModule.SettingsManager;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextFieldActionListener implements DocumentListener {

    private final String key;
    private final String oldValue;
    private final TextFieldElement source;

    public TextFieldActionListener(TextFieldElement source) {
        this.source = source;
        this.key = source.getConstant().getLogID();
        this.oldValue = String.valueOf((Integer) source.getConstant().getValue());
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

        SettingsManager.addSettingToBuffer(key, value);

        source.setCheckerText("new value: " + value);
    }

//    checks for errors and formats it to int type
    private String reformatValue(String value) {
        if (value.equals("")) {
            value = oldValue;
        } else {
            if (! value.matches("[1-9][0-9]*")) {
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
