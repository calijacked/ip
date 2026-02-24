package ragebait.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A Deadline task has a description and a due date/time.
 */
public class Deadline extends Task {

    /** The due date and time of this deadline task. */
    protected final LocalDateTime by;

    /**
     * Constructs a new Deadline task with a description and due date/time.
     *
     * @param description Description of the task. Must not be null or empty.
     * @param by Due date and time of the task. Must not be null.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        assert by != null : "Deadline date cannot be null";
        this.by = by;
    }

    /**
     * Constructs a Deadline task for loading from storage, including completion status.
     *
     * @param description Description of the task.
     * @param byDateTime Due date/time of the task.
     * @param isDone True if the task is already completed; false otherwise.
     */
    public Deadline(String description, LocalDateTime byDateTime, boolean isDone) {
        this(description, byDateTime);
        if (isDone) {
            markDone();
        }
    }

    /**
     * Returns a string representation of this task in file storage format.
     * Format: TYPE | completion_status | description | due_date
     *
     * @return Formatted string suitable for saving to file.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return type.getSymbol() + VERTICAL_BAR_SEPERATOR + super.toFileFormat()
                + VERTICAL_BAR_SEPERATOR + by.format(formatter);
    }

    /**
     * Returns a human-readable string representation of this Deadline task.
     * Example: [D][✓] Submit report (by: 01 Jan 2026 12:00)
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("[%s]%s (by: %s)", type.getSymbol(), super.toString(), by.format(displayFormatter));
    }

    /**
     * Returns the due date and time of this Deadline task.
     *
     * @return The LocalDateTime representing the deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }
}
