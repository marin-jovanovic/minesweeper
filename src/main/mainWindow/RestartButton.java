package main.mainWindow;

import main.imagesModule.Image;
import main.utils.eventDrivers.Command;
import main.utils.eventDrivers.Event;
import main.utils.eventDrivers.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RestartButton extends JButton {

//    private Command command;
    private final PropertyChangeSupport support;

    private static RestartButton instance = new RestartButton();

    public static RestartButton getInstance() {
        return instance;
    }

    private RestartButton() {

        support = new PropertyChangeSupport(this);

        setIcon(Image.PLAY_AGAIN.getImageIcon());

        addActionListener(e -> {
            System.out.println("restart button clicked");


            if (CenterPanel.getInstance().isFirstButtonClicked()) {
                CenterPanel.getInstance().setFirstButtonClicked(false);
            }

            support.firePropertyChange("restart timer", null, Command.RESTART_TIMER);
            support.firePropertyChange("new game", null, Command.NEW_GAME);


            setIcon(Image.PLAY_AGAIN.getImageIcon());

        });

    }


    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
//    @Override
//    public void addPropertyChangeListener(PropertyChangeListener pcl) {
//        support.addPropertyChangeListener(pcl);
//    }


    private final EventListenerList listenerList = new EventListenerList();


    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (Object listener : listeners) {
            System.out.println(listener);
        }

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
