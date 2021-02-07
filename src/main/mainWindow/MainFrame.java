package main.mainWindow;

import main.constantModule.Constant;
import main.constantModule.ConstantsManager;
import main.utils.eventDrivers.Event;
import main.utils.eventDrivers.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {

    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;

    public static MainFrame mainFrame;


    private class MainFrameWindowListener implements WindowListener {

        private final JFrame jFrame;

        public MainFrameWindowListener(JFrame jFrame) {
            this.jFrame = jFrame;
        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("closing");
            System.out.println(jFrame.getX());
            System.out.println(jFrame.getY());

            System.out.println(jFrame.getSize().getWidth());
            System.out.println(jFrame.getSize().getHeight());


            Constant.LOCATION_X.setValue(String.valueOf(jFrame.getX()));
            Constant.LOCATION_Y.setValue(jFrame.getY());
//            SettingsManager.addSettingToBuffer(Constant.LOCATION_X.getId(), String.valueOf(jFrame.getX()));
//            SettingsManager.addSettingToBuffer(Constant.LOCATION_Y.getId(), String.valueOf(jFrame.getY()));

            Constant.WIDTH.setValue(jFrame.getSize().getWidth());
            Constant.HEIGHT.setValue(jFrame.getSize().getHeight());

//            SettingsManager.addSettingToBuffer(Constant.WIDTH.getId(),
//                    String.valueOf(Integer.valueOf((int) jFrame.getSize().getWidth())));
//            SettingsManager.addSettingToBuffer(Constant.HEIGHT.getId(),
//                    String.valueOf(Integer.valueOf((int) jFrame.getSize().getHeight())));
//
            ConstantsManager.updateConstants();
//            SettingsManager.saveSettings();
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

    public MainFrame() {
        super("minesweeper");

        ConstantsManager.updateConstants();
//        ConstantsManager.refresh();

//        int x = (int) Constant.LOCATION_X.getValue();
//        int y = (Integer) Constant.LOCATION_Y.getValue();

        setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize((Integer) Constant.WIDTH.getValue(), (Integer) Constant.HEIGHT.getValue());
        setLayout(new BorderLayout());


        System.out.println("temp");
        System.out.println(this.getLocation());

        System.out.println(this.getX());
        System.out.println(this.getY());

        addWindowListener(new MainFrameWindowListener(this));


        mainFrame = this;
//        ConstantsManager.refresh();

        northPanel = new NorthPanel();
        centerPanel = new CenterPanel();

        northPanel.addListener(event -> {
            try {
                centerPanel.restart(event.getCommand());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
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


    private final EventListenerList listenerList = new EventListenerList();

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

}
