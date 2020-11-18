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

        northPanel.addNorthPanelListener(event -> centerPanel.restart());

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


    }

}
