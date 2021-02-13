package main.mainWindow;

import main.imagesModule.Image;
import main.settingsWindow.SettingsFrame;
import main.utils.eventDrivers.Command;
import main.utils.eventDrivers.Event;
import main.utils.eventDrivers.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel {

    private final JButton restartButton;

    public NorthPanel() {

//        TODO statistics, time

        restartButton = new JButton();

//        this is init state but it is same as play again
//        old
//        oldRestartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());

        restartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());
//        restartButton.setIcon(Image.getImageIcon(Image.PLAY_AGAIN.getPath()));


        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restart button clicked");
                fireEvent(new Event(this, Command.NEW_GAME));

                restartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());
//                restartButton.setIcon(Image.getImageIcon(Image.PLAY_AGAIN.getPath()));

            }
        });

        add(restartButton);


        JButton settingsButton = new JButton("settings");

        settingsButton.addActionListener(event -> {
            try {
                SwingUtilities.invokeLater(SettingsFrame::new);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(settingsButton);

    }

    private final EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i++) {
            System.out.println(listeners[i]);
        }

        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] instanceof Listener) {
                ((Listener) listeners[i]).eventOccurred(event);
                return;
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }

    public void setRestartButton(Command command) throws Exception {
        if (command.equals(Command.GAME_OVER)) {
            restartButton.setIcon(Image.DEFEAT.getImageIcon());

        } else if (command.equals(Command.GAME_WON)) {
            restartButton.setIcon(Image.VICTORY.getImageIcon());

        } else {
            throw new Exception("unsupported command");
        }

    }

}
