package main.constants;


import javax.swing.*;
import java.util.EnumSet;

public enum Image {


    VICTORY( "victory", Path.getButtonPath("victory"), "button"),
    DEFEAT("defeat", Path.getButtonPath("defeat"), "button"),
    PLAY_AGAIN("play again", Path.getButtonPath("playAgain"), "button"),


    CLOSED_CELL("closed cell", Path.getClosedTilePath("closedCell"), "closed_tiles"),
    FLAG("flag", Path.getClosedTilePath("flag"), "closed_tiles"),
    NOT_SURE("not sure", Path.getClosedTilePath("notSure"), "closed_tiles"),


    ZERO("0", Path.getOpenedTilePath("0"), "opened_tiles"),
    ONE("1", Path.getOpenedTilePath("1"), "opened_tiles"),
    TWO("2" , Path.getOpenedTilePath("2"), "opened_tiles"),
    THREE("3", Path.getOpenedTilePath("3"), "opened_tiles"),
    FOUR("4", Path.getOpenedTilePath("4"), "opened_tiles"),
    FIVE("5", Path.getOpenedTilePath("5"), "opened_tiles"),
    SIX("6", Path.getOpenedTilePath("6"), "opened_tiles"),
    SEVEN("7", Path.getOpenedTilePath("7"), "opened_tiles"),
    EIGHT("8", Path.getOpenedTilePath("8"), "opened_tiles"),
    MINE("mine", Path.getOpenedTilePath("-1"), "opened_tiles");


    private final String pathID;
    private final String jText;
    private final String group;
//    row in log
//    private final int logNumber;

//    private static int i = 1;

    Image(String jText, String pathID, String group) {
        this.pathID = pathID;
        this.jText = jText;
        this.group = group;
//        this.logNumber = Path.getRowNumber();
    }


    public String getPathID() {
        return pathID;
    }

//    public int getLogNumber() {
//        return logNumber;
//    }

    public ImageIcon getImageIcon() {
        return new ImageIcon(pathID);
    }

    public void flushImageIcon() {
        new ImageIcon(pathID).getImage().flush();
    }

    public static void flushAllImageIcons() {

        for (Image image : EnumSet.allOf(Image.class)) {
            image.getImageIcon().getImage().flush();
            System.out.println(image);
        }

    }

    public String getJText() {
        return "choose \"" + jText + "\" image";
    }

    public String getGroup() {
        return group;
    }

    public static void main(String[] args) {
        for (Image image : EnumSet.allOf(Image.class)) {
            System.out.println(image);
        }
    }
}
