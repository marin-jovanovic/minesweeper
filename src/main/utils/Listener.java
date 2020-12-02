package main.utils;

import java.util.EventListener;


public interface Listener extends EventListener {

    void eventOccurred(Event event);

}
