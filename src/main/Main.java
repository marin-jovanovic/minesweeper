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

//    fixme
//      when game is won right click operations are still enabled
//      check what happens when game over

    //    FIXME
//          1. when pressed "restart all settings" images in settings window are not restarted automatically
//          2. fix position on screen of mainframe and settingswindow


    public static void main(String[] args) {
        windowLauncher();
//        SwingUtilities.invokeLater(TestWindow::new);
    }

}
