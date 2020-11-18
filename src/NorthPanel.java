import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.EventObject;

public class NorthPanel extends JPanel{

    private JButton restartButton;

    public NorthPanel() {
//        TODO statistics, time

        restartButton = new JButton();

        try {
            Image img = ImageIO.read(getClass().getResource("resources/playAgain.png"));
            Image newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
            restartButton.setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        add(restartButton);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restart button clicked");
                fireEvent(new Event(this, "none"));
            }
        });



    }

//    true if game is won
//    false if stepped on mine
    public void setRestartButton(boolean result) {

        try {
            Image img;
            if (result) {
                img = ImageIO.read(getClass().getResource("resources/true.png"));
            }
            else {
                img = ImageIO.read(getClass().getResource("resources/false.jpg"));
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
