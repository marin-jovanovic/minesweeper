package com.minesweeper.windows.index;

import com.minesweeper.eventDrivers.Command;
import com.minesweeper.resourceManagers.constants.Constant;
import com.minesweeper.resourceManagers.constants.ConstantsManager;
import com.minesweeper.resourceManagers.images.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;

//            System.out.println(new java.text.SimpleDateFormat("hh:mm:ss").format(TimerElement.time));

public class TimerElement extends JPanel implements PropertyChangeListener {

    private final long startTime = 1;
    private final Timer timer;
    private final JLabel mostSigMinDigitLabel;
    private final JLabel leastSigMinDigitLabel;
    private final JLabel mostSigSecDigitLabel;
    private final JLabel leastSigSecDigitLabel;
    private long time;

    private int mostSigMinDigit;
    private int leastSigMinDigit;
    private int mostSigSecDigit;
    private int leastSigSecDigit;

    private boolean isTicking = false;

    public TimerElement() {
        setBorder(BorderFactory.createLineBorder(Color.black));
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

            mostSigMinDigit = minutes / 10;
            leastSigMinDigit = minutes % 10;

            mostSigSecDigit = seconds / 10;
            leastSigSecDigit = seconds % 10;

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

    public boolean isTicking() {
        return isTicking;
    }

    public void startOrContinueTimer() {

//        if (!isStarted) {
        timer.start();
        isTicking = true;


//            isStarted = true;
//        } else {
//            System.out.println("timer already started");
//        }
    }

    public void stopTimer() {

        timer.stop();
//        isTicking = false;
    }


    public void restartTimer() {

        timer.stop();

        time = startTime;

        isTicking = false;

        mostSigMinDigitLabel.setIcon(Image.T_ZERO.getImageIcon());
        leastSigMinDigitLabel.setIcon(Image.T_ZERO.getImageIcon());
        mostSigSecDigitLabel.setIcon(Image.T_ZERO.getImageIcon());
        leastSigSecDigitLabel.setIcon(Image.T_ZERO.getImageIcon());

    }

    public String getTime() {
        return String.valueOf(mostSigMinDigit + leastSigMinDigit + mostSigSecDigit + leastSigSecDigit);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == Command.RESTART_TIMER) {
            System.out.println("property changed: restarting timer");
            restartTimer();
        } else if (evt.getNewValue() == Command.STOP_TIMER) {
            System.out.println("property changed: stopping timer");
            stopTimer();
        } else {
            System.out.println("unsupported command in timer element");
            System.out.println(evt);
            System.out.println();
        }
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
