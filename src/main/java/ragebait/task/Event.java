package ragebait.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a defined time range.
 *
 * An Event consists of:
 * - A description
 * - A start date and time
 * - An end date and time
 *
 * The end time must not be before the start time.
 */
public class Event extends Task {

    /** Start date and time of the event. */
    protected final LocalDateTime from;

    /** End date and time of the event. */
    protected final LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * Preconditions:
     * - from must not be null
     * - to must not be null
     * - to must not be before from
     *
     * @param description Description of the event.
     * @param from Start date and time.
     * @param to End date and time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        assert from != null : "Start date/time must not be null.";
        assert to != null : "End date/time must not be null.";
        assert !to.isBefore(from) : "End date/time must not be before start date/time.";
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event from stored data and optionally marks it as completed.
     *
     * @param description Description of the event.
     * @param from Start date and time.
     * @param to End date and time.
     * @param isDone Indicates whether the event is already completed.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this(description, from, to);
        if (isDone) {
            markDone();
        }
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start date/time.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The end date/time.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation suitable for file storage.
     *
     * Format:
     * TYPE | marked | description | from | to
     *
     * Date/time format used: dd/MM/yyyy HHmm
     *
     * @return Formatted string for persistence.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return type.getSymbol() + VERTICAL_BAR_SEPERATOR + super.toFileFormat() + VERTICAL_BAR_SEPERATOR
                + from.format(formatter) + VERTICAL_BAR_SEPERATOR + to.format(formatter);
    }

    /**
     * Returns a human-readable string representation of the Event.
     *
     * Display format: dd MMM yyyy HH:mm
     *
     * @return Formatted display string.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("[%s]%s (from: %s to: %s)", type.getSymbol(), super.toString(),
                from.format(displayFormatter), to.format(displayFormatter));
    }
}
