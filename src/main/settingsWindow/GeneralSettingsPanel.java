package main.settingsWindow;

import main.constants.ConstantsManager;
import main.settingsWindow.elements.textField.Linker;
import main.settingsWindow.elements.textField.TextFieldElement;

import javax.swing.*;

public class GeneralSettingsPanel extends JPanel {

    public GeneralSettingsPanel() {
        add(new TextFieldElement(Linker.ROW_NUMBER.getFrontEnd(), String.valueOf(ConstantsManager.NUMBER_OF_ROWS)));
        add(new TextFieldElement(Linker.COLUMN_NUMBER.getFrontEnd(),  String.valueOf(ConstantsManager.NUMBER_OF_COLUMNS)));
        add(new TextFieldElement(Linker.MINE_NUMBER.getFrontEnd(), String.valueOf(ConstantsManager.NUMBER_OF_MINES)));
    }
}
