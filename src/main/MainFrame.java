package main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private NorthPanel northPanel;
    private CenterPanel centerPanel;

    public MainFrame() {
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLayout(new BorderLayout());

        northPanel = new NorthPanel();
        centerPanel = new CenterPanel();

        northPanel.addListener(event -> centerPanel.restart());

        centerPanel.addListener(event -> {

            System.out.println(event.getCommand());

            if (event.getCommand().equals("gameOver")) {
                northPanel.setRestartButton("gameOver");
            }
            else {
                northPanel.setRestartButton("gameWon");
            }

        });

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


    }

}
