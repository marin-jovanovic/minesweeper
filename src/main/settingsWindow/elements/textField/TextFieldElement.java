package main.settingsWindow.elements.textField;

import main.ConstantModule.Constant;

import javax.swing.*;

public class TextFieldElement extends JPanel{
    private final JTextField textField;
    private final JLabel checker;

    private final Constant constant;

    public TextFieldElement(Constant constant) {
        add(new JLabel(constant.getJText()));

        this.constant = constant;

        textField = new JTextField(10);
        textField.setText(String.valueOf((Integer) constant.getValue()));
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
