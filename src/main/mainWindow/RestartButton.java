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
            this.setIcon(new ImageIcon(Constants.getPathImageButton(result)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private final EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (Object listener : listeners) {
            if (listener instanceof Listener) {
                ((Listener) listener).eventOccurred(event);
                return;
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }
}
