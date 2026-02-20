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
     * @param byStr Due date/time as a string in the format "dd/MM/yyyy HHmm".
     * @param isDone True if the task is already completed, false otherwise.
     */
    public Deadline(String description, String byStr, boolean isDone) {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.by = LocalDateTime.parse(byStr, formatter);
        if (isDone) {
            markDone();
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its status and due date.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("[D]%s (by: %s", by.format(formatter));
    }
}
