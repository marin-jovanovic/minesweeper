package main.mainWindow;

import main.constantsModule.Config;
import main.constantsModule.Constant;
import main.constantsModule.ConstantsManager;
import main.settingsWindow.SettingsWindowListener;
import main.utils.eventDrivers.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MainFrame extends JFrame implements PropertyChangeListener {

    public static MainFrame mainFrame;
    private static boolean isFirstTime = true;
    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;
    private final RestartButton restartButton;

    public MainFrame() {
        super("minesweeper");

        ConstantsManager.initializeConstants();

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setSize(((Double) Constant.WIDTH.getValue()).intValue(), ((Double) Constant.HEIGHT.getValue()).intValue());
        setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());

        addWindowListener(new MainFrameWindowListener(this));

        mainFrame = this;

        northPanel = NorthPanel.getInstance();
        centerPanel = new CenterPanel();

        restartButton = RestartButton.getInstance();

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


        restartButton.addListener(centerPanel);
        restartButton.addListener(NorthPanel.getInstance().getTimerElement());
        centerPanel.addListener(northPanel);
        SettingsWindowListener.getInstance().addListener(this);
        SettingsWindowListener.getInstance().addListener(NorthPanel.getInstance());
    }

    public static void restartSequence() {
        System.out.println("restart seq started");

        mainFrame.dispose();
        mainFrame = new MainFrame();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == Command.RESTART_MAINFRAME) {
            SettingsWindowListener.getInstance().removeListener(mainFrame);
            SettingsWindowListener.getInstance().removeListener(NorthPanel.getInstance());
            restartButton.removeListener(centerPanel);
            restartButton.removeListener(NorthPanel.getInstance().getTimerElement());
            centerPanel.removeListener(northPanel);
            restartSequence();
        } else {
            System.out.println("non good var in mainframe ");
        }


    }

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
