package com.minesweeper.windows.index;

import com.minesweeper.eventDrivers.Command;
import com.minesweeper.resourceManagers.images.Image;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PauseButton extends JButton {

//    private final PropertyChangeSupport support;
    private State state = State.NORMAL;

    enum State {
        PAUSED,
        NORMAL,

    }

    public State getState() {
        return state;
    }

    public PauseButton() {

        this.setIcon(Image.STOP.getImageIcon());
    }


    public void stopGame() {
        this.setIcon(Image.STOP.getImageIcon());
    }
//
//    public void
}
