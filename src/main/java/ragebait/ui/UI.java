package ragebait.ui;

import java.util.Scanner;

import ragebait.task.Task;
import ragebait.task.TaskList;

/**
 * Handles all user interactions in the Ragebait application.
 * Responsible for reading input from the user and displaying messages to the console.
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
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Oh great.\n" + "I'm Ragebait. What disaster are we managing today?");
    }

    /**
     * Displays a message after a task is deleted and returns the message string.
     *
     * @param task The task that was removed.
     * @param size The current number of tasks remaining.
     * @return The deletion confirmation message.
     */
    public String getDelete(Task task, int size) {
        String msg = "Are you done? or just lazy? Removed:\n" + task
                + "\nNow you have " + size + " task(s) in the list.";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays a separator line on the console.
     *
     * @return The separator line string.
     */
    public String showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
        return line;
    }

    /**
     * Displays the goodbye message to the user.
     *
     * @return The goodbye message string.
     */
    public String getGoodbye() {
        String msg = "Finally. Peace and quiet. I was getting kinda tired of you.";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays the list of tasks header and task contents.
     *
     * @param tasks The TaskList containing tasks to display.
     * @return The list header message string.
     */
    public String getListHeader(TaskList tasks) {
        String msg = "Yawn. Here you go:\n" + tasks.listTasks();
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param t The task that was added.
     * @param size The updated number of tasks in the list.
     * @return The task-added confirmation message.
     */
    public String getTaskAdded(Task t, int size) {
        String msg = "Fine. I've added this to your never-ending list:\n  "
                + t
                + "\nYou now have " + size + " "
                + (size == 1 ? "task" : "tasks")
                + ". Impressive. Truly.";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays a message indicating a task has been marked as completed.
     *
     * @param t The task that was marked.
     * @return The marked task message string.
     */
    public String getMarked(Task t) {
        String msg = "Oh wow, you actually finished something?\n"
                + t;
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param t The task that was unmarked.
     * @return The unmarked task message string.
     */
    public String getUnmarked(Task t) {
        String msg = "Changed your mind already?\n"
                + t;
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays a message indicating that there are no tasks in the list.
     *
     * @return The no-tasks message string.
     */
    public String getNoTasks() {
        String msg = "Miraculously, you have nothing scheduled. Enjoy it while it lasts.";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays a message indicating that no search results were found.
     *
     * @return The no-results-found message string.
     */
    public String getNoTasksFound() {
        String msg = "Do a better job at searching. No results.";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays search result header and matched task list.
     *
     * @param tasks The TaskList containing matched tasks.
     * @return The find header message string.
     */
    public String getFindHeader(TaskList tasks) {
        String msg = "You searched. Here’s what matched your questionable memory: \n"
                + tasks.listTasks();
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
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
