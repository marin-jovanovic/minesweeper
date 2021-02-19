package main.windows.settings.elements.reset;

import main.resourceManagers.constants.Constant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestartCurrentButton extends JButton {

    private ArrayList<Constant> constants;

    public RestartCurrentButton(Constant... constants) {
        this.constants = new ArrayList<>();
        for (Constant c : constants) {
            this.constants.add(c);
        }


        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restarting this settings");
                for (Constant c : constants) {
                    System.out.println(c);
                }
            }
        });
    }

    public RestartCurrentButton() {
        setText("restart this page");
    }
}
