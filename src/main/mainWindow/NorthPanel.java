package main.mainWindow;

import main.*;
import main.Event;
import main.constants.Commands;
import main.utils.Listener;
import main.settingsWindow.SettingsFrame;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel{

    private JButton oldRestartButton;

//    private RestartButton restartButton;

    public NorthPanel() {

//        TODO statistics, time

        oldRestartButton = new JButton();

        oldRestartButton.setIcon(new ImageIcon(ButtonStatus.INIT.getPath()));

        add(oldRestartButton);

        oldRestartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restart button clicked");
                fireEvent(new main.Event(this, Commands.NEW_GAME));

                oldRestartButton.setIcon(new ImageIcon(ButtonStatus.PLAY_AGAIN.getPath()));
            }
        });

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

    public void setRestartButton(Commands command) throws Exception {
        if (command.equals(Commands.GAME_OVER)) {
            oldRestartButton.setIcon(new ImageIcon(ButtonStatus.DEFEAT.getPath()));
        } else if (command.equals(Commands.GAME_WON)) {
            oldRestartButton.setIcon(new ImageIcon(ButtonStatus.VICTORY.getPath()));
        } else {
            throw new Exception("unsupported command");
        }
//
//        if (command.equals("gameOver")) {
////            oldRestartButton.setIcon( new ImageIcon( defeat.getPath()));
//
//            buttonSetIcon(ButtonStatus.DEFEAT);
//        } else if (command.equals("gameWon")) {
//            buttonSetIcon(ButtonStatus.VICTORY);
//        }


    }

    private void buttonSetIcon(ButtonStatus defeat) {
            oldRestartButton.setIcon( new ImageIcon( defeat.getPath()));

    }


}
