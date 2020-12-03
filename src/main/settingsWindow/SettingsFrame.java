package main.settingsWindow;

import main.constants.GeneralConstants;
import main.settingsWindow.elements.imagePicker.ImageFileView;
import main.settingsWindow.elements.imagePicker.ImageFilter;
import main.settingsWindow.elements.imagePicker.ImagePreview;
import main.settingsWindow.elements.textField.TextFieldElement;
import main.utils.Event;
import main.constants.LayoutConstants;
import main.utils.Listener;
import main.mainWindow.MainFrame;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

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

public class SettingsFrame extends JFrame {

//    frow where was this frame launched
    private SettingsFrame origin;

//
//    private static String rowNumber;
//    private static String columnNumber;
//    private static String mineNumber;

    //    private JLabel rowNumberLabel;
//    private JTextField rowNumberField;
//    private JLabel rowNumberChecker;

//    private JLabel columnNumberLabel;
//    private JTextField columnNumberField;
//    private JLabel columnNumberChecker;

//    private JLabel mineNumberLabel;
//    private JTextField mineNumberField;
//    private JLabel getMineNumberChecker;

    static private String newline = "\n";
    private JTextArea log;
    private JFileChooser fc;

    public void setSettingsFrame(SettingsFrame origin) {
        this.origin = origin;
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(SettingsFrame::new);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

//    public static String getRowNumber() {
//        return rowNumber;
//    }
//
//    public static String getColumnNumber() {
//        return columnNumber;
//    }
//
//    public static void setRowNumber(String rowNumber) {
//        SettingsFrame.rowNumber = rowNumber;
//    }
//
//    public static void setColumnNumber(String columnNumber) {
//        SettingsFrame.columnNumber = columnNumber;
//    }
//
//    public static void setMineNumber(String mineNumber) {
//        SettingsFrame.mineNumber = mineNumber;
//    }


//    public static class BoxLayoutDemo {
//        public static void addComponentsToPane(Container pane) {
//            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
//
//            addAButton("Button 1", pane);
//            addAButton("Button 2", pane);
//            addAButton("Button 3", pane);
//            addAButton("Long-Named Button 4", pane);
//            addAButton("5", pane);
//        }
//
//        private static void addAButton(String text, Container container) {
//
//
//
//            JButton button = new JButton(text);
//            button.setAlignmentX(Component.CENTER_ALIGNMENT);
//            container.add(button);
//
//            JTextField jTextField = new JTextField("lalala");
//            container.add(jTextField);
//        }
//
//        /**
//         * Create the GUI and show it.  For thread safety,
//         * this method should be invoked from the
//         * event-dispatching thread.
//         */
//        private static void createAndShowGUI() {
//            //Create and set up the window.
//            JFrame frame = new JFrame("BoxLayoutDemo");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            //Set up the content pane.
//            addComponentsToPane(frame.getContentPane());
//
//            //Display the window.
//            frame.pack();
//            frame.setVisible(true);
//        }
//
//
//    }
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                BoxLayoutDemo.createAndShowGUI();
//            }
//        });
//    }



    public SettingsFrame() {
//        super("Settings");
        setSize(LayoutConstants.WIDTH, LayoutConstants.HEIGHT);
        setLocation(LayoutConstants.LOCATION_X, LayoutConstants.LOCATION_Y);
        setVisible(true);
        setLayout(new GridLayout(0, 3));
//        setLayout(new BoxLayout(this,
//                BoxLayout.PAGE_AXIS));
        //        settingsFrame.setVisible(true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));


        this.origin = this;


        TextFieldElement row = new TextFieldElement(
                "row number:", String.valueOf(GeneralConstants.NUMBER_OF_ROWS)
        );
        add(row);

        TextFieldElement columnField = new TextFieldElement(
                "column number:" , String.valueOf(GeneralConstants.NUMBER_OF_COLUMNS)
        );
        add(columnField);

        TextFieldElement mines = new TextFieldElement(
                "mine number:", String.valueOf(GeneralConstants.NUMBER_OF_MINES)
        );
        add(mines);

        this.addListener(event -> MainFrame.restartSequence());

//        saves on close new settings
        addWindowListener(new SettingsWindowListener());

//        addWindowListener(new WindowListener() {
//
//            @Override
//            public void windowOpened(WindowEvent e) {
//            }
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//                writeToSettings();
//            }
//
//            @Override
//            public void windowClosed(WindowEvent e) {
//                System.out.println("window closed");
//            }
//
////            minimized
//            @Override
//            public void windowIconified(WindowEvent e) {
//                System.out.println("window iconified");
//            }
//
//            @Override
//            public void windowDeiconified(WindowEvent e) {
//                System.out.println("window de iconified");
//            }
//
////            alt tab
//            @Override
//            public void windowActivated(WindowEvent e) {
//                System.out.println("window activated");
//            }
//
//            @Override
//            public void windowDeactivated(WindowEvent e) {
//                System.out.println("window deactivated");
//            }
//        });



        log = new JTextArea(5,20);

//Create the log first, because the action listener
        //needs to refer to it.
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        JButton sendButton = new JButton("Attach...");
//        sendButton.addActionListener(this);

        sendButton.addActionListener(this::actionPerformed);

        add(sendButton);
        add(logScrollPane);
    }

//    used for images
    public void actionPerformed(ActionEvent e) {
        //Set up the file chooser.
        if (fc == null) {
            fc = new JFileChooser();

            //Add a custom file filter and disable the default
            //(Accept All) file filter.
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);

            //Add custom icons for file types.
            fc.setFileView(new ImageFileView());

            //Add the preview pane.
            fc.setAccessory(new ImagePreview(fc));
        }

        //Show it.
        int returnVal = fc.showDialog(main.settingsWindow.SettingsFrame.this,"Attach");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            log.append("Attaching file: " + file.getName() + "." + newline);
        } else {
            log.append("Attachment cancelled by user." + newline);
        }
        log.setCaretPosition(log.getDocument().getLength());

        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    }


    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {


            if(listeners[i] == Listener.class) {
                ((Listener)listeners[i+1]).eventOccurred(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }
}
