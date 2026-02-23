package ragebait.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A Deadline task has a description and a due date/time.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Deadline extends Task {

    /** The due date and time of this deadline task */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and a due date/time.
     *
     * @param description Description of the task.
     * @param by Due date and time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Constructs a Deadline task from a string representation of the date (used for loading from file).
     *
     * @param description Description of the task.
     * @param byDateTime Due date/time of the task.
     * @param isDone True if the task is already completed.
     */
    public Deadline(String description, LocalDateTime byDateTime, boolean isDone) {
        this(description, byDateTime);
        if (isDone) {
            markDone();
        }
    }

    /**
     * Converts the deadline task into file storage format.
     *
     * @return Formatted string for file saving.
     */
    public String toFileFormat() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return type.getSymbol() + VERTICAL_BAR_SEPERATOR + super.toFileFormat() + VERTICAL_BAR_SEPERATOR
                + by.format(displayFormatter);
    }

    /**
     * Returns a string representation of the Deadline task, including its status and due date.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("[%s]%s (by: %s)", type.getSymbol(), super.toString(), by.format(displayFormatter));
    }
}
