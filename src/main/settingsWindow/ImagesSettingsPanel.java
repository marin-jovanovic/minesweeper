package main.settingsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ImagesSettingsPanel extends JPanel{

    public ImagesSettingsPanel() {
        setLayout(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();


        tabbedPane.addTab("General", new JButton("!toodo1"));
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        tabbedPane.addTab("Images",  new JButton("t2"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        tabbedPane.addTab("Sound", new JButton("t3"));
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
}
