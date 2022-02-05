package com.minesweeper.windows.settings.elements.textField;

import com.minesweeper.resourceManagers.constants.Constant;

import javax.swing.*;

public class TextFieldElement extends JPanel {
    private final JTextField textField;
    private final JLabel checker;

    private final Constant constant;
    private final String guiId;
    private final JLabel label;


    public TextFieldElement(Constant constant, String guiId) {
//        add(new JLabel("todo 1"));

        this.constant = constant;
        this.guiId = guiId;

        this.label = new JLabel(guiId);
        add(label);

        textField = new JTextField(10);
        textField.setText(String.valueOf(constant.getValue()));
        textField.getDocument().addDocumentListener(
                new TextFieldActionListener(this)
        );
        add(textField);

        checker = new JLabel();
        add(checker);
    }

    public Constant getConstant() {
        return constant;
    }


    public void setCheckerText(String string) {
        this.checker.setText(string);
    }

    public JTextField getTextField() {
        return textField;
    }
}
