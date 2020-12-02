package main.utils;

import main.constants.Commands;

import java.util.EventObject;

public class Event extends EventObject {

    private final Commands command;

    public Event(Object source, Commands command) {
        super(source);

        this.command = command;

    }

    public Commands getCommand() {
        return command;
    }

}
