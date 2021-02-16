package main.mainWindow;

import main.constantsModule.Constant;
import main.constantsModule.ConstantsManager;
import main.imagesModule.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class TimerElement extends JPanel {

    private String startTime;

    public TimerElement() {



        JLabel minutesTens = new JLabel(Image.ZERO.getImageIcon());

        add(minutesTens);


        String startTime = null;



    }

    private static Integer[] parseTime(String time) {
        String[] rawData = time.split(":");

        int startMinutes = Integer.parseInt(rawData[0]);
        int startSeconds = Integer.parseInt(rawData[1]);

        System.out.println(startMinutes);
        System.out.println(startSeconds);

//        bigMinutes =

//        return
        return null;
    }

    private Timer timer;

    public static long time = 0;

    private void startTimer() {

        int delay = 1; //milliseconds
        ActionListener taskPerformer = evt -> {

            time += 1000;

//            System.out.println(new java.text.SimpleDateFormat("hh:mm:ss").format(TimerElement.time));

            int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(time)
                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));

            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(time);

            System.out.println(minutes + " " + seconds);

            int mostSigMinDigit = minutes / 10;
            int leastSigMinDigit = minutes % 10;

            int mostSigSecDigit = seconds / 10;
            int leastSigSecDigit = seconds % 10;

            System.out.println(mostSigMinDigit + " " + leastSigMinDigit + " " +
                    mostSigSecDigit + " " + leastSigSecDigit);

            if (minutes == 60) {
                System.out.println("end");
                System.exit(-1);
            }


        };

        timer = new Timer(delay, taskPerformer);
        timer.start();


    }

    private void stopTimer() {
        timer.stop();
    }

    private void restartTimer() {
        timer.restart();
    }


    private static class Launcher extends JFrame {
        Launcher() {
            ConstantsManager.initializeConstants();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            setLayout(new BorderLayout());
            setLayout(new FlowLayout());

            setSize(((Double) Constant.WIDTH.getValue()).intValue(), ((Double) Constant.HEIGHT.getValue()).intValue());
            setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());

            TimerElement timer = new TimerElement();

            add(timer);

            JButton startButton = new JButton("start");

            startButton.addActionListener(e -> {
                timer.startTimer();
            });

            add(startButton);

        }
    }


    public static void main(String[] args) {
          SwingUtilities.invokeLater(Launcher::new);
//
//        String startTime = new java.text.SimpleDateFormat("HH:mm:ss")
//                .format(new java.util.Date(System.currentTimeMillis()));
//
//        System.out.println(startTime);
//
//        String[] rawData = startTime.split(":");
//
//        int startHours = Integer.parseInt(rawData[0]);
//        int startMinutes = Integer.parseInt(rawData[1]);
//        int startSeconds = Integer.parseInt(rawData[2]);
//
//        System.out.println(startHours);
//        System.out.println(startMinutes);
//        System.out.println(startSeconds);




    }

}
