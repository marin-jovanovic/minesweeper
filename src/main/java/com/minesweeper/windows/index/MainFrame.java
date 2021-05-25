package com.minesweeper.windows.index;

import com.minesweeper.eventDrivers.Command;
import com.minesweeper.minesweeper.windows.index.CenterPanel;
import com.minesweeper.resourceManagers.constants.Config;
import com.minesweeper.resourceManagers.constants.Constant;
import com.minesweeper.resourceManagers.constants.ConstantsManager;
import com.minesweeper.windows.settings.SettingsWindowListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MainFrame extends JFrame implements PropertyChangeListener {

    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;
    private final RestartButton restartButton;

    public MainFrame() {
        super("minesweeper");

        ConstantsManager.initializeConstants();

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setSize(((Double) Constant.WIDTH.getValue()).intValue(),
                ((Double) Constant.HEIGHT.getValue()).intValue());
        setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());

        addWindowListener(new MainFrameWindowListener(this));

        northPanel = new NorthPanel();

        centerPanel = new CenterPanel();

        restartButton = northPanel.getRestartButton();

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        restartButton.addListener(centerPanel);
        restartButton.addListener(northPanel);
        centerPanel.addListener(northPanel);

        SettingsWindowListener.getInstance().addListener(this);
        SettingsWindowListener.getInstance().addListener(northPanel);

    }

    public void restartSequence() {
        System.out.println("restart seq started");

        SettingsWindowListener.getInstance().removeListener(this);
        SettingsWindowListener.getInstance().removeListener(northPanel);

        restartButton.removeListener(centerPanel);
        centerPanel.removeListener(northPanel);
        restartButton.removeListener(northPanel);

        dispose();
        new MainFrame();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == Command.RESTART_MAINFRAME) {

            restartSequence();

        } else {
            System.out.println("non good var in mainframe ");
        }


    }

    /**
     * saves all constants on close
     * logs location and dimensions
     */
    private static class MainFrameWindowListener implements WindowListener {

        private final JFrame jFrame;

        public MainFrameWindowListener(JFrame jFrame) {
            this.jFrame = jFrame;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("closing");

            Constant.LOCATION_X.setValue(jFrame.getX());
            Constant.LOCATION_Y.setValue(jFrame.getY());

            Constant.WIDTH.setValue(jFrame.getSize().getWidth());
            Constant.HEIGHT.setValue(jFrame.getSize().getHeight());

            ConstantsManager.updateConstants(Config.getConstantsMemoryPath());
        }

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }

}
