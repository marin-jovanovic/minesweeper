package main.mainWindow;

import main.constants.Constants;
import main.utils.Event;
import main.constants.LayoutConstants;
import main.utils.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;

public class MainFrame extends JFrame {

    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;

    public static MainFrame mainFrame;


    public MainFrame() {
        super("minesweeper");
        setLocation(LayoutConstants.LOCATION_X, LayoutConstants.LOCATION_Y);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(LayoutConstants.WIDTH, LayoutConstants.HEIGHT);
        setLayout(new BorderLayout());

        this.mainFrame = this;
        Constants.refresh();

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
