package main.mainWindow;

import main.Constants;
import main.Event;
import main.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;

    public MainFrame() {
        super("minesweeper");

        this.mainFrame = this;

        setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLayout(new BorderLayout());

        northPanel = new NorthPanel();
        centerPanel = new CenterPanel();

        northPanel.addListener(event -> centerPanel.restart());

//        getCommand:
//            gameOver
//            gameWon
        centerPanel.addListener(event -> northPanel.setRestartButton(event.getCommand()));

//        northPanel.addListener(event -> System.out.println("$$$$$"+event));

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


//        JButton btn = new JButton();
//        add(btn, BorderLayout.SOUTH);
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                restartSequence();
//            }
//        });

    }

    public static MainFrame mainFrame;

    public static void restartSequence() {
        mainFrame.dispose();
        new MainFrame();
    }


    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {


            if(listeners[i] == Listener.class) {
                ((Listener)listeners[i+1]).EventOccured(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }

}
