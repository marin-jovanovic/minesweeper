package main.settingsWindow;

import main.Constants;

import javax.swing.*;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
//import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(SettingsFrame::new);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    JLabel rowNumberLabel;
    JTextField rowNumberField;
    private static String rowNumber;
    JLabel rowNumberChecker;


    JLabel columnNumberLabel;
    JTextField columnNumberField;
    private static String columnNumber;

    static private String newline = "\n";
    private JTextArea log;
    private JFileChooser fc;


//    public static String getRowNumber() {
//        return rowNumber;
//    }
//    public static String getColumnNumber() {
//        return columnNumber;
//    }
    public static void setRowNumber(String rowNumber) {
        SettingsFrame.rowNumber = rowNumber;
    }
    public static void setColumnNumber(String columnNumber) {
        SettingsFrame.columnNumber = columnNumber;
    }

    public SettingsFrame() {

        super("Settings");
        setLayout(new FlowLayout());
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
        setVisible(true);
//        TODO on close save data
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 3));

//        1. row
        rowNumberLabel = new JLabel("row number:");
        add(rowNumberLabel);

        rowNumberField = new JTextField(Constants.NUMBER_OF_ROWS);
        add(rowNumberField);

        rowNumberField.getDocument().addDocumentListener(
                new TextFieldActionListener(rowNumberField, "rowNumber")
        );

        rowNumberChecker = new JLabel();
        add(rowNumberChecker);




//        2. row
        columnNumberLabel = new JLabel(("column number:"));
        add(columnNumberLabel);

        columnNumberField = new JTextField(Constants.NUMBER_OF_COLUMNS);
        add(columnNumberField);

        columnNumberField.getDocument().addDocumentListener(
                new TextFieldActionListener(columnNumberField, "columnNumber")
        );

        addWindowListener(new WindowListener() {

//            opening
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("window opened");
            }

//            closing events
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("window closing");

                try {
                    BufferedReader file = new BufferedReader(
                            new FileReader("src/main/resources/settings.txt")
                    );
                    String line;
                    List<String> l = new ArrayList<>();

//                    int counter = 3;
                    file.readLine();
                    file.readLine();
                    file.readLine();

                    l.add("number of rows = " + rowNumber);
                    l.add("number of columns = " + columnNumber);
                    l.add("number of mines = " + "2");

                    while ((line = file.readLine()) != null) {
                        l.add(line);
                    }
                    file.close();


                    String result = String.join("\n", l);
                    System.out.println(result);


                    // write the new string with the replaced line OVER the same file
                    FileOutputStream fileOut = new FileOutputStream("src/main/resources/settings.txt");
                    fileOut.write(result.getBytes());
                    fileOut.close();

                    Constants.refresh();

                } catch (Exception er) {
                    System.out.println("Problem reading file.");
                }
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




        JButton btn = new JButton("save");
        add(btn);

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

}
