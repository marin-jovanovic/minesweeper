package main.mainWindow;

import main.imagesModule.Image;
import main.settingsWindow.SettingsFrame;
import main.soundsModule.SoundsManager;
import main.utils.eventDrivers.Command;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NorthPanel extends JPanel implements PropertyChangeListener {

    private final static NorthPanel instance = new NorthPanel();
    private final TimerElement timerElement;

    private final RestartButton restartButton;

    private NorthPanel() {


//        TODO statistics, time, pause button


        /* todo
            restart timer when settings are closed

            stop timer when settings are opened
            start timer when first button is clicked
            stop timer when game is over
            restart timer when new game is pressed
         */
        timerElement = new TimerElement();
        add(timerElement);

        restartButton = RestartButton.getInstance();
//        restartButton.addListener(timerElement);
//        restartButton.addPropertyChangeListener(timerElement);
        add(restartButton);

        JButton settingsButton = new JButton("settings");

        settingsButton.addActionListener(event -> {
            try {
                SwingUtilities.invokeLater(SettingsFrame::new);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(settingsButton);


        JButton testButton = new JButton("test button");

        testButton.addActionListener(event -> {
            System.out.println("******************************************\ntest button pressed");

            SoundsManager.playGameOverSound();
        });

        add(testButton);

    }

    public static NorthPanel getInstance() {
        return instance;
    }

    public TimerElement getTimerElement() {
        return timerElement;
    }

    public void setRestartButton(Command command) {
        if (command.equals(Command.GAME_OVER)) {
            restartButton.setIcon(Image.DEFEAT.getImageIcon());

        } else if (command.equals(Command.GAME_WON)) {
            restartButton.setIcon(Image.VICTORY.getImageIcon());

        } else {
            System.out.println("error set restart button in north panel");
            System.exit(-1);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getNewValue() == Command.GAME_OVER) {
            restartButton.setIcon(Image.DEFEAT.getImageIcon());
            setRestartButton(Command.GAME_OVER);
            timerElement.stopTimer();
        } else if (evt.getNewValue() == Command.GAME_WON) {
//            setRestartButton(Command.GAME_WON);
            restartButton.setIcon(Image.VICTORY.getImageIcon());
            timerElement.stopTimer();
        } else if (evt.getNewValue() == Command.RESTART_MAINFRAME) {
            restartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());
            timerElement.restartTimer();
//            setRestartButton();

        } else {
                System.out.println("north panel unknown event");
            }
    }

}

