package main.utils;

import main.constants.Command;

import java.util.EventObject;

public class Event extends EventObject {

    private final Command command;

    public Event(Object source, Command command) {
        super(source);
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
