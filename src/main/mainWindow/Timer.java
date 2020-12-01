package main.mainWindow;

import main.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Timer extends JPanel {

    public Timer() {
        try {



            BufferedImage myPicture = ImageIO.read(new File(Constants.getPathImageTime("0")));

            JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File(Constants.getPathImageTime("0")))));
            add(picLabel);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
