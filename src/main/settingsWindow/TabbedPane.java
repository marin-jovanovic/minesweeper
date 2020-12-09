package main.settingsWindow;


/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


/*
 * TabbedPaneDemo.java requires one additional file:
 *   images/middle.gif.
 */

import main.constants.ConstantsManager;
import main.constants.GeneralConstant;
import main.constants.imageDrivers.OpenedTileStatus;
import main.settingsWindow.elements.textField.Linker;
import main.settingsWindow.elements.textField.TextFieldElement;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class TabbedPane extends JPanel {
//
//    public TabbedPane() {
//        super(new GridLayout(1, 1));
//
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//
//        tabbedPane.addTab("General", new GeneralPanel());
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//
//
//        tabbedPane.addTab("Images",  new ImagesPanel());
//        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
//
//
//        tabbedPane.addTab("Sound", new SoundPanle());
//        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
//
//        //Add the tabbed pane to this panel.
//        add(tabbedPane);
//
//        //The following line enables to use scrolling tabs.
//        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//    }


    public static class SoundPanle extends JPanel {
        public SoundPanle() {
            add(new JButton("todo soudn"));
        }
    }


    //    Imagepickerelement
    public static class ImagesPanel extends JPanel {

//        public ImagesPanel() {
////            add(new JButton("todo"));
//
//            setLayout(new GridLayout(1, 1));
//            JTabbedPane tabbedPane = new JTabbedPane();
//
////
////            tabbedPane.addTab("General", new TabbedPane.GeneralPanel());
////            tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
////
////
////            tabbedPane.addTab("Images",  new TabbedPane.ImagesPanel());
////            tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
////
////
////            tabbedPane.addTab("Sound", new TabbedPane.SoundPanle());
////            tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
//
////            tabbedPane.addTab("1", new Image);
//
//
//            //The following line enables to use scrolling tabs.
//            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//
//            //Add the tabbed pane to this panel.
//            add(tabbedPane);
//
//        }
//    }

        public static class ImagesClosedTiles extends JPanel {
            public ImagesClosedTiles() {
                add(new JButton("todo2"));
            }
        }

        public static class ImagesButton extends JPanel {
            public ImagesButton() {
                add(new JButton("todo1"));
            }
        }

//    public static class GeneralPanel extends JPanel {
//
//        public GeneralPanel() {
//
//            add(new TextFieldElement(Linker.ROW_NUMBER.getFrontEnd(), String.valueOf(ConstantsManager.NUMBER_OF_ROWS)));
//            add(new TextFieldElement(Linker.COLUMN_NUMBER.getFrontEnd(),  String.valueOf(ConstantsManager.NUMBER_OF_COLUMNS)));
//            add(new TextFieldElement(Linker.MINE_NUMBER.getFrontEnd(), String.valueOf(ConstantsManager.NUMBER_OF_MINES)));
//
//        }
//    }


//    protected JComponent makeTextPanel(String text) {
//        JPanel panel = new JPanel(false);
//        JLabel filler = new JLabel(text);
//        filler.setHorizontalAlignment(JLabel.CENTER);
//        panel.setLayout(new GridLayout(1, 1));
//        panel.add(filler);
//        return panel;
//    }

//    /** Returns an ImageIcon, or null if the path was invalid. */
//    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = TabbedPane.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }
//


        public static void main(String[] args) {
            //Schedule a job for the event dispatch thread:
            //creating and showing this application's GUI.
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //Turn off metal's use of bold fonts
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
//                createAndShowGUI();
                    //Create and set up the window.
                    JFrame frame = new JFrame("TabbedPaneDemo");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    //Add content to the window.
                    frame.add(new TabbedPane(), BorderLayout.CENTER);

                    //Display the window.
                    frame.pack();
                    frame.setVisible(true);

                }
            });
        }
    }
}