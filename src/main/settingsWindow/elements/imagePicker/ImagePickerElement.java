package main.settingsWindow.elements.imagePicker;

import main.constants.Constant;
import main.settingsWindow.SettingsBuffer;
import main.settingsWindow.elements.imagePicker.driver.ImageFileView;
import main.settingsWindow.elements.imagePicker.driver.ImageFilter;
import main.settingsWindow.elements.imagePicker.driver.ImagePreview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

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

public class ImagePickerElement extends JPanel {
    private JTextArea log;
    private JFileChooser fc;
    private JLabel imageLabel;
    private JButton jButton;

    private static final int PADDING = 3;   // for example

    private String jText;

    public ImagePickerElement(String pathID, String jText, String logID) {




        this(jText);
    }

//    add in consturctor IMageicon for this
    public ImagePickerElement(String jText) {
        this.jText = jText;

        setBorder(BorderFactory.createLineBorder(Color.blue));

        log = new JTextArea(5,20);
        //Create the log first, because the action listener
        //needs to refer to it.
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);


        jButton = new JButton(jText);
        jButton.addActionListener(this::actionPerformed);
        add(jButton);


        add(new JScrollPane(log));


//        load default image for this or current if exists
        add(new JLabel(Constant.FIVE.getImageIcon()));

//        ImageIcon imgThisImg = new ImageIcon(PicURL));
//
//        jLabel2.setIcon(imgThisImg);
    }

    private void setImageLabel(String fileName) {


//        ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
//        Image image = imageIcon.getImage(); // transform it
//        Image newimg =
//                new ImageIcon("./img/imageName.png").getImage().getScaledInstance(120, 120,
//                java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        ImageIcon imageIcon = new ImageIcon(newimg);  // transform it back

        imageLabel.setIcon(new ImageIcon(
                                        new ImageIcon(fileName)
                                                .getImage()
                                                .getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH)
                                        )
                            );
        imageLabel.setText("current image");
        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
    }

//    public static class LabelDemo extends JPanel {
//        public LabelDemo() {
//            JLabel label1, label2, label3;
//
//            label1 = new JLabel("Image and Text",
//                    OpenedTileStatus.EIGHT.getImageIcon(),
//                    JLabel.CENTER);
//
//            //Set the position of its text, relative to its icon:
//            label1.setVerticalTextPosition(JLabel.BOTTOM);
//            label1.setHorizontalTextPosition(JLabel.CENTER);
//            add(label1);
//
//
//            label2 = new JLabel("Text-Only Label");
//            add(label2);
//
//
//            label3 = new JLabel(OpenedTileStatus.EIGHT.getImageIcon());
//            add(label3);
//
//        }
//    }

//    public static void main(String[] args) {
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                JFrame frame = new JFrame("LabelDemo");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//                //Add content to the window.
//                frame.add(new LabelDemo());
//
//                //Display the window.
//                frame.pack();
//                frame.setVisible(true);
//            }
//        });
//    }


//    public ImagePickerElement() {
//
//        log = new JTextArea(5,20);
//
////Create the log first, because the action listener
//        //needs to refer to it.
//        log.setMargin(new Insets(5,5,5,5));
//        log.setEditable(false);
//        JScrollPane logScrollPane = new JScrollPane(log);
//
//        JButton sendButton = new JButton("Attach...");
////        sendButton.addActionListener(this);
//
//        sendButton.addActionListener(this::actionPerformed);
//
//        add(sendButton);
//        add(logScrollPane);
//
//    }

//    private String fileName;

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
        int returnVal = fc.showDialog(this,"Attach");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            log.append("Attaching file: " + file.getName() + "." + "\n");
//            saves to buffer

            SettingsBuffer.writeToBuffer(jText, file.getAbsolutePath());


//            sets right component image to new selected image
            setImageLabel(file.getAbsolutePath());
//
        } else {
            log.append("Attachment cancelled by user." + "\n");
        }
        log.setCaretPosition(log.getDocument().getLength());

        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    }


}
