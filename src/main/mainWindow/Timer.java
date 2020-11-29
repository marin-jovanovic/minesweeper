package main.mainWindow;

import main.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Timer extends JPanel {

//    private class ImageTile extends JPanel {
////        private BufferedImage bufferedImage;
//
//        private ImageTile(String imagePath) {
//            try {
//                BufferedImage myPicture = ImageIO.read(new File(imagePath));
//                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//                add(picLabel);
//            }
//            catch (Exception exception) {
//                exception.printStackTrace();
//            }
//
//
////            add(bufferedImage);
//
//
//        }
//
//
//    }

//    ImageTile imageTile0;

    public Timer() {
//        imageTile0 = new ImageTile(Constants.RESIZED_IMAGES_PATH + Constants.TIME_IMAGES_PATH + "0"
//        + Constants.IMAGES_FORMAT);
//        add(imageTile0);

        try {

            BufferedImage myPicture = ImageIO.read(new File(Constants.RESIZED_IMAGES_PATH +
                    Constants.TIME_IMAGES_PATH + "0"
                    + Constants.IMAGES_FORMAT));

            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

//
//    public static void main(String[] args) {
//        try {
////          SwingUtilities.invokeLater(new Timer());
//        }
//        catch (Exception exception) {
//
//        }
//
//
//
//    }
}
