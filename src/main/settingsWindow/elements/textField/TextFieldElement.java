package main.settingsWindow.elements.textField;

import javax.swing.*;

public class TextFieldElement extends JPanel{
    private final JTextField textField;
    private final JLabel checker;

    public TextFieldElement(String message, String currentValue) {
        add(new JLabel(message));

        textField = new JTextField(10);
        textField.setText(currentValue);
        textField.getDocument().addDocumentListener(
                new TextFieldActionListener(this, message)
        );
        add(textField);

        checker = new JLabel("checker");
        add(checker);
    }

    public void setCheckerText(String string) {
        this.checker.setText(string);
    }

    public JTextField getTextField() {
        return textField;
    }
}
