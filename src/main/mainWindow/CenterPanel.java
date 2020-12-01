package main.mainWindow;

import main.ClosedTileStatus;
import main.Constants;
import main.Event;
import main.OpenedTileStatus;
import main.utils.Listener;
import main.utils.minesweeperDrivers.TableGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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

        numOfCells = Constants.NUMBER_OF_COLUMNS * Constants.NUMBER_OF_ROWS;

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


                try {
                    buttons[i][j].setIcon(new ImageIcon(
                            Constants.getPathImageClosedTiles(ClosedTileStatus.CLOSED_CELL)));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                }


                this.add(buttons[i][j]);

                buttons[i][j].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getButton() != MouseEvent.BUTTON1 && currentHoveredButton != null) {
                            System.out.println("RIGHT CLICK");

//                            name of picture on button
                            String name = currentHoveredButton.getIcon().toString();

                            ClosedTileStatus closedTileStatus;

                            if (name.contains("closedCell")) {
                                closedTileStatus = ClosedTileStatus.CLOSED_CELL;
                            }
                            else if (name.contains("flag")) {
                                closedTileStatus = ClosedTileStatus.FLAG;
                            }
                            else {
                                closedTileStatus = ClosedTileStatus.NOT_SURE;
                            }

                            try {
                                currentHoveredButton.setIcon(new ImageIcon(ImageIO.read(new File(
                                        Constants.getPathImageClosedTiles(closedTileStatus)
                                ))));
                            }
                            catch (Exception exception) {
                                exception.printStackTrace();
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

    private int numOfCells;

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

        OpenedTileStatus openedTileStatus;

        switch (table[i][j]) {
            case 0 -> openedTileStatus = OpenedTileStatus.ZERO;
            case 1 -> openedTileStatus = OpenedTileStatus.ONE;
            case 2 -> openedTileStatus = OpenedTileStatus.TWO;
            case 3 -> openedTileStatus = OpenedTileStatus.THREE;
            case 4 -> openedTileStatus = OpenedTileStatus.FOUR;
            case 5 -> openedTileStatus = OpenedTileStatus.FIVE;
            case 6 -> openedTileStatus = OpenedTileStatus.SIX;
            case 7 -> openedTileStatus = OpenedTileStatus.SEVEN;
            case 8 -> openedTileStatus = OpenedTileStatus.EIGHT;
            default -> throw new IndexOutOfBoundsException("error in parsing");
        }

        try {
            buttons[i][j].setDisabledIcon(
                    new ImageIcon(
                            Constants.getPathImageOpenedTiles(
                                        openedTileStatus
                                    )
                            )
                    )
            );
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

//        try {
//            buttons[i][j].setDisabledIcon(
//                    new ImageIcon(
//                            Constants.getPathImageOpenedTiles(
//                                    String.valueOf(table[i][j])
//                            )
//                    )
//            );
//        }
//        catch (Exception exception) {
//            exception.printStackTrace();
//        }
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
    public void restart(String command) {
        System.out.println("centerPanel: restart");
        System.out.println("command " + command);

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

                try {
                    buttons[i][j].setIcon(new ImageIcon(Constants.getPathImageClosedTiles(ClosedTileStatus.CLOSED_CELL)));


//                    buttons[i][j].setIcon(new ImageIcon(Constants.getPathImageClosedTiles("closedCell")));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

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
