package main.settingsWindow;

import main.utils.Event;
import main.utils.Listener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import java.util.Arrays;
import java.util.stream.Collectors;
//import java.util.Set;

public class TextFieldActionListener implements DocumentListener {

    private final JTextField source;
    private final String target;
    private String data;

    public TextFieldActionListener(JTextField source, String target) {
        this.source = source;
        this.target = target;
        this.data = "";
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        processData();

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        processData();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        processData();

    }

    public void processData() {
        data = source.getText();

        System.out.println(data);

        switch (target) {
            case "columnNumber" -> {
                SettingsFrame.setColumnNumber(data);

                try {
                    int test = Integer.parseInt(data);

                }
                catch (NumberFormatException exception) {
                    if (exception.getMessage().equals("null")) {

//                        FIXME this bellow must be part of the code
//                        fireEvent(new main.utils.Event(this, "columnNumber emptyCell"));

                    }
                    else {
                        System.out.println(exception);

                        String replacement = Arrays
                                .stream(data.split(""))
                                .filter(s -> "0123456789".contains(s))
                                .collect(Collectors.joining());

                        //                        FIXME this bellow must be part of the code
//                        fireEvent(new main.utils.Event(this, "columnNumber replacement: " + replacement));


                    }
                }
                catch (Exception e) {
                    System.err.println(e);
                    System.out.println("new error in text field action listenr");
                }
//                fireEvent(new main.utils.Event(this, "gameWon"));

            }
            case "rowNumber" -> SettingsFrame.setRowNumber(data);
            case "mineNumber" -> SettingsFrame.setMineNumber(data);
            default -> System.out.println("error with target");
        }

    }

    private EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if(listeners[i] == Listener.class) {
                ((Listener)listeners[i+1]).eventOccurred(event);
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }
}
