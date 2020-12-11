package main.settingsWindow;

import main.constants.Constant;
import main.constants.ConstantsManager;
import main.settingsWindow.elements.reset.RestartDefaultButton;
import main.settingsWindow.elements.textField.TextFieldElement;

import javax.swing.*;
import java.awt.*;

public class GeneralSettingsPanel extends JPanel {

    public GeneralSettingsPanel() {

        setLayout(new GridLayout(0,1));

        add(new TextFieldElement(Constant.NUMBER_OF_ROWS.getJText(),
//                String.valueOf(ConstantsManager.NUMBER_OF_ROWS)));
                String.valueOf(Constant.NUMBER_OF_ROWS.getValue())));
        add(new TextFieldElement(Constant.NUMBER_OF_COLUMNS.getJText(),
//                String.valueOf(ConstantsManager.NUMBER_OF_COLUMNS)));
                String.valueOf(Constant.NUMBER_OF_COLUMNS.getValue())));
        add(new TextFieldElement(Constant.NUMBER_OF_MINES.getJText(),
                String.valueOf(Constant.NUMBER_OF_MINES.getValue())));

        add(new RestartDefaultButton());

  }
}
