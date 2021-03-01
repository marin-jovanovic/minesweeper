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

    public NorthPanel() {

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

        JButton haltButton = new JButton("stop");

        haltButton.addActionListener(event -> {
            if (timerElement.isTicking()) {
                if (haltButton.getText().equals("stop")) {
//                    todo dissable center panel operations
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

    public void enableSettingsButton() {
        settingsButton.setEnabled(true);
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

