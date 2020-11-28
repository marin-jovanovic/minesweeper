package main;

import main.mainWindow.MainFrame;

import javax.swing.*;

public class Main {

    public static void windowLauncher() {
        SwingUtilities.invokeLater(MainFrame::new);

    }

//    TODO extract image loading to separate threads to make program faster
//    TODO settings window
//    TODO time
//    TODO right click flag
    public static void main(String[] args) {
        System.out.println("main loading");
        windowLauncher();
    }


}
