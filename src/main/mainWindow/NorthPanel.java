package main.mainWindow;

import main.utils.eventDrivers.Command;
import main.constants.Image;
import main.settingsWindow.SettingsFrame;
import main.utils.eventDrivers.Event;
import main.utils.eventDrivers.Listener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel{

    private JButton oldRestartButton;

//    private RestartButton restartButton;

    public NorthPanel() {

//        TODO statistics, time

        oldRestartButton = new JButton();

//        oldRestartButton.setIcon(new ImageIcon(ButtonStatus.INIT.getPath()));

//        this is init state but it is same as play again
        oldRestartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());

        add(oldRestartButton);

        oldRestartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restart button clicked");
                fireEvent(new Event(this, Command.NEW_GAME));
                oldRestartButton.setIcon(Image.PLAY_AGAIN.getImageIcon());
            }
        });

        JButton settingsButton = new JButton("settings");

        settingsButton.addActionListener(event -> {
            try {
                SwingUtilities.invokeLater(SettingsFrame::new);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(settingsButton);

//        btn1 = new JButton(Image.CLOSED_CELL.getImageIcon());
//        add(btn1);
//
//
//        JButton btn2 = new JButton();
//
//        add(btn2);
//
////        btn1.setIcon(new ImageIcon("main/resources/images/custom/closed_til" +
////                "es/closedCell.png"));
//
//        btn1.addActionListener(new ActionListener() {
//
//            ImageIcon img = Image.CLOSED_CELL.getImageIcon();
//
//
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
////
//                SettingsManager.processNewImage(
//                            new File("C:\\git\\minesweeper\\src\\main\\resources\\im" +
//                                    "ages\\custom\\button\\victory.png"),
//                            Image.CLOSED_CELL
//                );
////                img.getImage().flush();
//                btn1.setIcon(img);
//                MainFrame.restartSequence();
//
////                btn1.setIcon(Image.NOT_SURE.getImageIcon());
////                btn1.setIcon(Image.CLOSED_CELL.getImageIcon());
//
////                Thread seter = new Thread(() -> {
////
////                    btn2.setIcon(new ImageIcon("main/resources/images/custom/closed_til" +
////                            "es/closedCell.png"));
////                });
////
////                Thread t =  new Thread(() -> {
////                    SettingsManager.processNewImage(
////                            new File("C:\\git\\minesweeper\\src\\main\\resources\\im" +
////                                    "ages\\custom\\button\\victory.png"),
////                            Image.CLOSED_CELL
////                    );
////                });
////                ExecutorService threadpool = Executors.newCachedThreadPool();
////                Future<String> futureTask = threadpool.submit(() ->  SettingsManager.processNewImage(
////                        new File("C:\\git\\minesweeper\\src\\main\\resources\\im" +
////                                "ages\\custom\\button\\victory.png"),
////                        Image.CLOSED_CELL, "c"
////                ));
////                threadpool.submit(seter);
////
////                while (!futureTask.isDone()) {
////                    System.out.println("FutureTask is not finished yet...");
////                }
////
////                try {
////                    String result = futureTask.get();
////                    System.out.println("done");
////
////
////
////                    btn1.setIcon((Icon) ImageIO.read(new File("main/resources/images/custom/closed_til" +
////                            "es/closedCell.png")));
////
////                    btn2.setIcon(new ImageIcon( ImageIO.read(new File("main/resources/images/custom/closed_til" +
////                            "es/closedCell.png"))));
////
////
////                } catch (InterruptedException interruptedException) {
////                    interruptedException.printStackTrace();
////                } catch (ExecutionException executionException) {
////                    executionException.printStackTrace();
////                } catch (IOException ioException) {
////                    ioException.printStackTrace();
////                }
////
////                threadpool.shutdown();
//
////                btn1.setIcon(new ImageIcon("main/resources/images/custom/closed_til" +
////                        "es/closedCell.png"));
////                btn2.setIcon(new ImageIcon("main/resources/images/custom/closed_til" +
////                        "es/closedCell.png"));
//
//            }
//        });



    }
//
//    public class MyRunnable implements Runnable {
//
//        private int var;
//
//        public MyRunnable(int var) {
//            this.var = var;
//        }
//
//        public void run() {
//            // code in the other thread, can reference "var" variable
//            btn1.setIcon(Image.EIGHT.getImageIcon());
//            btn1.setIcon(Image.CLOSED_CELL.getImageIcon());
//            btn2.setIcon(Image.CLOSED_CELL.getImageIcon());
//        }
//    }

//    JButton btn2;
//    private JButton btn1;

    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i++) {
            System.out.println(listeners[i]);
        }

        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] instanceof Listener) {
                ((Listener)listeners[i]).eventOccurred(event);
                return;
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }

    public void setRestartButton(Command command) throws Exception {
        if (command.equals(Command.GAME_OVER)) {
            oldRestartButton.setIcon(Image.DEFEAT.getImageIcon());

//            oldRestartButton.setIcon(new ImageIcon(ButtonStatus.DEFEAT.getPath()));
        } else if (command.equals(Command.GAME_WON)) {
            oldRestartButton.setIcon(Image.VICTORY.getImageIcon());

//            oldRestartButton.setIcon(new ImageIcon(ButtonStatus.VICTORY.getPath()));
        } else {
            throw new Exception("unsupported command");
        }
//
//        if (command.equals("gameOver")) {
////            oldRestartButton.setIcon( new ImageIcon( defeat.getPath()));
//
//            buttonSetIcon(ButtonStatus.DEFEAT);
//        } else if (command.equals("gameWon")) {
//            buttonSetIcon(ButtonStatus.VICTORY);
//        }


    }

//    private void buttonSetIcon(Image buttonStatus) {
//        oldRestartButton.setIcon(buttonStatus.getImageIcon());
////            oldRestartButton.setIcon( new ImageIcon( defeat.getPath()));
//
//    }


}
