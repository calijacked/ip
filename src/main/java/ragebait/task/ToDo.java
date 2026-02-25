package ragebait.task;

/**
 * Represents a ToDo task.
 *
 * A ToDo task:
 * - Has a description
 * - Has a completion status
 * - Does not include any date or time information
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task with the specified description.
     * The task is initially marked as not completed.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Creates a ToDo task with the specified description
     * and completion status.
     *
     * @param description Description of the task.
     * @param isDone Indicates whether the task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO);
        if (isDone) {
            markDone();
        }
    }

    /**
     * Returns a string representation suitable for file storage.
     *
     * Format:
     * TYPE | completion_status | description
     *
     * @return Formatted storage string.
     */
    @Override
    public String toFileFormat() {
        return type.getSymbol() + VERTICAL_BAR_SEPERATOR + super.toFileFormat();
    }

    /**
     * Returns a human-readable string representation of the task.
     *
     * The output includes:
     * - Task type symbol
     * - Completion status
     * - Description
     *
     * @return Formatted display string.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", type.getSymbol(), super.toString());
    }
}
