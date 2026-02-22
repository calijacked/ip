package ragebait.task;

/**
 * Represents a generic task in the Ragebait application.
 * Each task has a description, a type, and a completion status.
 * This class is abstract and should be extended by specific task types like ToDo, Deadline, and Event.
 */
public abstract class Task {

    /**
     * Whether the task is completed
     */
    protected boolean isDone;

    /**
     * Description of the task
     */
    protected String description;

    /**
     * Type of the task (TODO, DEADLINE, EVENT)
     */
    protected TaskType type;

    /**
     * Constructs a new Task with a description and type.
     * The task is initially not completed.
     *
     * @param description Description of the task.
     * @param type        Type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description.trim();
        this.type = type;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns a string representing the status of the task for file storage.
     *
     * @return "1" if completed, "0" if not completed.
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    abstract String toFileFormat();
    /**
     * Returns a string representation of the task for display purposes,
     * including its type, status, and description.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
