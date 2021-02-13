package main.imagesModule;

import main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.EnumSet;

public enum Image {

    VICTORY(Config.getButtonPath(), "victory"),
    DEFEAT(Config.getButtonPath(), "defeat"),
    PLAY_AGAIN(Config.getButtonPath(), "playAgain"),

    CLOSED_CELL(Config.getClosedTiles(), "closedCell"),
    FLAG(Config.getClosedTiles(), "flag"),
    NOT_SURE(Config.getClosedTiles(), "notSure"),

    ZERO(Config.getOpenedTiles(), "0"),
    ONE(Config.getOpenedTiles(), "1"),
    TWO(Config.getOpenedTiles(), "2"),
    THREE(Config.getOpenedTiles(), "3"),
    FOUR(Config.getOpenedTiles(), "4"),
    FIVE(Config.getOpenedTiles(), "5"),
    SIX(Config.getOpenedTiles(), "6"),
    SEVEN(Config.getOpenedTiles(), "7"),
    EIGHT(Config.getOpenedTiles(), "8"),
    MINE(Config.getOpenedTiles(), "-1");

    private final String path;
    private ImageIcon imageIcon;
    private ImageIcon defaultImageIcon;


    Image(String folder, String name) {
        this.path =
                Config.getCustomImagesPath() + Config.getBackslash() +
                        folder + Config.getBackslash() +
                        name + Config.getDOT() + Config.getImagesFormatName();

//        this is slower, do not know why
//        this.imageIcon = ImageManager.loadImage(this.path);

        try {

            this.imageIcon = new ImageIcon(ImageIO.read(Main.class.getResource(
                    Config.getReducedCustomImagesPath() + Config.getBackslash() +
                            folder + Config.getBackslash() +
                            name + Config.getDOT() + Config.getImagesFormatName())));

            defaultImageIcon = new ImageIcon(ImageIO.read(Main.class.getResource(
                    Config.getReducedOriginalImagesPath() + Config.getBackslash() +
                            folder + Config.getBackslash() +
                            name + Config.getDOT() + Config.getImagesFormatName())));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        for (Image image : EnumSet.allOf(Image.class)) {
            System.out.println(image);
        }
    }

    public String getPath() {
        return path;
    }

    public void flushToDefaultImage() {
        imageIcon = defaultImageIcon;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public String toString() {
        return this.name() + " {" +
                "pathID='" + path + '\'' +
                '}';
    }

}
