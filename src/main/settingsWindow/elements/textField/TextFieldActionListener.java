package main.settingsWindow.elements.textField;


import main.settingsWindow.SettingsBuffer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TextFieldActionListener implements DocumentListener {

    private final JTextField source;
    private final String key;

    public TextFieldActionListener(JTextField source, String key) {
        this.source = source;
        this.key = key;

        SettingsBuffer.writeToBuffer(key, source.getText());
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

    public void processData() {
        String value = source.getText();
        System.out.println(key + " " + value);

        if (! value.matches("[1-9][0-9]*")) {
            value = Arrays
                    .stream(value.split(""))
                    .filter(s -> "0123456789".contains(s))
                    .collect(Collectors.joining());
        }

        if (value.startsWith("0")) {
            while (value.startsWith("0")) {
                value = value.substring(1);
            }
        }

        System.out.println(key + " " + value);
        SettingsBuffer.writeToBuffer(key, value);
    }

}
