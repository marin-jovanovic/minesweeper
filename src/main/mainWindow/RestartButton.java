package main.mainWindow;

import main.imagesModule.Image;
import main.utils.eventDrivers.Command;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RestartButton extends JButton {

    private static final RestartButton instance = new RestartButton();
    private final PropertyChangeSupport support;

    private RestartButton() {

        support = new PropertyChangeSupport(this);

        setIcon(Image.PLAY_AGAIN.getImageIcon());

        addActionListener(e -> {
            System.out.println("restart button clicked");


//
//            if (CenterPanel.getInstance().isFirstButtonClicked()) {
//                CenterPanel.getInstance().setFirstButtonClicked(false);
//            }

            support.firePropertyChange("restart timer", null, Command.RESTART_TIMER);
            support.firePropertyChange("new game", null, Command.NEW_GAME);

            NorthPanel.getInstance().setGameOver(false);

            setIcon(Image.PLAY_AGAIN.getImageIcon());

        });

    }

    public static RestartButton getInstance() {
        return instance;
    }

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public void removeListener(PropertyChangeListener propertyChangeListener) {
        support.removePropertyChangeListener(propertyChangeListener);
    }
}
