package ragebait.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a collection of tasks in the Ragebait application.
 * Provides methods to add, remove, retrieve, and list tasks.
 * Encapsulates an internal list of Task objects.
 */
public class TaskList {

    /** Internal list storing all tasks */
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a pre-existing list of tasks.
     * Uses the provided list directly; any external modifications will affect this TaskList.
     *
     * @param tasks Pre-existing list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added. Must not be null.
     * @throws IllegalArgumentException if task is null.
     */
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Cannot add a null task.");
        }
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index (0-based indexing).
     *
     * @param index Index of the task to remove.
     * @return The removed Task object.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index (0-based indexing).
     *
     * @param index Index of the task to retrieve.
     * @return Task at the specified index.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently stored in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns an unmodifiable view of the internal task list.
     * This prevents external modification of the internal list structure.
     *
     * @return Unmodifiable List of Task objects.
     */
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * Each task is numbered starting from 1.
     *
     * @return Formatted task list string, or a message if the list is empty.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks in the list!";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(".")
                    .append(tasks.get(i).toString());

            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return True if the task list contains no tasks, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
