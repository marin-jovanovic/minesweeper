package main.settingsWindow.elements.textField;


import main.settingsWindow.SettingsBuffer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextFieldActionListener implements DocumentListener {

//    private final JTextField source;
    private final String key;
    private final String oldValue;

    private final TextFieldElement source;

    public TextFieldActionListener(TextFieldElement source, String key) {
        this.source = source;
        this.key = key;
        this.oldValue = source.getTextField().getText();
        SettingsBuffer.writeToBuffer(key, source.getTextField().getText());
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


//    handles non int types
//    0000 + int
//    int + alphanumeric
//    "" -> empty cell
//        reads original input
//    alphas -> old value
    public void processData() {
        String value = source.getTextField().getText();

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

        SettingsBuffer.writeToBuffer(key, value);

        source.setCheckerText("new value: " + value);
    }

}
