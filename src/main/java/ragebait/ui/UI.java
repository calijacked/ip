package ragebait.ui;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.task.Task;
import ragebait.task.TaskList;

/**
 * Handles all user interactions in the Ragebait application.
 * Responsible for generating response messages and reading user input.
 */
public class UI {
    /**
     * Returns the welcome message displayed when the program starts.
     *
     * @return Welcome message string.
     */
    public String getWelcome() {
        return "Hello! I'm Ragebait. What disaster are we managing today?";
    }

    /**
     * Returns a message after a task is deleted.
     *
     * @param task The task that was removed.
     * @param size The current number of tasks remaining.
     * @return Deletion confirmation message.
     */
    public String getDelete(Task task, int size) {
        return "Are you done? or just lazy? Removed:\n" + task
                + "\nNow you have " + size + " task(s) in the list.";
    }

    /**
     * Returns a visual separator line.
     *
     * @return Separator line string.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Returns the goodbye message displayed when the program exits.
     *
     * @return Goodbye message string.
     */
    public String getGoodbye() {
        return "Finally. Peace and quiet. I was getting kinda tired of you.";
    }

    /**
     * Returns a formatted string listing all tasks in the TaskList.
     *
     * @param tasks The TaskList containing tasks to display.
     * @return Formatted task list string.
     */
    public String getListHeader(TaskList tasks) {
        return "Yawn. Here you go:\n" + tasks.listTasks();
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param t    The task that was added.
     * @param size The updated number of tasks in the list.
     * @return Task-added confirmation message.
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
     * @return Marked task message string.
     */
    public String getMarked(Task t) {
        return "Oh wow, you actually finished something?\n" + t;
    }

    /**
     * Returns a message indicating a task has been unmarked.
     *
     * @param t The task that was unmarked.
     * @return Unmarked task message string.
     */
    public String getUnmarked(Task t) {
        return "Changed your mind already?\n" + t;
    }

    /**
     * Returns a message indicating that there are no tasks in the list.
     *
     * @return No-tasks message string.
     */
    public String getNoTasks() {
        return "Miraculously, you have nothing scheduled. Enjoy it while it lasts.";
    }

    /**
     * Returns a message indicating that no search results were found.
     *
     * @return No-results-found message string.
     */
    public String getNoTasksFound() {
        return "Do a better job at searching. No results.";
    }

    /**
     * Returns a formatted string listing tasks matching a search keyword.
     *
     * @param tasks The TaskList containing matched tasks.
     * @return Formatted search results string.
     */
    public String getFindHeader(TaskList tasks) {
        return "You searched. Here’s what matched your questionable memory:\n"
                + tasks.listTasks();
    }

    /**
     * Returns a given error message.
     *
     * @param message The error message to display.
     * @return The error message string.
     */
    public String showError(String message) {
        return message;
    }

    public String getContactAdded(Contact contact, int size) {
        return "Fine. I've added this to your contacts list:\n"
                + contact
                + "\nYou now have " + size + " "
                + (size == 1 ? "task" : "tasks");
    }

    public String getDeleteContact(Contact selectedContact, int size) {
        return "Are you two still friends?\n" + selectedContact
                + "\nNow you have " + size + " contact(s) in the list.";
    }

    public String getNoContactsFound() {
        return "Do a better job at searching. Or do you have no friends?";
    }

    public String getFindContactHeader(ContactList matchingContacts) {
        return "You searched. Here are your contacts lol:\n"
                + matchingContacts.listContacts();
    }
    public String getNoContacts() {
        return "WOW. You actually have nobody in your life.";
    }

    public String getContactList(ContactList contacts) {
        return "Yawn. Contacts:\n" + contacts.listContacts();
    }
}

