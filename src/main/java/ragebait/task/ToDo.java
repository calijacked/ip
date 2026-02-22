package ragebait.task;

/**
 * Represents a To-Do task in the Ragebait application.
 * A To-Do task only has a description and a completion status,
 * and does not require any date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a new To-Do task with the given description.
     * The task is initially not completed.
     *
     * @param description Description of the To-Do task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructs a To-Do task with a given description and completion status.
     *
     * @param description Description of the To-Do task.
     * @param isDone      Whether the task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO);
        if (isDone) {
            markDone();
        }
    }

    /**
     * Returns a string representation of the To-Do task for display purposes.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", type.getSymbol(), super.toString());
    }
}
