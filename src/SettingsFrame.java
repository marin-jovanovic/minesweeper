import javax.imageio.ImageIO;
        import javax.swing.*;
        import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.EventListener;

public class SettingsFrame extends JFrame {

//    private NorthPanel northPanel;
//    private CenterPanel centerPanel;

    public SettingsFrame() {
        JButton btn = new JButton("browse");
        add(btn);
        System.out.println("dodan novi gumbic");

        setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
                    setVisible(true);
                    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//set

        setSize(Constants.WIDTH, Constants.HEIGHT);


        //Create a file chooser

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Handle open button action.
                final JFileChooser fc = new JFileChooser();
//                int returnVal = fc.showOpenDialog(aComponent);

//                if (e.getSource() == openButton) {
                    int returnVal = fc.showOpenDialog(SettingsFrame.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        //This is where a real application would open the file.
                        System.out.println(file.getName());
//                        log.append("Opening: " + file.getName() + "." + newline);
                    } else {
                        System.out.println("err");
//                        log.append("Open command cancelled by user." + newline);
                    }
//                }
            }
        });

    }

//    private class Utils {

        public final static String jpeg = "jpeg";
        public final static String jpg = "jpg";
        public final static String gif = "gif";
        public final static String tiff = "tiff";
        public final static String tif = "tif";
        public final static String png = "png";

        /*
         * Get the extension of a file.
         */
        public String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 &&  i < s.length() - 1) {
                ext = s.substring(i+1).toLowerCase();
            }
            return ext;
        }

    public String getTypeDescription(File f) {
        String extension = getExtension(f);
        String type = null;

        if (extension != null) {
            if (extension.equals(jpeg) ||
                    extension.equals(jpg)) {
                type = "JPEG Image";
            } else if (extension.equals(gif)){
                type = "GIF Image";
            } else if (extension.equals(tiff) ||
                    extension.equals(tif)) {
                type = "TIFF Image";
            } else if (extension.equals(png)){
                type = "PNG Image";
            }
        }
        return type;
    }

//    public Icon getIcon(File f) {
//        String extension = getExtension(f);
//        Icon icon = null;
//
//        if (extension != null) {
//            if (extension.equals(jpeg) ||
//                    extension.equals(jpg)) {
//                icon = jpgIcon;
//
//            } else if (extension.equals(gif)) {
//                icon = gifIcon;
//            } else if (extension.equals(tiff) ||
//                    extension.equals(tif)) {
//                icon = tiffIcon;
//            } else if (extension.equals(png)) {
//                icon = pngIcon;
//            }
//        }
//        return icon;
//    }

//    }


}
