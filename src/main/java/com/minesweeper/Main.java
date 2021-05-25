package com.minesweeper;

import com.minesweeper.resourceManagers.constants.Config;
import com.minesweeper.windows.index.MainFrame;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

//        create config folder if missing
        File f = new File(Config.getConstantsFolder());
        System.out.println(f.mkdir());

        SwingUtilities.invokeLater(MainFrame::new);
    }

//    TODO extract image loading to separate thread
//    TODO gui style
//    TODO change logs to json instead of ad-hoc solution

    //    FIXME
//          start/ stop button not working
//          when pressed "restart all settings" images in settings window are not restarted automatically
//          fix position on screen of mainframe and settingswindow
//          when index window is moved and settings window is opened; make sure that new window opens at same location
//          fix algorithm for game
//              first cell that is clicked must not be mine
//              check that game is solvable without brute fore
//              first cell opened must be zero
//                  dont generate any mine around that tile
//          error while changing dimensions
//          statistics graph


}
