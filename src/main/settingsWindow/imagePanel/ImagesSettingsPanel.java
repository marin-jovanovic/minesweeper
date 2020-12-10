package main.settingsWindow.imagePanel;

import main.settingsWindow.elements.imagePicker.ImagePickerElement;
import main.settingsWindow.elements.reset.RestartDefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ImagesSettingsPanel extends JPanel{

    public ImagesSettingsPanel() {
        setLayout(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();


        tabbedPane.addTab("button", new ButtonPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        tabbedPane.addTab("closed tiles",  new ClosedTilePanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        tabbedPane.addTab("opened tiles", new OpenedTilePanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

//        tabbedPane.addTab("settings checker flags", new T);
//        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        tabbedPane.addTab("time", new TimePanel());
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        //Add the tabbed pane to this panel.
        add(tabbedPane);
//        add(new RestartDefaultButton());


        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    private static class ButtonPanel extends JPanel{
        private ButtonPanel() {
            add(new ImagePickerElement("change victory image"));
            add(new ImagePickerElement("change play again image"));
            add(new ImagePickerElement("change defeat image"));
            add(new RestartDefaultButton());

        }
    }

    private static class ClosedTilePanel extends JPanel {
        private ClosedTilePanel() {
            add(new ImagePickerElement("change closed cell image"));
            add(new ImagePickerElement("change flag image"));
            add(new ImagePickerElement("change notSure image"));
            add(new RestartDefaultButton());

        }
    }

    private class OpenedTilePanel extends JPanel {
        private OpenedTilePanel() {
            add(new ImagePickerElement("change mine image"));
            add(new ImagePickerElement("change 0 image"));
            add(new ImagePickerElement("change 1 image"));
            add(new ImagePickerElement("change 2 image"));
            add(new ImagePickerElement("change 3 image"));
            add(new ImagePickerElement("change 4 image"));
            add(new ImagePickerElement("change 5 image"));
            add(new ImagePickerElement("change 6 image"));
            add(new ImagePickerElement("change 7 image"));
            add(new ImagePickerElement("change 8 image"));
            add(new RestartDefaultButton());

        }
    }

    private class TimePanel extends JPanel {
        private TimePanel() {
            add(new ImagePickerElement("change 0 image"));
            add(new ImagePickerElement("change 1 image"));
            add(new ImagePickerElement("change 2 image"));
            add(new ImagePickerElement("change 3 image"));
            add(new ImagePickerElement("change 4 image"));
            add(new ImagePickerElement("change 5 image"));
            add(new ImagePickerElement("change 6 image"));
            add(new ImagePickerElement("change 7 image"));
            add(new ImagePickerElement("change 8 image"));
            add(new ImagePickerElement("change 9 image"));
            add(new RestartDefaultButton());

        }
    }
}
