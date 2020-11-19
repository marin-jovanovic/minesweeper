import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CenterPanel extends  JPanel {

    private JButton[][] buttons;
    private int[][] table;


    public CenterPanel() {

        setSize(200, 100);
        setLayout(new GridLayout(Constants.NUMBER_OF_ROWS, Constants.NUMBER_OF_COLUMNS));

        buttons = new JButton[Constants.NUMBER_OF_ROWS][Constants.NUMBER_OF_COLUMNS];

        tableDriver();

        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {

                buttons[i][j] = new JButton();
                this.add(buttons[i][j]);

                try {
                    Image img = ImageIO.read(getClass().getResource("resources/closedCell.png"));
                    Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
                    buttons[i][j].setIcon(new ImageIcon(newimg));

                } catch (IOException e) {
                    e.printStackTrace();
                }

                buttons[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        checkForWin();

                        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++){
                            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {

                                if (areButtonsActive) {
                                    if(buttons[i][j].toString().equals(e.getSource().toString())) {
                                        System.out.println("clicked " + i + " " + j);

                                        if (table[i][j] != 0) {
                                            openCell(i, j);

                                            if (table[i][j] == -1) {
                                                System.out.println("game over");
//                                          TODO halt time
//                                          extract to new thread (swing worker)
                                                fireEvent(new Event(this, "gameOver"));
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
                });
            }
        }
    }

//    if field with mine is opened game is over
//    all buttons must be locked
//    this is controler for it
    private boolean areButtonsActive = true;
    private int numOfOppenedCells = 0;


    public void checkForWin() {
        int numOfCells = Constants.NUMBER_OF_COLUMNS * Constants.NUMBER_OF_ROWS;
        boolean isGameWon = (numOfCells - numOfOppenedCells == Constants.NUMBER_OF_MINES);
        System.out.println("num of cells " + numOfCells);
        System.out.println("opene        " + numOfOppenedCells);
        System.out.println("bobmbs       " + Constants.NUMBER_OF_MINES);
        System.out.println("control table");

        if (isGameWon) {
//                    TODO disable statistics
            System.out.println("game is won");
            fireEvent(new Event(this, "gameWon"));
            areButtonsActive = false;
        }

    }

//  opens targeted cell
    public void openCell(int i, int j){

        if (!buttons[i][j].isEnabled()) {
            return;
        }

        numOfOppenedCells++;
        System.out.println("otvaram " + i+ " " + j);

        buttons[i][j].setEnabled(false);

        int num = table[i][j];
        if (num == 9){
            num = 0;
        }

        try {
            Image img;

            try {
                img = ImageIO.read(getClass().getResource("resources/" + num + ".png"));

            }
            catch (Exception dde) {
                img = ImageIO.read(getClass().getResource("resources/" + num + ".jpg"));
            }

            Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;

            buttons[i][j].setDisabledIcon(new ImageIcon(newimg));
            buttons[i][j].setIcon(new ImageIcon(newimg));

        } catch (Exception ex) {
            System.out.println("errr");
            System.out.println(ex);
        }
    }

//    private int[][] controlTable = new int[Constants.NUMBER_OF_ROWS][Constants.NUMBER_OF_COLUMNS];


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

        return;
    }

//    main restart sequnce when game is started again
    public void restart() {
        System.out.println("##### new game ######");

        areButtonsActive = true;
        numOfOppenedCells = 0;

        restartButtons();

        tableDriver();

    }

//    generates and prints new table
    private void tableDriver() {
        table = TableGenerator.getTable();
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

//    enables all buttons
    private void restartButtons() {
        for (int i = 0; i < Constants.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_COLUMNS; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setIcon(null);
                buttons[i][j].setDisabledIcon(null);
            }
        }
    }


    private EventListenerList listenerList = new EventListenerList();

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
