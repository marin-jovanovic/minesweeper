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

    private JButton oldRestartButton;

    private RestartButton restartButton;

    public NorthPanel() {

        Timer timer = new Timer();
        add(timer);

//        TODO statistics, time

//
//        restartButton = new RestartButton();
//        restartButton.changeIcon("init");
//        add(restartButton);


//        addListener(event -> centerPanel.restart(event.getCommand()));


        oldRestartButton = new JButton();
        changeIcon("init");
        add(oldRestartButton);

        oldRestartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restart button clicked");
                fireEvent(new main.Event(this, "new game"));
                changeIcon("playAgain");
            }
        });




//        this.addListener(event -> {
//
//            System.out.println("event");
//            System.out.println(event);
//            System.out.println();
//
////            MainFrame.restartSequence();
//
//            System.out.println("restart tiles");
//
//
//        });
//
//        this.addListener(new Listener() {
//            @Override
//            public void eventOccurred(Event event) {
//                System.out.println("event");
//                System.out.println(event);
//                System.out.println();
//            }
//        });

        JButton settingsButton = new JButton("settings");

        settingsButton.addActionListener(event -> {
            try {
                SwingUtilities.invokeLater(SettingsFrame::new);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(settingsButton);
    }

//    private SettingsFrame settingsFrame;

    public void changeIcon(String result) {
        try {
            ImageIcon img;
//
//            String retString;
//
//            switch(result) {
//
//                case "gameOver":
//                    retString = "false";
//                    break;
//
//                case "gameWon":
//                    retString = "true";
//                    break;
//
//                default:
//                    retString = "playAgain";
//            }
//
//            img = new ImageIcon(Constants.RESIZED_IMAGES_PATH + retString +
//                    "." + Constants.IMAGES_FORMAT_NAME);


            if (result.equals("gameOver")) {
                img = new ImageIcon(Constants.RESIZED_IMAGE_BUTTON_DEFEAT_PATH);
                oldRestartButton.setIcon(img);
            }
            else if (result.equals("gameWon")) {
                img = new ImageIcon(Constants.RESIZED_IMAGE_BUTTON_VICTORY_PATH);
                oldRestartButton.setIcon(img);
            }
            else {
                img = new ImageIcon(Constants.RESIZED_IMAGE_BUTTON_PLAY_AGAIN_PATH);
                oldRestartButton.setIcon(img);
            }



            oldRestartButton.setIcon(img);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i++) {
            System.out.println(listeners[i]);
        }


        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] instanceof Listener) {
                ((Listener)listeners[i]).eventOccurred(event);
                return;
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }
}
