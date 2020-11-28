package main.mainWindow;

import main.Constants;
import main.Event;
import main.Listener;
import main.utils.minesweeperDrivers.TableGenerator;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CenterPanel extends  JPanel {

    private final JButton[][] buttons;
    private int[][] table;
    private JButton currentHoveredButton;

//    if field with mine is opened game is over
//    all buttons must be locked
//    this is controller for it
    private boolean areButtonsActive = true;
    private int numOfOpenedCells = 0;


    public CenterPanel() {
        setSize(200, 100);
        setLayout(new GridLayout(Constants.NUMBER_OF_ROWS, Constants.NUMBER_OF_COLUMNS));

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

                            switch (name) {
                                case "closedCell" -> buttonSetIcon(currentHoveredButton, "flag");
                                case "flag" -> buttonSetIcon(currentHoveredButton, "notSure");
                                case "notSure" -> buttonSetIcon(currentHoveredButton, "closedCell");
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
//                                        hoveredButton = i + ";" + j;
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
//                                        hoveredButton = "";
                                        currentHoveredButton = null;
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

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            checkForWin();
            if (areButtonsActive) {
                for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++){
                    for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                        if(buttons[i][j].toString().equals(e.getSource().toString())) {
                            System.out.println("clicked " + i + " " + j);

                            if (table[i][j] != 0) {
                                openCell(i, j);
                                if (table[i][j] == -1) {
                                    System.out.println("game over");
        //                                          TODO halt time
        //                                          extract to new thread (swing worker)
        //                                                defeat
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

    private void buttonSetIcon(JButton jButton, String imageName) {
        try {
            ImageIcon img = new ImageIcon(Constants.PICTURES_PATH + imageName + Constants.PICTURES_FORMAT);
            jButton.setIcon(img);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void checkForWin() {
        int numOfCells = Constants.NUMBER_OF_COLUMNS * Constants.NUMBER_OF_ROWS;
        boolean isGameWon = (numOfCells - numOfOpenedCells == Constants.NUMBER_OF_MINES);
        System.out.println("num of cells  " + numOfCells);
        System.out.println("opened        " + numOfOpenedCells);
        System.out.println("mines         " + Constants.NUMBER_OF_MINES);
        System.out.println("control table");

        if (isGameWon) {
//                    TODO disable statistics
            System.out.println("game is won");
//            victory
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
        System.out.println("open tile  " + i+ " " + j);

        buttons[i][j].setEnabled(false);

        int num = table[i][j];
        if (num == 9){
            num = 0;
        }

        try {

            ImageIcon img = new ImageIcon(Constants.PICTURES_PATH + num + Constants.PICTURES_FORMAT);
            buttons[i][j].setDisabledIcon(img);
            buttons[i][j].setIcon(img);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

//    opens all blank that are NEWS of targeted cell
    public void openBlanks(int x, int y) {

        if (table[x][y] != 0){
            openCell(x,y);
            return;
        }

        table[x][y] = 9;
        openCell(x,y);

        if (!(x+1 >= Constants.NUMBER_OF_ROWS))
            openBlanks(x+1, y);
        if (!(x-1 < 0))
            openBlanks(x-1, y);
        if (!(y+1 >= Constants.NUMBER_OF_COLUMNS))
            openBlanks(x, y+1);
        if (!(y-1 < 0))
            openBlanks(x, y-1);
        if (!(y+1 >= Constants.NUMBER_OF_COLUMNS) && !(x+1 >= Constants.NUMBER_OF_ROWS))
            openBlanks(x+1, y+1);
        if (!(y-1 < 0) && !(x-1 < 0))
            openBlanks(x-1, y-1);
        if (!(y-1 < 0) && !(x+1 >= Constants.NUMBER_OF_ROWS))
            openBlanks(x+1, y-1);
        if (!(y+1 >= Constants.NUMBER_OF_COLUMNS) && !(x-1 < 0))
            openBlanks(x-1, y+1);

    }

//    main restart sequence when game is started again
    public void restart() {
        System.out.println("##### new game ######");
//        SwingUtilities.updateComponentTreeUI(this);
// FIXME: 21.11.2020.
        areButtonsActive = true;
        numOfOpenedCells = 0;

        restartButtons();

//        tableDriver();
        table = TableGenerator.getTable();

    }

//    enables all buttons
    private void restartButtons() {
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                buttons[i][j].setEnabled(true);
                buttonSetIcon(buttons[i][j], "closedCell");
                buttons[i][j].setDisabledIcon(null);
            }
        }
    }

    private final EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {


            if(listeners[i] == Listener.class) {
                ((Listener)listeners[i+1]).EventOccured(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }

}
