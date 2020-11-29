package main.utils;

import main.Event;

import java.util.EventListener;


public interface Listener extends EventListener {

    void eventOccurred(Event event);

}
