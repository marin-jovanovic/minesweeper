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

            BufferedImage myPicture = ImageIO.read(new File(Constants.RESIZED_IMAGE_TIME_0_PATH));

            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
