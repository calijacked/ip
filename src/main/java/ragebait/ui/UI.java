package ragebait.ui;

import java.util.Scanner;

import ragebait.task.Task;
import ragebait.task.TaskList;

/**
 * Handles all user interactions in the Ragebait application.
 * Responsible for generating response messages for display.
 */
public class UI {

    /** Scanner for reading user input */
    private final Scanner sc;

    /**
     * Constructs a UI object and initializes the input scanner.
     */
    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the welcome message.
     *
     * @return The welcome message string.
     */
    public String getWelcome() {
        return "Hello! I'm Ragebait. What disaster are we managing today?";
    }

    /**
     * Returns a message after a task is deleted.
     *
     * @param task The task that was removed.
     * @param size The current number of tasks remaining.
     * @return The deletion confirmation message.
     */
    public String getDelete(Task task, int size) {
        return "Are you done? or just lazy? Removed:\n" + task
                + "\nNow you have " + size + " task(s) in the list.";
    }

    /**
     * Returns a separator line.
     *
     * @return The separator line string.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Returns the goodbye message.
     *
     * @return The goodbye message string.
     */
    public String getGoodbye() {
        return "Finally. Peace and quiet. I was getting kinda tired of you.";
    }

    /**
     * Returns the list header and task contents.
     *
     * @param tasks The TaskList containing tasks to display.
     * @return The formatted task list string.
     */
    public String getListHeader(TaskList tasks) {
        return "Yawn. Here you go:\n" + tasks.listTasks();
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param t The task that was added.
     * @param size The updated number of tasks in the list.
     * @return The task-added confirmation message.
     */
    public String getTaskAdded(Task t, int size) {
        return "Fine. I've added this to your never-ending list:\n  "
                + t
                + "\nYou now have " + size + " "
                + (size == 1 ? "task" : "tasks")
                + ". Impressive. Truly.";
    }

    /**
     * Returns a message indicating a task has been marked as completed.
     *
     * @param t The task that was marked.
     * @return The marked task message string.
     */
    public String getMarked(Task t) {
        return "Oh wow, you actually finished something?\n" + t;
    }

    /**
     * Returns a message indicating a task has been unmarked.
     *
     * @param t The task that was unmarked.
     * @return The unmarked task message string.
     */
    public String getUnmarked(Task t) {
        return "Changed your mind already?\n" + t;
    }

    /**
     * Returns a message indicating that there are no tasks in the list.
     *
     * @return The no-tasks message string.
     */
    public String getNoTasks() {
        return "Miraculously, you have nothing scheduled. Enjoy it while it lasts.";
    }

    /**
     * Returns a message indicating that no search results were found.
     *
     * @return The no-results-found message string.
     */
    public String getNoTasksFound() {
        return "Do a better job at searching. No results.";
    }

    /**
     * Returns search result header and matched task list.
     *
     * @param tasks The TaskList containing matched tasks.
     * @return The formatted find results string.
     */
    public String getFindHeader(TaskList tasks) {
        return "You searched. Here’s what matched your questionable memory:\n"
                + tasks.listTasks();
    }

    /**
     * Returns an error message.
     *
     * @param message The error message.
     * @return The error message string.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Closes the scanner resource used for input.
     */
    public void close() {
        sc.close();
    }

    /**
     * Reads a command input from the user.
     *
     * @return The trimmed user command string.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }
}