/**
 * Represents a basic event with a name.
 */
public class Event {
    /** The name of the event */
    private String name;

    /**
     * Constructs a new Event with the given name.
     *
     * @param name The name of the event
     */
    public Event(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the event.
     *
     * @return Event name
     */
    public String getName() {
        return name;
    }
}

