package main.settingsWindow.elements.textField;

import javax.swing.*;

public class TextFieldElement extends JPanel{
    JLabel label;
    JTextField textField;
    JLabel checker;

    private static final int textFieldLength = 10;

    public TextFieldElement(String message, String currentValue) {
        label = new JLabel(message);
        add(label);

        textField = new JTextField(textFieldLength);
        textField.setText(currentValue);
        textField.getDocument().addDocumentListener(
                new TextFieldActionListener(textField, message)
        );
        add(textField);

        checker = new JLabel("checker");
        add(checker);

    }

}