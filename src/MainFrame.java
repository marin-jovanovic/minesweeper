import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class MainFrame extends JFrame {

    private NorthPanel northPanel;
    private CenterPanel centerPanel;

    public MainFrame() {
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLayout(new BorderLayout());

        northPanel = new NorthPanel();
        centerPanel = new CenterPanel();

//        northPanel.addNorthPanelListener(event -> centerPanel.restart());
        northPanel.addListener(event -> centerPanel.restart());
        centerPanel.addListener(new Listener() {
            @Override
            public void EventOccured(Event event) {
//                System.out.println(event.getSource());
//                System.out.println(event);
                System.out.println(event.getCommand());

                if (event.getCommand().equals("gameOver")) {
                    northPanel.setRestartButton("gameOver");
                } else {
                    northPanel.setRestartButton("gameWon");
                }

            }
        });

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


    }

}
