package main.settingsWindow;

import main.constants.GeneralConstants;
import main.settingsWindow.elements.imagePicker.ImagePickerElement;
import main.settingsWindow.elements.textField.TextFieldElement;
import main.constants.LayoutConstants;

import javax.swing.*;

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

//    TODO reset to default

    public SettingsFrame() {
        super("Settings");
        setSize(LayoutConstants.WIDTH, LayoutConstants.HEIGHT);
        setLocation(LayoutConstants.LOCATION_X, LayoutConstants.LOCATION_Y);
        setVisible(true);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));


        TextFieldElement rowFiled = new TextFieldElement(
                "row number:", String.valueOf(GeneralConstants.NUMBER_OF_ROWS)
        );
        add(rowFiled);

        TextFieldElement columnField = new TextFieldElement(
                "column number:" , String.valueOf(GeneralConstants.NUMBER_OF_COLUMNS)
        );
        add(columnField);

        TextFieldElement mineField = new TextFieldElement(
                "mine number:", String.valueOf(GeneralConstants.NUMBER_OF_MINES)
        );
        add(mineField);


        ImagePickerElement imagePickerElement = new ImagePickerElement();
        add(imagePickerElement);


//        restart @MainFrame
//        this.addListener(event -> MainFrame.restartSequence());


//        saves on close new settings
        addWindowListener(new SettingsWindowListener());


    }

//    private EventListenerList listenerList = new EventListenerList();
//
//    public void fireEvent(Event event) {
//        Object[] listeners = listenerList.getListenerList();
//
//        for (int i = 0; i < listeners.length; i += 2) {
//
//
//            if(listeners[i] == Listener.class) {
//                ((Listener)listeners[i+1]).eventOccurred(event);
//            }
//        }
//    }
//
//    public void addListener(Listener listener) {
//        listenerList.add(Listener.class, listener);
//    }
}
