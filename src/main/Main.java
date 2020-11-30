package main;

import main.mainWindow.MainFrame;

import javax.swing.*;

public class Main {

    public static void windowLauncher() {
        SwingUtilities.invokeLater(MainFrame::new);
    }

//    TODO extract image loading to separate threads to make program faster
//    TODO time
    public static void main(String[] args) {
        windowLauncher();
    }


}
