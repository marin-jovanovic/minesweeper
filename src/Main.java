import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void windowLauncher() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setLocation(Constants.LOCATION_X, Constants.LOCATION_Y);
                mainFrame.setVisible(true);
                mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            });
        }
        catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("main loading");
        windowLauncher();
    }


}
