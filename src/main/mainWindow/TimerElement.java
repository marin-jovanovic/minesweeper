package main.mainWindow;

import main.constantsModule.Constant;
import main.constantsModule.ConstantsManager;
import main.imagesModule.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

//            System.out.println(new java.text.SimpleDateFormat("hh:mm:ss").format(TimerElement.time));

public class TimerElement extends JPanel {

    private final long startTime = 1;
//    private final int delay = 1000;
    private long time;
    private final Timer timer;
    private final JLabel mostSigMinDigitLabel;
    private final JLabel leastSigMinDigitLabel;
    private final JLabel mostSigSecDigitLabel;
    private final JLabel leastSigSecDigitLabel;


    public TimerElement() {

        setLayout(new FlowLayout());

        mostSigMinDigitLabel = new JLabel(Image.T_ZERO.getImageIcon());
        add(mostSigMinDigitLabel);

        leastSigMinDigitLabel = new JLabel(Image.T_ZERO.getImageIcon());
        add(leastSigMinDigitLabel);

        JLabel separator = new JLabel(":");
        add(separator);

        mostSigSecDigitLabel = new JLabel(Image.T_ZERO.getImageIcon());
        add(mostSigSecDigitLabel);

        leastSigSecDigitLabel = new JLabel(Image.T_ZERO.getImageIcon());
        add(leastSigSecDigitLabel);


        ActionListener taskPerformer = evt -> {

            time += 1000;


            int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(time)
                    - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));

            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(time);

            int mostSigMinDigit = minutes / 10;
            int leastSigMinDigit = minutes % 10;

            int mostSigSecDigit = seconds / 10;
            int leastSigSecDigit = seconds % 10;

            updateImage(mostSigMinDigitLabel, mostSigMinDigit);
            updateImage(leastSigMinDigitLabel, leastSigMinDigit);
            updateImage(mostSigSecDigitLabel, mostSigSecDigit);
            updateImage(leastSigSecDigitLabel, leastSigSecDigit);

            if (minutes == 60) {
                System.out.println("end");
                System.out.println("handle this in TimerElement action listener");
                System.exit(-1);
            }


        };
        int delay = 1000;
        timer = new Timer(delay, taskPerformer);

        time = startTime;
    }

    private static void updateImage(JLabel tile, int digit) {
        switch (digit) {
            case 0 -> tile.setIcon(Image.T_ZERO.getImageIcon());
            case 1 -> tile.setIcon(Image.T_ONE.getImageIcon());
            case 2 -> tile.setIcon(Image.T_TWO.getImageIcon());
            case 3 -> tile.setIcon(Image.T_THREE.getImageIcon());
            case 4 -> tile.setIcon(Image.T_FOUR.getImageIcon());
            case 5 -> tile.setIcon(Image.T_FIVE.getImageIcon());
            case 6 -> tile.setIcon(Image.T_SIX.getImageIcon());
            case 7 -> tile.setIcon(Image.T_SEVEN.getImageIcon());
            case 8 -> tile.setIcon(Image.T_EIGHT.getImageIcon());
            case 9 -> tile.setIcon(Image.T_NINE.getImageIcon());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestFrame::new);
    }

    private boolean isStarted = false;

    private void startOrContinueTimer() {

        if (!isStarted) {
            timer.start();

            isStarted = true;
        } else {
            System.out.println("timer already started");
        }
    }

    private void stopTimer() {

        timer.stop();

    }

    private void restartTimer() {

        timer.stop();

        time = startTime;

        mostSigMinDigitLabel.setIcon(Image.T_ZERO.getImageIcon());
        leastSigMinDigitLabel.setIcon(Image.T_ZERO.getImageIcon());
        mostSigSecDigitLabel.setIcon(Image.T_ZERO.getImageIcon());
        leastSigSecDigitLabel.setIcon(Image.T_ZERO.getImageIcon());

    }

    private static class TestFrame extends JFrame {
        TestFrame() {
            ConstantsManager.initializeConstants();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());

            setSize(((Double) Constant.WIDTH.getValue()).intValue(), ((Double) Constant.HEIGHT.getValue()).intValue());
            setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());

            TimerElement timer = new TimerElement();

            add(timer);

            JButton startButton = new JButton("start/continue");
            startButton.addActionListener(e -> timer.startOrContinueTimer());
            add(startButton);


            JButton stop = new JButton("stop");
            stop.addActionListener(e -> timer.stopTimer());
            add(stop);

            JButton restart = new JButton("restart");
            restart.addActionListener(e -> timer.restartTimer());
            add(restart);

        }
    }

}
