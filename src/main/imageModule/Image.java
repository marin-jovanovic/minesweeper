package main.imageModule;

import main.Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
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

//    private final File pathAsFile;
    private final String path;
    private ImageIcon imageIcon;
    private final File fullPath;

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getPath() {
        return path;
    }

    public File getFullPath() {
        return fullPath;
    }

    String t;

    Image(String folder, String name) {
        this.path =
            Config.getResizedImagesPath() + Config.getBackslash() +
            folder + Config.getBackslash() +
            name + Config.getDOT() + Config.getImagesFormatName();

        t =  Config.getBackslash() +
                folder + Config.getBackslash() +
                name + Config.getDOT() + Config.getImagesFormatName();

        System.out.println(new File(this.path).getAbsolutePath());

//        both return true
        System.out.println(new File(new File(this.path).getAbsolutePath()).exists());
        System.out.println(new File(this.path).exists());

//        this is slower, do not know why
//        this.imageIcon = ImageManager.loadImage(this.path);

        try {

            this.imageIcon = new ImageIcon(ImageIO.read(Loader.class.getResource(
                    Config.getReducedResizedImagesPath() + Config.getBackslash() +
                folder + Config.getBackslash() +
                name + Config.getDOT() + Config.getImagesFormatName())));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        fullPath = new File(this.path).getAbsoluteFile();

        System.out.println();

    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void flushImageIcon() {
//        this.imageIcon = new ImageIcon(getClass().getResource(this.path));
        new ImageIcon(path).getImage().flush();
        try {
            this.imageIcon = new ImageIcon(ImageIO.read(Loader.class.getResource(
                    Config.getReducedResizedImagesPath() + t)));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        new ImageIcon(path).getImage().flush();
        try {
            this.imageIcon = new ImageIcon(ImageIO.read(Loader.class.getResource(
                    Config.getReducedResizedImagesPath() + t)));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

//        this.imageIcon.paintIcon();

    }

    @Override
    public String toString() {
        return this.name() + " {" +
                "pathID='" + path + '\'' +
                '}';
    }

    public static void main(String[] args) {
        for (Image image : EnumSet.allOf(Image.class)) {
            System.out.println(image);
        }
    }

}
