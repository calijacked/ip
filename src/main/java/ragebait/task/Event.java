package ragebait.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a period of time.
 * An Event has a description, a start date/time, and an end date/time.
 */
public class Event extends Task {

    /** Start date and time of the event */
    protected final LocalDateTime from;

    /** End date and time of the event */
    protected final LocalDateTime to;

    /**
     * Constructs a new Event task with a description, start, and end time.
     *
     * @param description Description of the event. Must not be null or empty.
     * @param from Start date/time of the event. Must not be null.
     * @param to End date/time of the event. Must not be null and not before {@code from}.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        assert from != null : "Event start datetime cannot be null";
        assert to != null : "Event end datetime cannot be null";
        assert !to.isBefore(from) : "Event end datetime must not be before start datetime";
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task from file data, with an option to mark it as done.
     *
     * @param description Description of the event.
     * @param from Start date/time of the event.
     * @param to End date/time of the event.
     * @param isDone True if the event is already completed.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this(description, from, to);
        if (isDone) {
            markDone();
        }
    }

    /**
     * Returns the start date/time of the event.
     *
     * @return LocalDateTime representing the start of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of the event.
     *
     * @return LocalDateTime representing the end of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event task suitable for file storage.
     * Format: TYPE | completion_status | description | from | to
     *
     * @return Formatted string for saving to file.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return type.getSymbol() + VERTICAL_BAR_SEPERATOR + super.toFileFormat() + VERTICAL_BAR_SEPERATOR
                + from.format(formatter) + VERTICAL_BAR_SEPERATOR + to.format(formatter);
    }

    /**
     * Returns a human-readable string representation of the Event task,
     * including its completion status and the time range.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("[%s]%s (from: %s to: %s)", type.getSymbol(), super.toString(),
                from.format(displayFormatter), to.format(displayFormatter));
    }
}
