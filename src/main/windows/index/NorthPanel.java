package main.windows.index;

import main.eventDrivers.Command;
import main.resourceManagers.images.Image;
import main.windows.settings.SettingsFrame;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NorthPanel extends JPanel implements PropertyChangeListener {

    private final TimerElement timerElement;

    private final RestartButton restartButton;
    private final JButton settingsButton;
    private boolean isGameOver = false;

    public RestartButton getRestartButton() {
        return restartButton;
    }

    public NorthPanel() {

        timerElement = new TimerElement();
        add(timerElement);

        restartButton = new RestartButton();
        add(restartButton);

        add(ResultComponent.getInstance());

        settingsButton = new JButton("settings");
        settingsButton.addActionListener(e -> {
            try {
                SwingUtilities.invokeLater(() -> {
                    SettingsFrame settingsFrame = new SettingsFrame();
                    settingsFrame.addPropertyChangeListener(timerElement);
                });

            } catch (Exception exy) {
                exy.printStackTrace();
            }

            settingsButton.setEnabled(false);
        });
        add(settingsButton);

        JButton haltButton = new JButton("stop");
        haltButton.addActionListener(event -> {
            if (timerElement.isTicking()) {
                if (haltButton.getText().equals("stop")) {
//                    todo disable center panel operations
//                    todo when restarted or settings closed move state to stop
                    timerElement.stopTimer();
                    haltButton.setText("continue");
                } else {
                    timerElement.startOrContinueTimer();
                    haltButton.setText("stop");
                }
            }
        });
        add(haltButton);

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
            settingsButton.setEnabled(true);

        } else {
            System.out.println("north panel unknown event");
        }
    }

}

