package main;

import main.Event;

import java.util.EventListener;


public interface Listener extends EventListener {

    public void EventOccured(Event event);

}