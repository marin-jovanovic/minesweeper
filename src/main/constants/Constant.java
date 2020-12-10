package main.constants;

import main.constants.imageDrivers.ImagesConstants;

import javax.swing.*;

public enum Constant {

//    button
    VICTORY( "victory", Paths.getButtonPath("victory"), "image__button__victory"),
    DEFEAT("defeat", Paths.getButtonPath("defeat"), "image__button__defeat"),
    PLAY_AGAIN("play again", Paths.getButtonPath("playAgain"), "image__button__play_again"),
    INIT("play again", Paths.getButtonPath("playAgain"),"image__button__play_again"),

//    closed tiles
    CLOSED_CELL("closed cell", Paths.getClosedTilePath("closedCell"), "image__closed_tile__closed_cell"),
    FLAG("flag", Paths.getClosedTilePath("flag"), "image__closed_tile__flag"),
    NOT_SURE("not sure", Paths.getClosedTilePath("notSure"), "image__closed_tile__not_sure"),

//    opened tiles
    ZERO("0", Paths.getOpenedTilePath("0"), "image__opened_tile__0"),
    ONE("1", Paths.getOpenedTilePath("1"), "image__opened_tile__1"),
    TWO("2" , Paths.getOpenedTilePath("2"), "image__opened_tile__2"),
    THREE("3", Paths.getOpenedTilePath("3"), "image__opened_tile__3"),
    FOUR("4", Paths.getOpenedTilePath("4"), "image__opened_tile__4"),
    FIVE("5", Paths.getOpenedTilePath("5"), "image__opened_tile__5"),
    SIX("6", Paths.getOpenedTilePath("6"), "image__opened_tile__6"),
    SEVEN("7",Paths.getOpenedTilePath("7"), "image__opened_tile__7"),
    EIGHT("8",Paths.getOpenedTilePath("8"), "image__opened_tile__8"),
    MINE("mine", Paths.getOpenedTilePath("-1"), "image__opened_tile__mine");


    private final String pathID;
    private final String jText;
    private final String logID;

    private Constant(String jText, String pathID, String logID) {
        this.pathID = pathID;
        this.jText = jText;
        this.logID = logID;
    }

    private Constant(String pathIDAndJText, String logID) {
        this.pathID = pathIDAndJText;
        this.jText = pathIDAndJText;
        this.logID = logID;
    }

    public ImageIcon getImageIcon() {
        return new ImageIcon(pathID);
    }
    public String getjText() {
        return jText;
    }
    public String getLogID() {
        return logID;
    }
}
