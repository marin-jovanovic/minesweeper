package com.minesweeper.windows.index;

import com.minesweeper.eventDrivers.Command;
import com.minesweeper.resourceManagers.images.Image;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RestartButton extends JButton {

    private final PropertyChangeSupport support;

    public RestartButton() {

        support = new PropertyChangeSupport(this);

        setIcon(Image.PLAY_AGAIN.getImageIcon());

        addActionListener(e -> {
            System.out.println("restart button clicked");

            support.firePropertyChange("center panel", null, Command.NEW_GAME);

            support.firePropertyChange("north panel", null, Command.RESTART_NORTH_PANEL);

        });

    }

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener propertyChangeListener) {
        support.removePropertyChangeListener(propertyChangeListener);
    }
}
