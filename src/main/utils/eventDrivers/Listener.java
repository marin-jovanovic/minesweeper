package main.utils.eventDrivers;

import main.utils.eventDrivers.Event;

import java.util.EventListener;


public interface Listener extends EventListener {

    void eventOccurred(Event event);

}
