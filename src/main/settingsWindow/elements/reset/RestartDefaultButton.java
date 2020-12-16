package main.settingsWindow.elements.reset;

import main.ConstantModule.SettingsManager;

import javax.swing.*;

public class RestartDefaultButton extends JPanel {
    private final JButton jButton;
    private JLabel checker;


    public RestartDefaultButton() {
        jButton = new JButton("restart to default settings");
        jButton.addActionListener(e -> SettingsManager.restartSettings(this));
        add(jButton);

        checker = new JLabel();
        add(checker);
    }

    public void setCheckerText(String string) {
        this.checker.setText(string);
    }

    public void main(String[] args) {
        SettingsManager.restartSettings();
    }
}
