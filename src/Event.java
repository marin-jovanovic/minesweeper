import java.util.EventObject;

public class Event extends EventObject {

    private String command;

    public Event(Object source, String command) {
        super(source);

        this.command = command;

    }

    public String getCommand() {
        return command;
    }

}
