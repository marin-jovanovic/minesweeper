package main.constants;


import javax.swing.*;

public enum Image {

//    button
    VICTORY( "victory", Path.getButtonPath("victory"), "image__button__victory"),
    DEFEAT("defeat", Path.getButtonPath("defeat"), "image__button__defeat"),
    PLAY_AGAIN("play again", Path.getButtonPath("playAgain"), "image__button__play_again"),
//    INIT("play again", Paths.getButtonPath("playAgain"),"image__button__play_again"),

//    closed tiles
    CLOSED_CELL("closed cell", Path.getClosedTilePath("closedCell"), "image__closed_tile__closed_cell"),
    FLAG("flag", Path.getClosedTilePath("flag"), "image__closed_tile__flag"),
    NOT_SURE("not sure", Path.getClosedTilePath("notSure"), "image__closed_tile__not_sure"),

//    opened tiles
    ZERO("0", Path.getOpenedTilePath("0"), "image__opened_tile__0"),
    ONE("1", Path.getOpenedTilePath("1"), "image__opened_tile__1"),
    TWO("2" , Path.getOpenedTilePath("2"), "image__opened_tile__2"),
    THREE("3", Path.getOpenedTilePath("3"), "image__opened_tile__3"),
    FOUR("4", Path.getOpenedTilePath("4"), "image__opened_tile__4"),
    FIVE("5", Path.getOpenedTilePath("5"), "image__opened_tile__5"),
    SIX("6", Path.getOpenedTilePath("6"), "image__opened_tile__6"),
    SEVEN("7", Path.getOpenedTilePath("7"), "image__opened_tile__7"),
    EIGHT("8", Path.getOpenedTilePath("8"), "image__opened_tile__8"),
    MINE("mine", Path.getOpenedTilePath("-1"), "image__opened_tile__mine");


    private final String pathID;
    private final String jText;
    private final String logID;
//    row in log
    private final int logNumber;

//    private static int i = 1;

    private Image(String jText, String pathID, String logID) {
        this.pathID = pathID;
        this.jText = jText;
        this.logID = logID;
        this.logNumber = Path.getRowNumber();

    }

    public int getLogNumber() {
        return logNumber;
    }
//
//    private Constant(String pathIDAndJText, String logID) {
//        this.pathID = pathIDAndJText;
//        this.jText = pathIDAndJText;
//        this.logID = logID;
//        logNumber = 0;
//    }

    public ImageIcon getImageIcon() {
        return new ImageIcon(pathID);
    }
    public String getJText() {
        return jText;
    }
    public String getLogID() {
        return logID;
    }


    public static void main(String[] args) {
        System.out.println(Image.EIGHT.getLogNumber());
    }
}
