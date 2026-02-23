package ragebait.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Ragebait application.
 * Provides methods to add, remove, retrieve, and list tasks.
 */
public class TaskList {

    /** Internal list storing all tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks Pre-existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index (0-based indexing).
     *
     * @param index Index of the task to remove.
     * @return The removed Task object.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index (0-based indexing).
     *
     * @param index Index of the task to retrieve.
     * @return Task at the specified index.
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
     * Returns the internal list containing all tasks.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     *
     * @return Formatted task list string, or a message if the list is empty.
     */
    public String listTasks() {
        int lastTask = tasks.size() - 1;
        if (tasks.isEmpty()) {
            return "No tasks in the list!";
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
                    .append(".")
                    .append(tasks.get(i).toString());

            if (i < lastTask) {
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
