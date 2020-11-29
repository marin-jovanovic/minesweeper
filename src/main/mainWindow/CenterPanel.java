package main.mainWindow;

//import com.sun.jdi.IncompatibleThreadStateException;
import main.Constants;
import main.Event;
import main.utils.Listener;
import main.utils.minesweeperDrivers.TableGenerator;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.stream.IntStream;

public class CenterPanel extends  JPanel {

    private final JButton[][] buttons;

//    table that shows board mines
    private int[][] table;

    private final int[][] rightClickTable;
    private JButton currentHoveredButton;

//    if field with mine is opened game is over
//    all buttons must be locked
//    this is controller for it
    private boolean areButtonsActive = true;
    private int numOfOpenedCells = 0;


    public CenterPanel() {

        setLayout(new GridLayout(Constants.NUMBER_OF_ROWS, Constants.NUMBER_OF_COLUMNS));

        initialization();

        rightClickTable = new int[Constants.NUMBER_OF_ROWS][Constants.NUMBER_OF_COLUMNS];
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                rightClickTable[i][j] = 0;
            }
        }

        table = TableGenerator.getTable();
        buttons = new JButton[Constants.NUMBER_OF_ROWS][Constants.NUMBER_OF_COLUMNS];

        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {

                buttons[i][j] = new JButton();

//                image for when button is not pressed
                buttonSetIcon(buttons[i][j], "closedCell");

                this.add(buttons[i][j]);

                buttons[i][j].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getButton() != MouseEvent.BUTTON1 && currentHoveredButton != null) {
                            System.out.println("RIGHT CLICK");

                            String name = currentHoveredButton.getIcon().toString()
                                    .replace(Constants.PICTURES_PATH, "")
                                    .replace(Constants.PICTURES_FORMAT, "");

                            String[] temp = hoveredButton.split(";");
                            int i = Integer.parseInt(temp[0]);
                            int j = Integer.parseInt(temp[1]);

                            switch (name) {
                                case "closedCell" -> {
                                    buttonSetIcon(currentHoveredButton, "flag");
                                    rightClickTable[i][j] = 1;
                                }
                                case "flag" -> {
                                    buttonSetIcon(currentHoveredButton, "notSure");
                                    rightClickTable[i][j] = 2;
                                }
                                case "notSure" -> {
                                    buttonSetIcon(currentHoveredButton, "closedCell");
                                    rightClickTable[i][j] = 0;
                                }
                            }
                        }
                    }
               });



                buttons[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if (areButtonsActive) {
                            for (int i = 0; i <Constants.NUMBER_OF_ROWS; i++) {
                                for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                                    if (buttons[i][j].toString().equals(evt.getSource().toString())) {
                                        System.out.println("mouse entered");
                                        hoveredButton = i + ";" + j;
                                        currentHoveredButton = buttons[i][j];
                                    }
                                }
                            }
                        }
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {

                        if (areButtonsActive) {
                            for (int i = 0; i <Constants.NUMBER_OF_ROWS; i++) {
                                for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                                    if (buttons[i][j].toString().equals(evt.getSource().toString())) {
                                        System.out.println("mouse exited");
                                        currentHoveredButton = null;
                                        hoveredButton = i + ";" + j;
                                    }
                                }
                            }
                        }
                    }
                });

                buttons[i][j].addActionListener(new ButtonActionListener());

            }
        }
    }

    private String hoveredButton;

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            checkForWin();
            if (areButtonsActive) {

                for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++){
                    for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                        if(buttons[i][j].toString().equals(e.getSource().toString())) {

                            System.out.println("clicked " + i + " " + j);

                            if (rightClickTable[i][j] == 0) {
                                if (table[i][j] != 0) {
                                    openCell(i, j);
                                    if (table[i][j] == -1) {
                                        System.out.println("game over");
                                        //                                          TODO halt time
                                        //                                          extract to new thread (swing worker)
                                        fireEvent(new main.Event(this, "gameOver"));
                                        areButtonsActive = false;
                                        return;
                                    }
                                }

                                if (table[i][j] == 0) {
                                    openBlanks(i, j);

                                }

                                checkForWin();

                                System.out.println("*** halt ***");
                                System.out.println();
                                return;
                            }


                        }
                    }
                }
            }
        }
    }

    private void buttonSetIcon(JButton jButton, String imageName) {
        try {
            ImageIcon img = new ImageIcon(Constants.PICTURES_PATH + imageName + Constants.PICTURES_FORMAT);
            jButton.setIcon(img);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void buttonSetDisabledIcon(JButton jButton, String imageName) {
        try {
            ImageIcon img = new ImageIcon(Constants.PICTURES_PATH + imageName + Constants.PICTURES_FORMAT);
            jButton.setDisabledIcon(img);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    private int numOfCells;

    public void initialization() {
        numOfCells = Constants.NUMBER_OF_COLUMNS * Constants.NUMBER_OF_ROWS;
    }


    public void checkForWin() {
        if (numOfCells - numOfOpenedCells == Constants.NUMBER_OF_MINES) {
//            TODO disable statistics
            System.out.println("game is won");
            fireEvent(new main.Event(this, "gameWon"));
            areButtonsActive = false;
        }

    }

//  opens targeted cell
    public void openCell(int i, int j){

        if (!buttons[i][j].isEnabled()) {
            return;
        }

        numOfOpenedCells++;
        buttons[i][j].setEnabled(false);
        buttonSetDisabledIcon(buttons[i][j], String.valueOf(table[i][j]));
    }

//    opens all blank that are NEWS, ne, ns, ...,  of targeted cell
    public void openBlanks(int x, int y) {

        if (!buttons[x][y].isEnabled()) {
            return;
        }

        openCell(x,y);

        if (table[x][y] != 0) {
            return;
        }

//        table[x][y] already opened but first if statement in openCell handles that
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    openBlanks(x+i, y+j);
                }
                catch (Exception ignored) {
                }
            }
        }
    }

//    main restart sequence when game is started again
    public void restart() {
        System.out.println("##### new game ######");

        areButtonsActive = true;
        numOfOpenedCells = 0;

        restartButtons();

//         generate new table
        table = TableGenerator.getTable();

    }

//    enables all buttons
//    changes icon to closedCell
    private void restartButtons() {
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                buttons[i][j].setEnabled(true);
                buttonSetIcon(buttons[i][j], "closedCell");
            }
        }
    }

    private final EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {


            if(listeners[i] == Listener.class) {
                ((Listener)listeners[i+1]).eventOccurred(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }

}
