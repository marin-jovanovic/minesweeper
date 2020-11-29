package main.mainWindow;

import main.Constants;
import main.Event;
import main.utils.Listener;
import main.settingsWindow.SettingsFrame;

//import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel{

    private JButton restartButton;

    public NorthPanel() {
//        TODO statistics, time

        restartButton = new JButton();

        setRestartButton("init");

        add(restartButton);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restart button clicked");
                fireEvent(new main.Event(this, "none"));
                setRestartButton("playAgain");
            }
        });

        JButton settingsButton = new JButton("settings");

        add(settingsButton);

        this.addListener(event -> MainFrame.restartSequence());

//        JButton btn = new JButton();
//        add(btn, BorderLayout.SOUTH);
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                MainFrame.restartSequence();
//                fireEvent(new main.Event(this, "new"));
//            }
//        });

        settingsButton.addActionListener(event -> {
            try {
                SwingUtilities.invokeLater(SettingsFrame::new);

//                SwingUtilities.invokeAndWait(SettingsFrame::new);
//                SettingsFrame settingsFrame = new SettingsFrame();
//                SwingUtilities.invokeAndWait((Runnable) settingsFrame);
            }
//            catch ()

            catch (Exception e) {
                e.printStackTrace();
            }
//            fireEvent(new main.Event(this, "new"));

        });



    }
//
//    public void restartSequence() {
//        MainFrame.restartSequence();
//    }



    private SettingsFrame settingsFrame;


    public void setRestartButton(String result) {
        try {
            ImageIcon img;

            if (result.equals("gameOver")) {
                img = new ImageIcon(Constants.PICTURES_PATH + "false" + Constants.PICTURES_FORMAT);
            }
            else if (result.equals("gameWon")){
                img = new ImageIcon(Constants.PICTURES_PATH + "true" + Constants.PICTURES_FORMAT);
            }
            else {
                img = new ImageIcon(Constants.PICTURES_PATH + "playAgain" + Constants.PICTURES_FORMAT);
            }
            restartButton.setIcon(img);
        } catch (Exception ex) {
//            System.out.println(ex);
            ex.printStackTrace();
        }
    }


    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] instanceof Listener) {
                ((Listener)listeners[i]).eventOccurred(event);
            }
        }




//        for (int i = 0; i < listeners.length; i += 2) {
//            if(listeners[i] == Listener.class) {
//                ((Listener)listeners[i+1]).EventOccured(event);
//            }
//        }



    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }
}
