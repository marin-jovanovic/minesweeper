package main.mainWindow;

import main.constantsModule.Config;
import main.constantsModule.Constant;
import main.constantsModule.ConstantsManager;
import main.utils.eventDrivers.Event;
import main.utils.eventDrivers.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {

    public static MainFrame mainFrame;
    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;
    private final EventListenerList listenerList = new EventListenerList();

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

        northPanel = new NorthPanel();
        centerPanel = new CenterPanel();

        northPanel.addListener(event -> centerPanel.restart(event.getCommand()));

        centerPanel.addListener(event -> {
            try {
                northPanel.setRestartButton(event.getCommand());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

    }


    public static void restartSequence() {
        mainFrame.dispose();
        new MainFrame();
    }

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == Listener.class) {
                ((Listener) listeners[i + 1]).eventOccurred(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
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
