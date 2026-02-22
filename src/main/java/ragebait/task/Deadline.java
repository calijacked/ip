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
     * @param byDateTime Due date/time in the format "dd/MM/yyyy HHmm".
     * @param isDone True if the task is already completed, false otherwise.
     */
    public Deadline(String description, LocalDateTime byDateTime, boolean isDone) {
        this(description, byDateTime);
        if (isDone) {
            markDone();
        }
    }

    public String toFileFormat() {
        return type.getSymbol() + VERTICAL_BAR_SEPERATOR + super.toFileFormat();
    }
    /**
     * Returns a string representation of the Deadline task, including its status and due date.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("[%s]%s (by: %s", type.getSymbol(), super.toString(), by.format(formatter));
    }
}
