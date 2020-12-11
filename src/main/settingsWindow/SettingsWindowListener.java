package main.settingsWindow;

import main.settingsWindow.settingsManager.SettingsBuffer;
import main.settingsWindow.settingsManager.SettingsManager;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SettingsWindowListener implements WindowListener {
    /**
     * Invoked the first time a window is made visible.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

//    //    for adding lines to settings.txt
//    private void checkAndAddLine(BufferedReader file, List<String> l, String k, String targ) throws IOException {
//        try {
//            l.add("number of " + k + " = " + Integer.parseInt(targ));
//            file.readLine();
//        }
//        catch (NumberFormatException exception) {
//            if (exception.getMessage().equals("null")) {
//                l.add(file.readLine());
//            }
//            else {
//                System.out.println(exception);
//
//                String replacement = Arrays
//                        .stream(targ.split(""))
//                        .filter(s -> "0123456789".contains(s))
//                        .collect(Collectors.joining());
//
//                l.add("number of " + k + " = " + replacement);
//                file.readLine();
//            }
//        }
//        catch (Exception e) {
//            System.err.println(e);
//            System.out.println("check and add line in settings frame");
//            l.add(file.readLine());
//        }
//    }
//
//
//    public ArrayList<String> getPreviousSettings() {
//        ArrayList<String> l = new ArrayList<>();
//
//        try (BufferedReader file = new BufferedReader(new FileReader(GeneralConstants.SETTINGS_MEMORY_PATH))){
//
//            String line;
//
//
////            get all file content
//            while ((line = file.readLine()) != null) {
//                l.add(line);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return l;
//    }

    /**
     * Invoked when the user attempts to close the window
     * from the window's system menu.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosing(WindowEvent e) {

        System.out.println("\nnew settings");
        SettingsManager.printCurrentStateOfBuffer();
//        SettingsBuffer.printBufferContent();

        SettingsManager.saveSettings();

//
////        newLines
////        ArrayList<String> newLines = new ArrayList<>();
////        SettingsBuffer.getAllLines().forEach((key, value) -> newLines.add(key + " " + value));
////        HashMap<String, String> lines = SettingsBuffer.
//
//
////        current settings
//        ArrayList<String> oldLines = getPreviousSettings();
//
////        update old lines
////        check if new line exist in old
////          if exist:
////            update
////          else
////            add
//
//
////        write new settings
//
//        try {
////            BufferedReader file = new BufferedReader(
////                    new FileReader(GeneralConstants.SETTINGS_MEMORY_PATH)
////            );
////
////            String line;
////
////            List<String> l = new ArrayList<>();
//////            get all file content
////            while ((line = file.readLine()) != null) {
////                l.add(line);
////            }
////
////            file.close();
//
//
////                    adds to l -> number of + k + number
////                    checks exceptions and handles if not int
////            checkAndAddLine(file, l, "rows", rowNumber);
////            checkAndAddLine(file, l, "columns", columnNumber);
////            checkAndAddLine(file, l, "mines", mineNumber);
//
//
//
//
////            l = lista
////            String result = String.join("\n", l);
////            System.out.println(result);
////
//////             write the new string with the replaced line OVER the same file
////            FileOutputStream fileOut = new FileOutputStream(GeneralConstants.SETTINGS_MEMORY_PATH);
////            fileOut.write(result.getBytes());
////            fileOut.close();
//
//            GeneralConstants.refresh();
//
////                        FIXME this bellow must be part of the code
////                    fireEvent(new main.utils.Event(this, "setting saved"));
////                    fireEvent(new main.utils.Event(origin, "settingsChanged"));
//
//        } catch (Exception er) {
//            System.out.println("Problem reading file.");
//            er.printStackTrace();
//        }

    }

    /**
     * Invoked when a window has been closed as the result
     * of calling dispose on the window.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosed(WindowEvent e) {

    }

    /**
     * Invoked when a window is changed from a normal to a
     * minimized state. For many platforms, a minimized window
     * is displayed as the icon specified in the window's
     * iconImage property.
     *
     * @param e the event to be processed
     * @see Frame#setIconImage
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Invoked when a window is changed from a minimized
     * to a normal state.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * Invoked when the Window is set to be the active Window. Only a Frame or
     * a Dialog can be the active Window. The native windowing system may
     * denote the active Window or its children with special decorations, such
     * as a highlighted title bar. The active Window is always either the
     * focused Window, or the first Frame or Dialog that is an owner of the
     * focused Window.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }

    /**
     * Invoked when a Window is no longer the active Window. Only a Frame or a
     * Dialog can be the active Window. The native windowing system may denote
     * the active Window or its children with special decorations, such as a
     * highlighted title bar. The active Window is always either the focused
     * Window, or the first Frame or Dialog that is an owner of the focused
     * Window.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
