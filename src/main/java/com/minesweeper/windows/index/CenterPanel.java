package com.minesweeper.minesweeper.windows.index;

import com.minesweeper.eventDrivers.Command;
import com.minesweeper.minesweeperDrivers.Cell;
import com.minesweeper.minesweeperDrivers.Table;
import com.minesweeper.resourceManagers.constants.Constant;
import com.minesweeper.resourceManagers.images.Image;
import com.minesweeper.resourceManagers.sounds.SoundsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

//    TODO
//      enable/ disable left click operations while under right click element (flag, question mark)

public class CenterPanel extends JPanel implements PropertyChangeListener {

    private final PropertyChangeSupport support;

    // columns * rows
    private final int numOfCells;
    private final JButton[][] buttons;
    //    table - blueprint of field
    private Table table;
    //    can you click on button (including left and right click operations)
    private boolean areButtonsActive = true;
    //    used to check if you can declare win
    private int numOfOpenedCells = 0;
    //    for navigation in table
    private int currentHoveredButtonX;
    private int currentHoveredButtonY;
    //    used for starting timer
    private boolean isFirstButtonClicked = false;

    public CenterPanel() {

        support = new PropertyChangeSupport(this);

        setLayout(new GridLayout((int) Constant.NUMBER_OF_ROWS.getValue(),
                (int) Constant.NUMBER_OF_COLUMNS.getValue()));

        numOfCells = (Integer) Constant.NUMBER_OF_COLUMNS.getValue() * (Integer) Constant.NUMBER_OF_ROWS.getValue();

//        table = new Table((int) Constant.NUMBER_OF_ROWS.getValue(), (int) Constant.NUMBER_OF_COLUMNS.getValue(),
//                (int) Constant.NUMBER_OF_MINES.getValue());

        buttons = new JButton[(int) Constant.NUMBER_OF_ROWS.getValue()][(int) Constant.NUMBER_OF_COLUMNS.getValue()];

        for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {

                buttons[i][j] = new JButton();

                buttons[i][j].setIcon(Image.CLOSED_CELL.getImageIcon());

                buttons[i][j].addMouseListener(new RightClickListener(this, buttons, i, j));

                buttons[i][j].addActionListener(new LeftClickListener(this));

                this.add(buttons[i][j]);

            }
        }
    }


    /**
     * all logic for setting buttons
     *
     * @param value
     */
    private void setButtons(boolean value) {
        areButtonsActive = value;

        RightClickListener.setAreButtonsActive(value);
    }

    public void checkForWin() {
        if (numOfCells - numOfOpenedCells == (Integer) Constant.NUMBER_OF_MINES.getValue()) {

            System.out.println("game is won");
            setButtons(false);

            support.firePropertyChange("game won", null, Command.GAME_WON);
        }
    }

//    fixme
//      if there are flags or question marks then cells beyond them should not be opened but they are

    /**
     * opens selected cell
     *
     * @param i row
     * @param j column
     */
    public void openCell(int i, int j) {

        if (!buttons[i][j].isEnabled()) {
            return;
        }

        if (!(Boolean) Constant.CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN.getValue()) {
            if (!buttons[i][j].getIcon().toString().equals(Image.CLOSED_CELL.getImageIcon().toString())) {
                return;
            }
        }


        numOfOpenedCells++;
        buttons[i][j].setEnabled(false);

        if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.MINE) {
            buttons[i][j].setDisabledIcon(Image.MINE.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.ZERO) {
            buttons[i][j].setDisabledIcon(Image.ZERO.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.ONE) {
            buttons[i][j].setDisabledIcon(Image.ONE.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.TWO) {
            buttons[i][j].setDisabledIcon(Image.TWO.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.THREE) {
            buttons[i][j].setDisabledIcon(Image.THREE.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.FOUR) {
            buttons[i][j].setDisabledIcon(Image.FOUR.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.FIVE) {
            buttons[i][j].setDisabledIcon(Image.FIVE.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.SIX) {
            buttons[i][j].setDisabledIcon(Image.SIX.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.SEVEN) {
            buttons[i][j].setDisabledIcon(Image.SEVEN.getImageIcon());
        } else if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.EIGHT) {
            buttons[i][j].setDisabledIcon(Image.EIGHT.getImageIcon());
        }

    }

    //    opens all blank that are NEWS, ne, ns, ...,  of targeted cell
    public void openBlanks(int x, int y) {

        if (!buttons[x][y].isEnabled()) {
            return;
        }

        openCell(x, y);

        if (table.getCell(x, y).getCellStatus() == Cell.CellStatus.ZERO) {
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

    }

    public void restart() {
        System.out.println("center panel: restart");

        isFirstButtonClicked = false;
        numOfOpenedCells = 0;

        setButtons(true);
        restartButtons();
    }

    /**
     * enables all buttons and sets icon to {@code CLOSED_CELL}
     */
    private void restartButtons() {

        for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setIcon(Image.CLOSED_CELL.getImageIcon());
            }
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == Command.NEW_GAME) {
            restart();
        } else {
            System.out.println("unsupported command in center panel");
            System.out.println(evt);
        }
    }

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener p) {
        support.removePropertyChangeListener(p);
    }

    private static class RightClickListener extends MouseAdapter {
        private static boolean areButtonsActive = true;
        private final int x;
        private final int y;
        private final CenterPanel panel;
        private final JButton[][] buttons;

        public RightClickListener(CenterPanel panel, JButton[][] buttons, int x, int y) {
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

    private class LeftClickListener implements ActionListener {

        private final CenterPanel centerPanel;

        public LeftClickListener(CenterPanel centerPanel) {
            this.centerPanel = centerPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            checkForWin();

            if (areButtonsActive) {

                for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
                    for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                        if (buttons[i][j].toString().equals(e.getSource().toString())) {

                            System.out.println("clicked " + i + " " + j);

                            if (!centerPanel.isFirstButtonClicked) {
                                table = new Table((int) Constant.NUMBER_OF_ROWS.getValue(),
                                        (int) Constant.NUMBER_OF_COLUMNS.getValue(),
                                        (int) Constant.NUMBER_OF_MINES.getValue());

//                                table = new Table((int) Constant.NUMBER_OF_ROWS.getValue(),
//                                        (int) Constant.NUMBER_OF_COLUMNS.getValue(),
//                                        (int) Constant.NUMBER_OF_MINES.getValue(), i, j);

                                support.firePropertyChange("start timer", null,
                                        Command.START_TIMER);
                                centerPanel.isFirstButtonClicked = true;
                            }

                            if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.ZERO) {
                                openBlanks(i, j);

                            } else {

                                openCell(i, j);

//                                game over check
                                if (checkForGameOver(i, j)) return;
                            }

                            checkForWin();

                            return;
                        }
                    }
                }
            }
        }

        private boolean checkForGameOver(int i, int j) {
            if (table.getCell(i, j).getCellStatus() == Cell.CellStatus.MINE && !buttons[i][j].isEnabled()) {
                System.out.println("game over");

//              TODO extract to new thread (swing worker)

                support.firePropertyChange("game over", null, Command.GAME_OVER);

                SoundsManager.playGameOverSound();

                setButtons(false);
                return true;
            }
            return false;
        }
    }
}
