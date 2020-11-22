package main.mainWindow;

import main.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final NorthPanel northPanel;
    private final CenterPanel centerPanel;

    public MainFrame() {
        super("minesweeper");

        setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLayout(new BorderLayout());

        northPanel = new NorthPanel();
        centerPanel = new CenterPanel();

        northPanel.addListener(event -> centerPanel.restart());
        centerPanel.addListener(event -> northPanel.setRestartButton(event.getCommand()));


//        centerPanel.addListener(event -> {
//
////            System.out.println(event.getCommand());
//
//            northPanel.setRestartButton(event.getCommand());
//
////            if (event.getCommand().equals("gameOver")) {
////                northPanel.setRestartButton("gameOver");
////            }
////            else {
////                northPanel.setRestartButton("gameWon");
////            }
//
//        });

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

    }

}
