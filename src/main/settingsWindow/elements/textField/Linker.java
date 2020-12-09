package main.settingsWindow.elements.textField;



public enum Linker {
    ROW_NUMBER("row number:", "row number = 10"),
    COLUMN_NUMBER("column number:", "column number = 10"),
    MINE_NUMBER("mine number:", "mine number = 2");

    private String frontEnd;
    private String backEnd;

    public String getFrontEnd() {
        return frontEnd;
    }

    public String getBackEnd() {
        return backEnd;
    }

    private Linker(String frontEnd, String backEnd) {
        this.frontEnd = frontEnd;
        this.backEnd = backEnd;
    }

}


//package main.constants.imageDrivers;
//
//        import main.constants.imageDrivers.ImagesConstants;
//
//        import javax.swing.*;
//
//public enum ButtonStatus {
//    VICTORY("victory"),
//    DEFEAT("defeat"),
//    PLAY_AGAIN("playAgain"),
//    INIT("playAgain");
//
//    private String path;
//
//    public ImageIcon getImageIcon() {
//        return new ImageIcon(ImagesConstants.RESIZED_IMAGES_PATH + ImagesConstants.BUTTON_PATH + this.path + ImagesConstants.DOT +
//                ImagesConstants.IMAGES_FORMAT_NAME);
//    }
//
//    ButtonStatus(String path) {
//        this.path = path;
//    }
//}
