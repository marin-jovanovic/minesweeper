package main;

import main.mainWindow.MainFrame;

import javax.swing.*;

public class Main {

    public static void windowLauncher() {
        SwingUtilities.invokeLater(MainFrame::new);
    }

//    TODO extract image loading to separate threads to make program faster
//    TODO time
//    TODO gui style

    //    FIXME
//        1. when pressed "restart all settings" images in settings window are not restarted automatically
//        2. fix position on screen of mainframe and settingswindow


    public static void main(String[] args) {
        windowLauncher();

//        SwingUtilities.invokeLater(TestWindow::new);
    }

//    private static class TestWindow extends JFrame {
//        public TestWindow() {
//
//            setLocation(0, 0);
//            setVisible(true);
//            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            setSize(1000, 1000);
//            setLayout(new BorderLayout());
//
//            BufferedImage bi = loadImage("/main/resources/images/custom/opened_tiles/-1.png");
//
//            JButton oldRestartButton = new JButton();
//
//            oldRestartButton.setIcon(new ImageIcon(bi));
//
//            add(oldRestartButton);
//        }
//
//        public static BufferedImage loadImage(String path){
//            try {
//                return ImageIO.read(Loader.class.getResource(path));
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.exit(1);
//            }
//            return null;
//
//        }
//
//    }



}
