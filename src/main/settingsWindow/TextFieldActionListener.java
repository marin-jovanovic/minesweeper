package main.settingsWindow;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
//import java.util.Set;

public class TextFieldActionListener implements DocumentListener {


    private JTextField source;
    private String target;
    private String data;

    public TextFieldActionListener(JTextField source, String target) {
        this.source = source;
        this.target = target;
        this.data = "";

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
        data = source.getText();

        try {
            int tester = Integer.parseInt(data);
            System.out.println(data);
            if (target.equals("columnNumber")) {
                SettingsFrame.setColumnNumber(data);
            }
            else if (target.equals("rowNumber")) {
                SettingsFrame.setRowNumber(data);
            }
            else if (target.equals("mineNumber")) {
                SettingsFrame.setMineNumber(data);
            }
            else {
                System.out.println("error with target");
            }
        }
        catch (Exception e) {
            String replacement = "";
            for(String token : data.split("")) {
                if ("0123456789".contains(token)) {
                    replacement += token;
                }
            }
            System.out.println("replacement " + replacement);

            System.out.println("Nan");
        }


//        SettingsFrame.columnNumber = columnNumberField.getText();
//        System.out.println(SettingsFrame.columnNumber);
    }
}
