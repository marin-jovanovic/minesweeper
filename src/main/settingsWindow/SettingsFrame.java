package main.settingsWindow;

import main.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//import

public class SettingsFrame extends JFrame {

//    private NorthPanel northPanel;
//    private CenterPanel centerPanel;


    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SettingsFrame settingsFrame = new SettingsFrame();
                    settingsFrame.setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
                    settingsFrame.setVisible(true);
                    settingsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                }
            });
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    JLabel rowNumberLabel;
    JTextField rowNumberField;

    public SettingsFrame() {

        super("Settings");
        setLayout(new FlowLayout());
        setSize(Constants.WIDTH, Constants.HEIGHT);

        rowNumberLabel = new JLabel("row number:");
        add(rowNumberLabel);

        rowNumberField = new JTextField("10");
        add(rowNumberField);




        JButton btn = new JButton("save");
        add(btn);


    }

}
