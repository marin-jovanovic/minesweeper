package com.minesweeper.windows.settings.elements.reset;

import com.minesweeper.resourceManagers.constants.ConstantsManager;
import com.minesweeper.resourceManagers.images.ImageManager;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class RestartDefaultButton extends JPanel {
    private final JButton jButton;
    private final JLabel checker;


    public RestartDefaultButton() {
        jButton = new JButton("restart to default settings");
        jButton.addActionListener(e ->
        {
            ConstantsManager.restartConstants();

            ImageManager.restartAllImages();

            new Thread(() -> {
                this.setCheckerText("settings restarted");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                this.setCheckerText("");
            }).start();

        });
        add(jButton);

        checker = new JLabel();
        add(checker);
    }

    public void setCheckerText(String string) {
        this.checker.setText(string);
    }

    public void main(String[] args) {
        ConstantsManager.restartConstants();
    }
}
