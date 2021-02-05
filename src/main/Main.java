package main;

//import main.mainWindow.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

//    public static void windowLauncher() {
//        SwingUtilities.invokeLater(MainFrame::new);
//    }

//    TODO extract image loading to separate threads to make program faster
//    TODO time
//    TODO gui style

    //    FIXME
//        1. when pressed "restart all settings" images in settings window are not restarted automatically
//        2. fix position on screen of mainframe and settingswindow


    public static void main(String[] args) {
//        windowLauncher();

        SwingUtilities.invokeLater(TestWindow::new);
    }

    private static class TestWindow extends JFrame {


        public TestWindow() {

            setLocation(0, 0);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(1000, 1000);
            setLayout(new BorderLayout());

            BufferedImage bi = Loader.loadImage("/main/resources/images/custom/opened_tiles/-1.png");

            JButton oldRestartButton = new JButton();

            oldRestartButton.setIcon(new ImageIcon(bi));

            add(oldRestartButton);

            writeFile("test");
        }

//        public static BufferedImage loadImage(String path){
//            try {
//                return ImageIO.read(Loader.class.getResource(path));
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.exit(1);
//            }
//            return null;
//
//        }

        public void writeFile(String value){
            String PATH = "/remote/dir/server/";
//            String directoryName = PATH.concat(this.getClassName());
//            String fileName = id + getTimeStamp() + ".txt";

            File directory = new File("test_dir");
            if (! directory.exists()){
                directory.mkdir();
                System.out.println("dir made");
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }

//            File file = new File(directoryName + "/" + fileName);
//            try{
//                FileWriter fw = new FileWriter(file.getAbsoluteFile());
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write(value);
//                bw.close();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//                System.exit(-1);
//            }
        }

    }






}
