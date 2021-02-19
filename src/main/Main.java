package main;

import main.windows.index.MainFrame;

import javax.swing.*;

public class Main {

//    fixme
//      error while changing dimensions

    public static void windowLauncher() {
//        SettingsWindowListener settingsWindowListener = SettingsWindowListener.getInstance();
//
//        settingsWindowListener.addListener(MainFrame);

        SwingUtilities.invokeLater(MainFrame::new);
    }

//    TODO extract image loading to separate threads to make program faster
//    TODO time
//    TODO gui style

    //    FIXME
//          1. when pressed "restart all settings" images in settings window are not restarted automatically
//          2. fix position on screen of mainframe and settingswindow


    public static void main(String[] args) {
        windowLauncher();

//        SwingUtilities.invokeLater(TestWindow::new);
    }

}
