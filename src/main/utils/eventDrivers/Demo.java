package main.utils.eventDrivers;

import javax.swing.event.EventListenerList;

public class Demo {
    private final EventListenerList listenerList = new EventListenerList();

    public void fireEvent(Event event) {
        Object[] listeners = listenerList.getListenerList();

        for (Object listener : listeners) {
            System.out.println(listener);
        }

        for (Object listener : listeners) {
            if (listener instanceof Listener) {
                ((Listener) listener).eventOccurred(event);
                return;
            }
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(Listener.class, listener);
    }


    public static void main(String[] args) {

//
//        centerPanel.addListener(event -> {
//            try {
//                northPanel.setRestartButton(event.getCommand());
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });

    }
}
