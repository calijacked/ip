package ragebait.task;

/**
 * Represents a generic task in the Ragebait application.
 * Each task has a description, a type, and a completion status.
 * This class is abstract and should be extended by specific task types like ToDo, Deadline, and Event.
 */
public abstract class Task {

    /** Separator used for file storage format */
    public static final String VERTICAL_BAR_SEPERATOR = " | ";

    /** Marker for completed tasks in file storage */
    public static final String MARKED = "1";

    /** Marker for uncompleted tasks in file storage */
    public static final String UNMARKED = "0";

    /** Indicates whether the task is completed */
    protected boolean isDone;

    /** Description of the task */
    protected final String description;

    /** Type of the task (TODO, DEADLINE, EVENT) */
    protected final TaskType type;

    /**
     * Constructs a new Task with a description and type.
     * The task is initially not completed.
     *
     * @param description Description of the task. Must not be null or empty.
     * @param type        Type of the task.
     */
    public Task(String description, TaskType type) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
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
     * Returns a string representing the status icon for display.
     *
     * @return "X" if completed, otherwise a blank space.
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Converts the task into a string suitable for file storage.
     * Format: completion_status | description
     *
     * @return Formatted string representing the task for storage.
     */
    public String toFileFormat() {
        return (isDone ? MARKED : UNMARKED) + VERTICAL_BAR_SEPERATOR + this.description;
    }

    /**
     * Returns a human-readable string representation of the task for display.
     * Includes status and description.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    /**
     * Checks whether the task is marked as completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isMarked() {
        return isDone;
    }

    /**
     * Returns the task's description.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return TaskType of the task.
     */
    public TaskType getType() {
        return type;
    }
}
