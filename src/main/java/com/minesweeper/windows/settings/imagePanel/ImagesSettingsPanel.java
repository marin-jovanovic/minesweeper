package com.minesweeper.windows.settings.imagePanel;

import com.minesweeper.resourceManagers.images.Image;
import com.minesweeper.windows.settings.elements.imagePicker.ImagePickerElement;
import com.minesweeper.windows.settings.elements.reset.RestartDefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ImagesSettingsPanel extends JPanel {

    public ImagesSettingsPanel() {
        setLayout(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();


        tabbedPane.addTab("button", new ButtonPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        tabbedPane.addTab("closed tiles", new ClosedTilePanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        tabbedPane.addTab("opened tiles", new OpenedTilePanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.addTab("time", new TimePanel());
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        //Add the tabbed pane to this panel.
        add(tabbedPane);
//        add(new RestartDefaultButton());


        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


    }

    private static class ButtonPanel extends JPanel {
        private ButtonPanel() {

            add(new ImagePickerElement(Image.VICTORY));
            add(new ImagePickerElement(Image.PLAY_AGAIN));
            add(new ImagePickerElement(Image.DEFEAT));

            add(new RestartDefaultButton());

        }
    }

    private static class ClosedTilePanel extends JPanel {
        private ClosedTilePanel() {


            add(new ImagePickerElement(Image.CLOSED_CELL));
            add(new ImagePickerElement(Image.FLAG));
            add(new ImagePickerElement(Image.NOT_SURE));

            add(new RestartDefaultButton());

        }
    }

    private class OpenedTilePanel extends JPanel {
        private OpenedTilePanel() {

            add(new ImagePickerElement(Image.MINE));
            add(new ImagePickerElement(Image.ZERO));
            add(new ImagePickerElement(Image.ONE));

            add(new ImagePickerElement(Image.TWO));
            add(new ImagePickerElement(Image.THREE));
            add(new ImagePickerElement(Image.FOUR));

            add(new ImagePickerElement(Image.FIVE));
            add(new ImagePickerElement(Image.SIX));
            add(new ImagePickerElement(Image.SEVEN));

            add(new ImagePickerElement(Image.EIGHT));

            add(new RestartDefaultButton());

        }
    }

    private class TimePanel extends JPanel {
        private TimePanel() {

            add(new JLabel("TODO"));
//            add(new ImagePickerElement("change 0 image"));
//            add(new ImagePickerElement("change 1 image"));
//            add(new ImagePickerElement("change 2 image"));
//            add(new ImagePickerElement("change 3 image"));
//            add(new ImagePickerElement("change 4 image"));
//            add(new ImagePickerElement("change 5 image"));
//            add(new ImagePickerElement("change 6 image"));
//            add(new ImagePickerElement("change 7 image"));
//            add(new ImagePickerElement("change 8 image"));
//            add(new ImagePickerElement("change 9 image"));
            add(new RestartDefaultButton());

        }
    }
}
