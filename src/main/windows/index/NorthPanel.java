package main.windows.index;

import main.eventDrivers.Command;
import main.resourceManagers.images.Image;
import main.windows.settings.SettingsFrame;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NorthPanel extends JPanel implements PropertyChangeListener {

    private final static NorthPanel instance = new NorthPanel();
    private final TimerElement timerElement;

    private final RestartButton restartButton;
    private boolean isGameOver = false;

    private final JButton settingsButton;

    public void enableSettingsButton() {
        settingsButton.setEnabled(true);
    }

    private NorthPanel() {

//        TODO pause button

        timerElement = new TimerElement();
        add(timerElement);

        restartButton = RestartButton.getInstance();
        add(restartButton);


        add(ResultComponent.getInstance());

        settingsButton = new JButton("settings");

        settingsButton.addActionListener(event -> {
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SettingsFrame settingsFrame = new SettingsFrame();
                        settingsFrame.addPropertyChangeListener(timerElement);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

            settingsButton.setEnabled(false);

        });

        add(settingsButton);

//        JButton testButton = new JButton("test button");
//
//        testButton.addActionListener(event -> {
//            System.out.println("******************************************\ntest button pressed");
//
//            SoundsManager.playGameOverSound();
//        });
//
//        add(testButton);

    }

    public static NorthPanel getInstance() {
        return instance;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getNewValue() == Command.GAME_OVER) {
            if (!isGameOver) {

                restartButton.setIcon(Image.DEFEAT.getImageIcon());
                timerElement.stopTimer();
                ResultLogger.processResult(ResultLogger.Result.DEFEAT, timerElement.getTime());

                isGameOver = true;
            } else {
                System.out.println("inspect game over signal");
            }

        } else if (evt.getNewValue() == Command.GAME_WON) {
            if (!isGameOver) {

                restartButton.setIcon(Image.VICTORY.getImageIcon());
                timerElement.stopTimer();
                ResultLogger.processResult(ResultLogger.Result.VICTORY, timerElement.getTime());

                isGameOver = true;
            } else {
                System.out.println("inspect game won signal");
            }


        } else if (evt.getNewValue() == Command.RESTART_NORTH_PANEL) {
//            settings changed

            restartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());
            timerElement.restartTimer();

            isGameOver = false;

        } else if (evt.getNewValue() == Command.START_TIMER) {
            timerElement.startOrContinueTimer();
        } else if (evt.getNewValue() == Command.RESTART_MAINFRAME) {
            timerElement.restartTimer();
        } else {
            System.out.println("north panel unknown event");
        }
    }

}

