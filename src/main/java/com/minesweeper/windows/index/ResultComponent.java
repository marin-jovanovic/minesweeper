package com.minesweeper.windows.index;

import com.minesweeper.resourceManagers.constants.Constant;
import com.minesweeper.resourceManagers.constants.ConstantsManager;

import javax.swing.*;
import java.awt.*;

public class ResultComponent extends JButton {

    private static final ResultComponent instance = new ResultComponent();
    private final JLabel winLabel;
    private final JLabel loseLabel;

    private ResultComponent() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new FlowLayout());

        winLabel = new JLabel(String.valueOf(Constant.NUMBER_OF_WINS.getValue()));
        loseLabel = new JLabel(String.valueOf(Constant.NUMBER_OF_LOSSES.getValue()));

        JLabel separator = new JLabel(":");

        add(winLabel);
        add(separator);
        add(loseLabel);


        addActionListener((actionEvent) -> {
            GraphPanel.run();
        });
    }

    public static ResultComponent getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResultComponent.TestFrame::new);
    }

    public void refreshLoseLabel() {
        loseLabel.setText(String.valueOf(Constant.NUMBER_OF_LOSSES.getValue()));
    }

    public void refreshWinLabel() {
        winLabel.setText(String.valueOf(Constant.NUMBER_OF_WINS.getValue()));
    }

    private static class TestFrame extends JFrame {
        TestFrame() {
            ConstantsManager.initializeConstants();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());

            setSize(((Double) Constant.WIDTH.getValue()).intValue(), ((Double) Constant.HEIGHT.getValue()).intValue());
            setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());

            ///////////////////////////////////////////////

            add(new ResultComponent());

        }
    }

}
