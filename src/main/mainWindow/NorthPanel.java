package main.mainWindow;

import main.constants.Image;
import main.settingsWindow.settingsManager.SettingsManager;
import main.utils.Event;
import main.constants.Command;
import main.utils.Listener;
import main.settingsWindow.SettingsFrame;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NorthPanel extends JPanel{

    private JButton oldRestartButton;

//    private RestartButton restartButton;

    public NorthPanel() {

//        TODO statistics, time

        oldRestartButton = new JButton();

//        oldRestartButton.setIcon(new ImageIcon(ButtonStatus.INIT.getPath()));

//        this is init state but it is same as play again
        oldRestartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());

        add(oldRestartButton);

        oldRestartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restart button clicked");
                fireEvent(new Event(this, Command.NEW_GAME));
                oldRestartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());

//                oldRestartButton.setIcon(new ImageIcon(ButtonStatus.PLAY_AGAIN.getPath()));
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

        temp = new JButton(Image.CLOSED_CELL.getImageIcon());
        add(temp);

        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SettingsManager.processNewImage(
                        new File("C:\\git\\minesweeper\\src\\main\\resources\\images\\custom\\button\\victory.png"),
                        Image.CLOSED_CELL
                );

                temp.setIcon(Image.CLOSED_CELL.getImageIcon());


            }
        });



    }

    private JButton temp;

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

    public void setRestartButton(Command command) throws Exception {
        if (command.equals(Command.GAME_OVER)) {
            oldRestartButton.setIcon(Image.DEFEAT.getImageIcon());

//            oldRestartButton.setIcon(new ImageIcon(ButtonStatus.DEFEAT.getPath()));
        } else if (command.equals(Command.GAME_WON)) {
            oldRestartButton.setIcon(Image.VICTORY.getImageIcon());

//            oldRestartButton.setIcon(new ImageIcon(ButtonStatus.VICTORY.getPath()));
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

//    private void buttonSetIcon(Image buttonStatus) {
//        oldRestartButton.setIcon(buttonStatus.getImageIcon());
////            oldRestartButton.setIcon( new ImageIcon( defeat.getPath()));
//
//    }


}
