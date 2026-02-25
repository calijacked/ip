package ragebait.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a collection of Task objects.
 *
 * Provides operations to:
 * - Add tasks
 * - Remove tasks
 * - Retrieve tasks
 * - List tasks
 *
 * The internal list is encapsulated to prevent direct external modification.
 */
public class TaskList {

    /** Internal list storing all tasks. */
    private final List<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList initialized with the given tasks.
     *
     * A defensive copy of the provided list is created to prevent
     * external modifications from affecting this TaskList.
     *
     * @param tasks List of tasks used to initialize this TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added. Must not be null.
     * @throws IllegalArgumentException if task is null.
     */
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null.");
        }
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * Indexing is zero-based.
     *
     * @param index Index of the task to remove.
     * @return The removed Task.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * Indexing is zero-based.
     *
     * @param index Index of the task to retrieve.
     * @return The Task at the specified index.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns an unmodifiable view of the task list.
     *
     * This prevents modification of the internal list structure.
     *
     * @return Unmodifiable list of tasks.
     */
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns a formatted string representation of all tasks.
     *
     * Tasks are numbered starting from 1.
     * If the list is empty, a message is returned.
     *
     * @return Formatted string of tasks or an empty-list message.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Task list is empty.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString());

            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns whether the task list contains no tasks.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
