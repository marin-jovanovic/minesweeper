package main.settingsWindow;

import main.constantsModule.Config;
import main.constantsModule.ConstantsManager;
import main.utils.eventDrivers.Command;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsWindowListener implements WindowListener {

    private static final SettingsWindowListener instance = new SettingsWindowListener();

    public static SettingsWindowListener getInstance() {
        return instance;
    }

    private final PropertyChangeSupport support;
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    private SettingsWindowListener() {
        support = new PropertyChangeSupport(this);
//        this.addListener(event -> MainFrame.restartSequence());
    }

    /**
     * Invoked the first time a window is made visible.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Invoked when the user attempts to close the window
     * from the window's system menu.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosing(WindowEvent e) {

        ConstantsManager.updateConstants(Config.getConstantsMemoryPath());
        support.firePropertyChange("settings window closing", null, Command.RESTART_MAINFRAME);

//        fireEvent(new main.utils.eventDrivers.Event(this, Command.RESTART_MAINFRAME));
    }

//    private final EventListenerList listenerList = new EventListenerList();

//    public void fireEvent(Event event) {
//        Object[] listeners = listenerList.getListenerList();
//
//        for (Object listener : listeners) {
//            if (listener instanceof Listener) {
//                ((Listener) listener).eventOccurred(event);
//                return;
//            }
//        }
//    }
//
//    public void addListener(Listener listener) {
//        listenerList.add(Listener.class, listener);
//    }


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
