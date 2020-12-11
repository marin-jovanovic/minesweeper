package main.settingsWindow.elements.imagePicker;

import main.constants.Image;
import main.settingsWindow.settingsManager.SettingsBuffer;
import main.settingsWindow.elements.imagePicker.driver.ImageFileView;
import main.settingsWindow.elements.imagePicker.driver.ImageFilter;
import main.settingsWindow.elements.imagePicker.driver.ImagePreview;
import main.settingsWindow.settingsManager.SettingsManager;

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
    private String jText;
    private main.constants.Image image;


    public ImagePickerElement(main.constants.Image image) {
        this.image = image;

        this.jText = image.getJText();

        setBorder(BorderFactory.createLineBorder(Color.BLUE));

        log = new JTextArea(5,20);
        //Create the log first, because the action listener
        //needs to refer to it.
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);


        jButton = new JButton(jText);
        jButton.addActionListener(this::actionPerformed);
        add(jButton);


        add(new JScrollPane(log));

        imageLabel = new JLabel(image.getImageIcon());
        add(imageLabel);
    }


    private void setImageLabel(Image image) {
//String filename

        imageLabel.setIcon(image.getImageIcon());

//        imageLabel.setIcon(new ImageIcon(
//                                        new ImageIcon(fileName)
//                                                .getImage()
//                                                .getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH)
//                                        )
//                            );
        imageLabel.setText("current image");
        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
    }


    //    used for images
    public void actionPerformed(ActionEvent e) {
        //Set up the file chooser.
        setupFileChooser();

        //Show it.
        int returnVal = fc.showDialog(this,"Attach");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            log.append("Attaching file: " + file.getName() + "." + "\n");
//            saves to buffer


//            sets right component image to new selected image
            File newImage = new File(file.getAbsolutePath());

            SettingsManager.processNewImage(newImage, this.image);



            setImageLabel(this.image);
//
        } else {
            log.append("Attachment cancelled by user." + "\n");
        }
        log.setCaretPosition(log.getDocument().getLength());

        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    }

    private void setupFileChooser() {
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
    }


}
