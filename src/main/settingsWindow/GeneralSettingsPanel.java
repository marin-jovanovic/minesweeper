package main.settingsWindow;

import main.constants.Constant;
import main.constants.ConstantsManager;
import main.settingsWindow.elements.reset.RestartCurrentButton;
import main.settingsWindow.elements.reset.RestartDefaultButton;
import main.settingsWindow.elements.textField.TextFieldElement;

import javax.swing.*;
import java.awt.*;

public class GeneralSettingsPanel extends JPanel {

    public GeneralSettingsPanel() {

        setLayout(new GridLayout(0,1));

        add(new TextFieldElement(Constant.NUMBER_OF_ROWS));
        add(new TextFieldElement(Constant.NUMBER_OF_COLUMNS));
        add(new TextFieldElement(Constant.NUMBER_OF_MINES));



//TODO
//        add(new RestartCurrentButton(Constant.NUMBER_OF_ROWS, Constant.NUMBER_OF_COLUMNS, Constant.NUMBER_OF_MINES));

        add(new RestartDefaultButton());

  }
}
