package main.windows.index;

import main.resourceManagers.images.Image;
import main.eventDrivers.Command;

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

            support.firePropertyChange("center panel", null, Command.NEW_GAME);

            support.firePropertyChange("north panel", null, Command.RESTART_NORTH_PANEL);

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
