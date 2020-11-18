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
                fireNorthPanelEvenet(new NorthPanelEvent(this));
            }
        });



    }

    private EventListenerList listenerList = new EventListenerList();

    public void fireNorthPanelEvenet(NorthPanelEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if(listeners[i] == NorthPanelListener.class) {
                ((NorthPanelListener)listeners[i+1]).NorthPanelEventOccured(event);
            }
        }
    }

    public void addNorthPanelListener(NorthPanelListener listener) {
        listenerList.add(NorthPanelListener.class, listener);
    }

    public void removeNorthPanelListener(NorthPanelListener listener){
        listenerList.remove(NorthPanelListener.class, listener);
    }
}
