package main.settingsWindow;

import main.constants.GeneralConstants;
import main.utils.Event;
import main.constants.LayoutConstants;
import main.utils.Listener;
import main.mainWindow.MainFrame;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private JLabel rowNumberLabel;
    private JTextField rowNumberField;
    private static String rowNumber;
    private JLabel rowNumberChecker;

    private JLabel columnNumberLabel;
    private JTextField columnNumberField;
    private static String columnNumber;
    private JLabel columnNumberChecker;

    private JLabel mineNumberLabel;
    private JTextField mineNumberField;
    private static String mineNumber;
    private JLabel getMineNumberChecker;

    static private String newline = "\n";
    private JTextArea log;
    private JFileChooser fc;

    public void setSettingsFrame(SettingsFrame origin) {
        this.origin = origin;
    }

//    public static void main(String[] args) {
//        try {
//            SwingUtilities.invokeLater(SettingsFrame::new);
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

    public static String getRowNumber() {
        return rowNumber;
    }

    public static String getColumnNumber() {
        return columnNumber;
    }

    public static void setRowNumber(String rowNumber) {
        SettingsFrame.rowNumber = rowNumber;
    }

    public static void setColumnNumber(String columnNumber) {
        SettingsFrame.columnNumber = columnNumber;
    }

    public static void setMineNumber(String mineNumber) {
        SettingsFrame.mineNumber = mineNumber;
    }


    public static class BoxLayoutDemo {
        public static void addComponentsToPane(Container pane) {
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

            addAButton("Button 1", pane);
            addAButton("Button 2", pane);
            addAButton("Button 3", pane);
            addAButton("Long-Named Button 4", pane);
            addAButton("5", pane);
        }

        private static void addAButton(String text, Container container) {
            JButton button = new JButton(text);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(button);

            JTextField jTextField = new JTextField("lalala");
            container.add(jTextField);
        }

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private static void createAndShowGUI() {
            //Create and set up the window.
            JFrame frame = new JFrame("BoxLayoutDemo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Set up the content pane.
            addComponentsToPane(frame.getContentPane());

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        }


    }
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BoxLayoutDemo.createAndShowGUI();
            }
        });
    }
    public SettingsFrame() {
//        super("Settings");

        this.origin = this;

        setSize(LayoutConstants.WIDTH, LayoutConstants.HEIGHT);
        setLocation(LayoutConstants.LOCATION_X, LayoutConstants.LOCATION_Y);
        setVisible(true);
        setLayout(new GridLayout(0, 3));

//        setLayout(new BoxLayout(this,
//                BoxLayout.PAGE_AXIS));
        //        settingsFrame.setVisible(true);

//        1. row
//        row
        row1();

//        2. row
//        column
        row2();

//        3. row
//        mines
        row3();


        this.addListener(event -> MainFrame.restartSequence());

//        saves on close new settings
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                writeToSettings();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("window closed");
            }

//            minimized
            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("window iconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("window de iconified");
            }

//            alt tab
            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("window activated");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("window deactivated");
            }
        });


//Create the log first, because the action listener
        //needs to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        JButton sendButton = new JButton("Attach...");
//        sendButton.addActionListener(this);

        sendButton.addActionListener(this::actionPerformed);

        add(sendButton);
        add(logScrollPane);
    }

    private void writeToSettings() {
        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(GeneralConstants.SETTINGS_MEMORY_PATH)

//            BufferedReader file = new BufferedReader(
//                    new FileReader("src/main/resources/settings.txt")
            );
            String line;
            List<String> l = new ArrayList<>();

//                    adds to l -> number of + k + number
//                    checks exceptions and handles if not int
            checkAndAddLine(file, l, "rows", rowNumber);
            checkAndAddLine(file, l, "columns", columnNumber);
            checkAndAddLine(file, l, "mines", mineNumber);



//            reads rest of file and copy to l
            while ((line = file.readLine()) != null) {
                l.add(line);
            }
            file.close();

            String result = String.join("\n", l);
            System.out.println(result);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(GeneralConstants.SETTINGS_MEMORY_PATH);
            fileOut.write(result.getBytes());
            fileOut.close();

            GeneralConstants.refresh();
//                        FIXME this bellow must be part of the code
//                    fireEvent(new main.utils.Event(this, "setting saved"));
//                    fireEvent(new main.utils.Event(origin, "settingsChanged"));

        } catch (Exception er) {
            System.out.println("Problem reading file.");
            er.printStackTrace();
        }
    }

    //    for adding lines to settings.txt
    private void checkAndAddLine(BufferedReader file, List<String> l, String k, String targ) throws IOException {
        try {
            l.add("number of " + k + " = " + Integer.parseInt(targ));
            file.readLine();
        }
        catch (NumberFormatException exception) {
            if (exception.getMessage().equals("null")) {
                l.add(file.readLine());
            }
            else {
                System.out.println(exception);

                String replacement = Arrays
                        .stream(targ.split(""))
                        .filter(s -> "0123456789".contains(s))
                        .collect(Collectors.joining());

                l.add("number of " + k + " = " + replacement);
                file.readLine();
            }
        }
        catch (Exception e) {
            System.err.println(e);
            System.out.println("check and add line in settings frame");
            l.add(file.readLine());
        }
    }

    class SettingsTextField extends JPanel{

        JLabel jLabel;
        JTextField jTextField;


        SettingsTextField(String label) {
            this.jLabel = new JLabel(label);
            add(this.jLabel);

            this.jTextField = new JTextField();

//          TODO action listener for text field
//             add content of text field to buffer which will be saved and updated after closing settings

            add(jTextField);

        }
    }

    private void row1() {
        rowNumberLabel = new JLabel("row number:");
        add(rowNumberLabel);

        rowNumberField = new JTextField(GeneralConstants.NUMBER_OF_ROWS);
        add(rowNumberField);

        TextFieldActionListener textFieldActionListener =
                new TextFieldActionListener(rowNumberField, "rowNumber");

        textFieldActionListener.addListener(event -> {

            System.out.println(event.getCommand());

            if (event.getCommand().equals("columnNumber emptyCell")) {
//                northPanel.setRestartButton("gameOver");
                System.out.println("empty cell");

            }
            else {
                System.out.println("neki drugi fire event");
//                northPanel.setRestartButton("gameWon");
            }

        });


        rowNumberField.getDocument().addDocumentListener(
                textFieldActionListener
        );

        rowNumberChecker = new JLabel();
        add(rowNumberChecker);
    }

    private void row2() {
        columnNumberLabel = new JLabel(("column number:"));
        add(columnNumberLabel);

        columnNumberField = new JTextField(GeneralConstants.NUMBER_OF_COLUMNS);
        add(columnNumberField);

        columnNumberField.getDocument().addDocumentListener(
                new TextFieldActionListener(columnNumberField, "columnNumber")
        );

        columnNumberChecker = new JLabel();
        add(columnNumberChecker);
    }

    private void row3() {
        mineNumberLabel = new JLabel(("mine number:"));
        add(mineNumberLabel);

        mineNumberField = new JTextField(GeneralConstants.NUMBER_OF_MINES);
        add(mineNumberField);

        mineNumberField.getDocument().addDocumentListener(
                new TextFieldActionListener(mineNumberField, "mineNumber")
        );

        columnNumberChecker = new JLabel();
        add(columnNumberChecker);
    }

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
