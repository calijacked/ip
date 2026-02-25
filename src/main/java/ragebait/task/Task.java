package ragebait.task;

/**
 * Abstract base class representing a task in the application.
 *
 * A Task contains:
 * - A description
 * - A task type
 * - A completion status
 *
 * This class is intended to be extended by concrete task types
 * such as ToDo, Deadline, and Event.
 */
public abstract class Task {

    /** Separator used when formatting tasks for file storage. */
    public static final String VERTICAL_BAR_SEPERATOR = " | ";

    /** Value representing a completed task in file storage. */
    public static final String MARKED = "1";

    /** Value representing an incomplete task in file storage. */
    public static final String UNMARKED = "0";

    /** Indicates whether the task has been completed. */
    protected boolean isDone;

    /** Description of the task. */
    protected final String description;

    /** Type of the task. */
    protected final TaskType type;

    /**
     * Constructs a Task with the specified description and type.
     * The task is initially marked as not completed.
     *
     * Preconditions:
     * - description must not be null
     * - description must not be empty after trimming
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        assert description != null && !description.trim().isEmpty()
                : "Description must not be null or empty.";
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
     * Returns the status indicator used for display.
     *
     * @return "X" if the task is completed, otherwise a blank space.
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Converts the task into a string suitable for file storage.
     *
     * Format:
     * completion_status | description
     *
     * @return A formatted string representing the task.
     */
    public String toFileFormat() {
        return (isDone ? MARKED : UNMARKED) + VERTICAL_BAR_SEPERATOR + this.description;
    }

    /**
     * Returns a human-readable string representation of the task.
     *
     * The output includes the completion status indicator and description.
     *
     * @return A formatted string for display.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    /**
     * Returns whether the task is marked as completed.
     *
     * @return true if completed, false otherwise.
     */
    public boolean isMarked() {
        return isDone;
    }

    /**
     * Returns the task description.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task type.
     *
     * @return The TaskType of this task.
     */
    public TaskType getType() {
        return type;
    }
}
