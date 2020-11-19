package main;

import main.Event;
import main.Listener;
import main.settingsWindow.SettingsFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel{

    private JButton restartButton;
    private JButton settingsButton;

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

        settingsButton = new JButton("settings");

        add(settingsButton);

        settingsButton.addActionListener(event -> {
            try {
                SwingUtilities.invokeLater(() -> {
                    SettingsFrame settingsFrame = new SettingsFrame();
                    settingsFrame.setVisible(true);
//                    settingsFrame.setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
//                    settingsFrame.setVisible(true);
//                    settingsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                });
            } catch (Exception e) {
                System.out.println(e);
            }
//            catch (InvocationTargetException e) {
//                e.getTargetException().printStackTrace();
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });

    }

    public void setRestartButton(String result) {

        try {
            Image img;
            if (result.equals("gameOver")) {
                img = ImageIO.read(getClass().getResource("resources/false.jpg"));
            }
            else if (result.equals("gameWon")){
                img = ImageIO.read(getClass().getResource("resources/true.png"));
            }
            else {
                img = ImageIO.read(getClass().getResource("resources/playAgain.png"));
            }
            Image newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
            restartButton.setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            System.out.println(ex);
        }
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
