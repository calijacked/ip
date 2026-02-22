package ragebait.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a period of time.
 * An Event has a description, a start date/time, and an end date/time.
 */
public class Event extends Task {

    /**
     * Start date and time of the event
     */
    protected LocalDateTime from;

    /**
     * End date and time of the event
     */
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with a description, start, and end time.
     *
     * @param description Description of the event.
     * @param from        Start date/time of the event.
     * @param to          End date/time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task from file data, with an option to mark it as done.
     *
     * @param description Description of the event.
     * @param from        Start date/time of the event.
     * @param to          End date/time of the event.
     * @param isDone      True if the event is already completed, false otherwise.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this(description, from, to);
        if (isDone) {
            markDone();
        }
    }

    public String toFileFormat() {
        return type.getSymbol() + VERTICAL_BAR_SEPERATOR + super.toFileFormat();
    }
    /**
     * Returns a string representation of the Event, including status, start, and end times.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("[%s]%s (from: %s to: %s)", type.getSymbol(), super.toString(),
                from.format(displayFormatter), to.format(displayFormatter));
    }
}
