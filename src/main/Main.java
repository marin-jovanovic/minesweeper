package main;

import javax.swing.*;

public class Main {

    public static void windowLauncher() {

        try {
            SwingUtilities.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
                mainFrame.setVisible(true);
                mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            });
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

//    TODO extract image loading to separate threads to make program faster
//    TODO pre-crop all images to default size
//    TODO settings window
    public static void main(String[] args) {
        System.out.println("main loading");
        windowLauncher();
    }


}
