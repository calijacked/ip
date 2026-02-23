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

    public UI() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Oh great.\n" + "I'm Ragebait. What disaster are we managing today?");
    }

    public String getDelete(Task task, int size) {
        String msg = "Are you done? or just lazy? Removed:\n" + task
                + "\nNow you have " + size + " task(s) in the list.";
        System.out.println(msg);
        return msg;
    }

    public String showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
        return line;
    }

    public String getGoodbye() {
        String msg = "Finally. Peace and quiet. I was getting kinda tired of you.";
        System.out.println(msg);
        return msg;
    }

    public String getListHeader(TaskList tasks) {
        String msg = "Yawn. Here you go:\n" + tasks.listTasks();
        System.out.println(msg);
        return msg;
    }

    public String getTaskAdded(Task t, int size) {
        String msg = "Fine. I've added this to your never-ending list:\n  "
                + t
                + "\nYou now have " + size + " "
                + (size == 1 ? "task" : "tasks")
                + ". Impressive. Truly.";
        System.out.println(msg);
        return msg;
    }

    public String getMarked(Task t) {
        String msg = "Oh wow, you actually finished something?\n"
                + t;
        System.out.println(msg);
        return msg;
    }

    public String getUnmarked(Task t) {
        String msg = "Changed your mind already?\n"
                + t;
        System.out.println(msg);
        return msg;
    }

    public String getNoTasks() {
        String msg = "Miraculously, you have nothing scheduled. Enjoy it while it lasts.";
        System.out.println(msg);
        return msg;
    }

    public String getNoTasksFound() {
        String msg = "Do a better job at searching. No results.";
        System.out.println(msg);
        return msg;
    }

    public String getFindHeader(TaskList tasks) {
        String msg = "You searched. Here’s what matched your questionable memory: \n"
                + tasks.listTasks();
        System.out.println(msg);
        return msg;
    }

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
        String msg = "Wow. That didn’t work.\n"
                + message + "\n\n";
        System.out.println(msg);
        return msg;
    }

    public String getLoadingError() {
        String msg = "Your task file is a mess.\n"
                + "I’m starting fresh before it infects me.\n\n";
        System.out.println(msg);
        return msg;
    }

    public String getEmptyLine() {
        String msg = "\n";
        System.out.println(msg);
        return msg;
    }

    public String getTaskItem(int index, Task task) {
        String msg = index + "." + task + "\n";
        System.out.println(msg);
        return msg;
    }
}