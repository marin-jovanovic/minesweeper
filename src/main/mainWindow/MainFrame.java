package main.mainWindow;

import main.constants.ConstantsManager;
import main.utils.Event;
import main.constants.LayoutConstants;
import main.utils.Listener;

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

        private JFrame jFrame;

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
        setLocation(LayoutConstants.LOCATION_X, LayoutConstants.LOCATION_Y);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(LayoutConstants.WIDTH, LayoutConstants.HEIGHT);
        setLayout(new BorderLayout());

        System.out.println("temp");
        System.out.println(this.getLocation());

        System.out.println(this.getX());
        System.out.println(this.getY());

        addWindowListener(new MainFrameWindowListener(this));

//        addWindowListener(new WindowListener() {
//
//            @Override
//            public void windowOpened(WindowEvent e) {
//            }
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.out.println();
//            }
//
//            @Override
//            public void windowClosed(WindowEvent e) {
//                System.out.println("window closed");
//            }
//
//            //            minimized
//            @Override
//            public void windowIconified(WindowEvent e) {
//                System.out.println("window iconified");
//            }
//
//            @Override
//            public void windowDeiconified(WindowEvent e) {
//                System.out.println("window de iconified");
//            }
//
//
//            //            alt tab
//            @Override
//            public void windowActivated(WindowEvent e) {
//                System.out.println("window activated");
//            }
//
//            @Override
//            public void windowDeactivated(WindowEvent e) {
//                System.out.println("window deactivated");
//            }
//        });



        this.mainFrame = this;
        ConstantsManager.refresh();

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


    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if(listeners[i] == Listener.class) {
                ((Listener)listeners[i+1]).eventOccurred(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }

}
