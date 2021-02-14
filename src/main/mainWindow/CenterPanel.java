package main.mainWindow;

import main.constantsModule.Constant;
import main.imagesModule.Image;
import main.soundsModule.SoundsManager;
import main.utils.eventDrivers.Command;
import main.utils.eventDrivers.Event;
import main.utils.eventDrivers.Listener;
import main.utils.minesweeperDrivers.TableGenerator;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CenterPanel extends JPanel {

//    TODO
//      add ability to block all buttons under flag

    private final JButton[][] buttons;
    private final int numOfCells;


    //    used for right click actions
    private final EventListenerList listenerList = new EventListenerList();
    //    table that shows board mines
    private int[][] table;
    //    if field with mine is opened game is over
//    all buttons must be locked
//    this is controller for it
    private boolean areButtonsActive = true;

    private void setButtons(boolean value) {
        areButtonsActive = value;

        MouseActionListener.setAreButtonsActive(value);
    }

    //    used to check if you can declare win
    private int numOfOpenedCells = 0;

    //    for navigation in table
    private int currentHoveredButtonX;
    private int currentHoveredButtonY;

    public CenterPanel() {

        setLayout(new GridLayout((int) Constant.NUMBER_OF_ROWS.getValue(),
                (int) Constant.NUMBER_OF_COLUMNS.getValue()));

        numOfCells = (Integer) Constant.NUMBER_OF_COLUMNS.getValue() * (Integer) Constant.NUMBER_OF_ROWS.getValue();

        table = TableGenerator.getTable();

        buttons = new JButton[(int) Constant.NUMBER_OF_ROWS.getValue()][(int) Constant.NUMBER_OF_COLUMNS.getValue()];

        for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {

                buttons[i][j] = new JButton();

                buttons[i][j].setIcon(Image.CLOSED_CELL.getImageIcon());

                buttons[i][j].addMouseListener(new MouseActionListener(this, areButtonsActive, buttons, i, j));

                buttons[i][j].addActionListener(new ButtonActionListener());

                this.add(buttons[i][j]);

            }
        }
    }

    public void checkForWin() {
        if (numOfCells - numOfOpenedCells == (Integer) Constant.NUMBER_OF_MINES.getValue()) {
//            TODO disable statistics
            System.out.println("game is won");
            setButtons(false);
//            areButtonsActive = false;

            fireEvent(new Event(this, Command.GAME_WON));
        }
    }

    //  opens targeted cell
    public void openCell(int i, int j) {

        if (!buttons[i][j].isEnabled()) {
            return;
        }

        if (!(Boolean) Constant.CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN.getValue()) {
            if (!buttons[i][j].getIcon().toString().equals(Image.CLOSED_CELL.getImageIcon().toString())) {
                return;
            }
        }

        System.out.println("opening cell");

        numOfOpenedCells++;
        buttons[i][j].setEnabled(false);

        Image disabledIcon;

        switch (table[i][j]) {
            case 0 -> disabledIcon = Image.ZERO;
            case 1 -> disabledIcon = Image.ONE;
            case 2 -> disabledIcon = Image.TWO;
            case 3 -> disabledIcon = Image.THREE;
            case 4 -> disabledIcon = Image.FOUR;
            case 5 -> disabledIcon = Image.FIVE;
            case 6 -> disabledIcon = Image.SIX;
            case 7 -> disabledIcon = Image.SEVEN;
            case 8 -> disabledIcon = Image.EIGHT;
            case -1 -> disabledIcon = Image.MINE;
            default -> throw new IndexOutOfBoundsException("error in parsing");
        }

        buttons[i][j].setDisabledIcon(disabledIcon.getImageIcon());

        System.out.println("done opening cell");
    }

    //    opens all blank that are NEWS, ne, ns, ...,  of targeted cell
    public void openBlanks(int x, int y) {

        if (!buttons[x][y].isEnabled()) {
            return;
        }

        openCell(x, y);

        if (table[x][y] != 0) {
            return;
        }

//        table[x][y] already opened but first if statement in openCell handles that
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    openBlanks(x + i, y + j);
                } catch (Exception ignored) {

                }
            }
        }
    }

    //    main restart sequence when game is started again
    public void restart(Command command) throws Exception {
        if (command.equals(Command.NEW_GAME)) {
            System.out.println("centerPanel: restart");

            setButtons(true);
//            areButtonsActive = true;
            numOfOpenedCells = 0;

            restartButtons();

//         generate new table
            table = TableGenerator.getTable();

        } else {
            throw new Exception("unsupported command");
        }


    }

    //    enables all buttons
//    changes icon to closedCell
    private void restartButtons() {
        for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                buttons[i][j].setEnabled(true);

                try {
                    buttons[i][j].setIcon(Image.CLOSED_CELL.getImageIcon());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        }
    }

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {


            if (listeners[i] == Listener.class) {
                ((Listener) listeners[i + 1]).eventOccurred(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }

    //    handles right click operations
    private static class MouseActionListener extends MouseAdapter {
        private final int x;
        private final int y;
        private final CenterPanel panel;
        private static boolean areButtonsActive = true;
        private final JButton[][] buttons;

        public MouseActionListener(CenterPanel panel, boolean areButtonsActive, JButton[][] buttons, int x, int y) {
            this.x = x;
            this.y = y;
            this.panel = panel;
            this.buttons = buttons;
        }


        public static void setAreButtonsActive(boolean value) {
            areButtonsActive = value;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (areButtonsActive && e.getButton() != MouseEvent.BUTTON1 && panel.currentHoveredButtonX != -1) {
                System.out.println("RIGHT CLICK");


                String string = buttons[panel.currentHoveredButtonX][panel.currentHoveredButtonY].getIcon().toString();

                if (Image.CLOSED_CELL.getImageIcon().toString().equals(string)) {
                    buttons[panel.currentHoveredButtonX][panel.currentHoveredButtonY].setIcon(
                            Image.FLAG.getImageIcon()
                    );
                } else if (Image.FLAG.getImageIcon().toString().equals(string)) {
                    buttons[panel.currentHoveredButtonX][panel.currentHoveredButtonY].setIcon(
                            Image.NOT_SURE.getImageIcon()
                    );
                } else {
                    buttons[panel.currentHoveredButtonX][panel.currentHoveredButtonY].setIcon(
                            Image.CLOSED_CELL.getImageIcon()
                    );
                }
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            panel.currentHoveredButtonX = x;
            panel.currentHoveredButtonY = y;
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            panel.currentHoveredButtonX = -1;
        }

    }

    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            checkForWin();

            if (areButtonsActive) {

                for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
                    for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                        if (buttons[i][j].toString().equals(e.getSource().toString())) {

                            System.out.println("clicked " + i + " " + j);

                            if (table[i][j] != 0) {
                                openCell(i, j);

//                                game over check
                                if (table[i][j] == -1 && !buttons[i][j].isEnabled()) {
                                    System.out.println("game over");
//                                  TODO halt time
//                                    extract to new thread (swing worker)

                                    fireEvent(new Event(this, Command.GAME_OVER));

                                    SoundsManager.playGameOverSound();

                                    setButtons(false);
//                                    areButtonsActive = false;
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
