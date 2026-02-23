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
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index Index of the task to remove (0-based).
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }
    /**
     * Retrieves the task at the specified index.
     *
     * @param index Index of the task to retrieve (0-based).
     * @return Task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the internal list of all tasks.
     *
     * @return List of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns a formatted string of all tasks in the list for display.
     *
     * @return String listing all tasks, or a message if the list is empty.
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

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
