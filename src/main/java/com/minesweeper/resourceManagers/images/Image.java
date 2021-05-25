package com.minesweeper.resourceManagers.images;

import com.minesweeper.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Objects;

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
    MINE(Config.getOpenedTiles(), "-1"),

    T_ZERO(Config.getTimeTiles(), "0"),
    T_ONE(Config.getTimeTiles(), "1"),
    T_TWO(Config.getTimeTiles(), "2"),
    T_THREE(Config.getTimeTiles(), "3"),
    T_FOUR(Config.getTimeTiles(), "4"),
    T_FIVE(Config.getTimeTiles(), "5"),
    T_SIX(Config.getTimeTiles(), "6"),
    T_SEVEN(Config.getTimeTiles(), "7"),
    T_EIGHT(Config.getTimeTiles(), "8"),
    T_NINE(Config.getTimeTiles(), "9");

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
            this.imageIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(Main.class.getResource(
                    Config.getReducedCustomImagesPath() + Config.getBackslash() +
                            folder + Config.getBackslash() +
                            name + Config.getDOT() + Config.getImagesFormatName()))));

            defaultImageIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(Main.class.getResource(
                    Config.getReducedOriginalImagesPath() + Config.getBackslash() +
                            folder + Config.getBackslash() +
                            name + Config.getDOT() + Config.getImagesFormatName()))));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error in Image.java while loading images");

            System.exit(-1);
        }

    }

    public static void main(String[] args) {
//            JFrame f = new JFrame();
//            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            //Since I'm not setting a layout manager to contentPane, it's default (BorderLayout) is used
//            String p = "/images/resized_images/opened_tiles/-1.png";
//            //This sets the image in JFrame's content area
////            f.getContentPane().add(new JLabel(new ImageIcon(p)));
//
//        JButton button = new JButton();
//        try {
//            BufferedImage img = ImageIO.read(Objects.requireNonNull(Image.class.getResource(p)));
//            button.setIcon(new ImageIcon(img));
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        button.addActionListener(e -> {
//            System.out.println("pressed");
//        });
//        f.add(button);
////        URL urlConfig = MyClass.class.getResource("/settings/config.ini"); //by default "src/main/resources/" is in classpath and no config needs to be changed.
////        InputStream inputAvatar = MyClass.class.getResourceAsStream("/myAvatar.gif"); //with changes in pom.xml now "src/main/images" is counted as resource folder, and added to classpath. So we use it directly.
//        //
////        BufferedImage img = ImageIO.read(Image.class.getResource("com/minesweeper/resources/images/resized_images/opened_tiles/-1.png"));
////            f.add(new JButton(new ImageIcon(String.valueOf(img))));
//
//
//            //This sets JFrame's icon (shown in top left corner of JFrame)
////            f.setIconImage(new ImageIcon("com/minesweeper/resources/images/resized_images/opened_tiles/-1.png").getImage());
//
//            f.setBounds(300, 200, 400, 300);
//            f.setVisible(true);
//

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
