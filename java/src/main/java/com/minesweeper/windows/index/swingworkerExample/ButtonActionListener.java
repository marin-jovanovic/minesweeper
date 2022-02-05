package main.windows.index.swingworkerExample;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {

    private final JButton[][] buttons;
    private final int[][] table;
    private final JButton currentHoveredButton;

    //    if field with mine is opened game is over
//    all buttons must be locked
//    this is controller for it
    private boolean areButtonsActive = true;
    private int numOfOpenedCells = 0;

    public ButtonActionListener(JButton[][] buttons, int[][] table, JButton currentHoveredButton,
                                boolean areButtonsActive, int numOfOpenedCells) {
        this.buttons = buttons;
        this.table = table;
        this.currentHoveredButton = currentHoveredButton;
        this.areButtonsActive = areButtonsActive;
        this.numOfOpenedCells = numOfOpenedCells;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        checkForWin();
//        if (areButtonsActive) {
//            for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++){
//                for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
//                    if(buttons[i][j].toString().equals(e.getSource().toString())) {
//                        System.out.println("clicked " + i + " " + j);
//
//                        if (table[i][j] != 0) {
//                            openCell(i, j);
//                            if (table[i][j] == -1) {
//                                System.out.println("game over");
//                                //                                          extract to new thread (swing worker)
//                                //                                                defeat
//                                fireEvent(new main.eventDrivers.Event(this, "gameOver"));
//                                areButtonsActive = false;
//                                return;
//                            }
//                        }
//
//                        if (table[i][j] == 0) {
//                            openBlanks(i, j);
//                        }
//
//                        checkForWin();
//
//                        System.out.println("*** halt ***");
//                        System.out.println();
//                        return;
//                    }
//                }
//            }
//        }
    }
}