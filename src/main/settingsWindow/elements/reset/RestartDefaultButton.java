package main.settingsWindow.elements.reset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartDefaultButton extends JPanel {
    private JButton jButton;

    public RestartDefaultButton() {
        jButton = new JButton("restart to default settings");
        jButton.addActionListener(new RestartButtonActionListener());
    }

    private class RestartButtonActionListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
//            read settings from @defaultsettings.txt
//            write settings to @settings.txt
        }
    }
}
