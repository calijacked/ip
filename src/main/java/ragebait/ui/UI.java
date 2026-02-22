package ragebait.ui;

import java.time.LocalDateTime;

import ragebait.task.Task;

/**
 * Handles all user interactions in the Ragebait application.
 * Responsible for reading input from the user and displaying messages to the console.
 */
public class UI {

    public String getWelcome() {
        return "Oh great. You're back.\n"
                + "What disaster are we managing today?\n\n";
    }

    public String getGoodbye() {
        return "Finally. Peace and quiet.\n"
                + "Try not to create more tasks while I'm gone.\n\n";
    }

    public String getListHeader() {
        return "Here’s the chaos you’ve created:\n";
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

    public String getTaskAdded(Task t, int size) {
        return "Fine. I've added this to your never-ending list:\n  "
                + t
                + "\nYou now have " + size + " "
                + (size == 1 ? "task" : "tasks")
                + ". Impressive. Truly.\n\n";
    }

    public String getTaskRemoved(Task t, int size) {
        return "One less thing for you to ignore:\n  "
                + t + "\n"
                + "You're down to " + size + " "
                + (size == 1 ? "task" : "tasks")
                + ". Progress? Maybe.\n\n";
    }

    public String getMarked(Task t) {
        return "Oh wow, you actually finished something?\n"
                + t + "\n\n";
    }

    public String getUnmarked(Task t) {
        return "Changed your mind already?\n"
                + t + "\n\n";
    }

    public String getDateSearchHeader(LocalDateTime date) {
        return "On " + date + ", here’s what’s haunting you:\n";
    }

    public String getNoTasksOnDate() {
        return "Miraculously, you have nothing scheduled.\n"
                + "Enjoy it while it lasts.\n\n";
    }

    public String getTaskItem(int index, Task task) {
        return index + "." + task + "\n";
    }

    public String getFindHeader() {
        return "You searched. Here’s what matched your questionable memory:\n";
    }
}
