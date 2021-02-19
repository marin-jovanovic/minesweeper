package main.mainWindow;

import main.constantsModule.Constant;
import main.constantsModule.ConstantsManager;

import javax.swing.*;
import java.awt.*;

public class ResultComponent extends JPanel {

    private static final ResultComponent instance = new ResultComponent();
    private final JLabel winLabel;
    private final JLabel loseLabel;
    private ResultComponent() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new FlowLayout());

        winLabel = new JLabel(String.valueOf(Constant.NUMBER_OF_WINS.getValue()));
        loseLabel = new JLabel(String.valueOf(Constant.NUMBER_OF_LOSSES.getValue()));

        add(winLabel);

        JLabel separator = new JLabel(":");
        add(separator);

        add(loseLabel);

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
