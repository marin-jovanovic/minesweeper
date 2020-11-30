package main.mainWindow;

import main.Constants;
import main.Event;
import main.utils.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButton extends JButton {

    public RestartButton() {

        addActionListener(new RestartButtonActionListener());
//        addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("restart button clicked");
//                fireEvent(new main.Event(this, "none"));
//                changeIcon("playAgain");
//            }
//        });

    }


    public class RestartButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("restart button clicked");
            fireEvent(new main.Event(this, "new game"));
            changeIcon("playAgain");
        }
    }

    public void changeIcon(String result) {
        try {
            ImageIcon img;


//            String retString;
//
//            switch(result) {
//
//                case "gameOver":
//                    retString = "false";
////                    img = new ImageIcon(Constants.RESIZED_IMAGES_PATH + "false" + Constants.IMAGES_FORMAT);
//                    break;
//
//                case "gameWon":
//                    retString = "true";
////                    img = new ImageIcon(Constants.RESIZED_IMAGES_PATH + "true" + Constants.IMAGES_FORMAT);
//                    break;
//
//                default:
//                    retString = "playAgain";
////                    img = new ImageIcon(Constants.RESIZED_IMAGES_PATH + "playAgain" + Constants.IMAGES_FORMAT);
//            }
//
//            img = new ImageIcon(Constants.RESIZED_IMAGES_PATH + retString +
//                    "." + Constants.IMAGES_FORMAT_NAME);

            if (result.equals("gameOver")) {
                img = new ImageIcon(Constants.RESIZED_IMAGE_BUTTON_DEFEAT_PATH);
                this.setIcon(img);

            }
            else if (result.equals("victory")) {
                img = new ImageIcon(Constants.RESIZED_IMAGE_BUTTON_VICTORY_PATH);
                this.setIcon(img);

            }
            else if (result.equals("playAgain")) {
                img = new ImageIcon(Constants.RESIZED_IMAGE_BUTTON_PLAY_AGAIN_PATH);
                this.setIcon(img);

            }

//            FIXME what if none of above is true

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

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
