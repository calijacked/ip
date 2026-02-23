package ragebait.ui;

import java.time.LocalDateTime;
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

    public String showWelcome() {
        return "Oh great. You're back.\n"
                + "What disaster are we managing today?\n\n";
    }

    public String getDelete(Task task, int size) {
        return "Are you done? or just lazy? Removed:" + task
            + "Now you have " + size + " task(s) in the list.";
    }

    public String showLine() {
        return "____________________________________________________________";
    }

    public String getGoodbye() {
        return "Finally. Peace and quiet. I was getting kinda tired of you.\n\n";
    }

    public String getListHeader(TaskList tasks) {
        return "Yawn. Here you go:\n" + tasks.listTasks();
    }

    public String getTaskAdded(Task t, int size) {
        return "Fine. I've added this to your never-ending list:\n  "
                + t
                + "\nYou now have " + size + " "
                + (size == 1 ? "task" : "tasks")
                + ". Impressive. Truly.\n\n";
    }

    public String getMarked(Task t) {
        return "Oh wow, you actually finished something?\n"
                + t + "\n\n";
    }

    public String getUnmarked(Task t) {
        return "Changed your mind already?\n"
                + t + "\n\n";
    }

    public String getNoTasks() {
        return "Miraculously, you have nothing scheduled.\n"
                + "Enjoy it while it lasts.\n\n";
    }

    public String getNoTasksFound() {
        return "Do a better job at searching. No results.";
    }

    public String getFindHeader(TaskList tasks) {
        return "You searched. Here’s what matched your questionable memory: \n"
                + tasks.listTasks();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }
    public void close() {
        sc.close();
    }
    public String readCommand() {
        return sc.nextLine().trim();
    }
    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getError(String message) {
        return "Wow. That didn’t work.\n"
                + message + "\n\n";
    }

    public String getLoadingError() {
        return "Your task file is a mess.\n"
                + "I’m starting fresh before it infects me.\n\n";
    }

    public String getEmptyLine() {
        return "\n";
    }

    public String getTaskItem(int index, Task task) {
        return index + "." + task + "\n";
    }
}


