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

    public String getWelcome() {
        return "Oh look who’s back. I’m Ragebait Bot. How can I help you today! :)";
    }

    public String getDelete(Task task, int size) {
        return "Deleted. Happy now?\n" + task
                + "\nYou’re down to " + size + " task(s). Try not to mess those up too.";
    }

    public String showLine() {
        return "____________________________________________________________";
    }

    public String getGoodbye() {
        return "Goodbye. Go touch some grass. Or don’t. Not my problem anymore.";
    }

    public String getListHeader(TaskList tasks) {
        return "Brace yourself. Here’s your disaster lineup:\n" + tasks.listTasks();
    }

    public String getTaskAdded(Task t, int size) {
        return "Congrats. Another responsibility you’ll probably ignore:\n  "
                + t
                + "\nThat makes " + size + " "
                + (size == 1 ? "task" : "tasks")
                + ". Collecting them like regrets.";
    }

    public String getMarked(Task t) {
        return "Wait… you actually completed something? Miracles happen:\n" + t;
    }

    public String getUnmarked(Task t) {
        return "Commitment issues already?\n" + t;
    }

    public String getNoTasks() {
        return "Nothing scheduled. Either you’re ultra-organized… or wildly unproductive.";
    }

    public String getNoTasksFound() {
        return "No results. Maybe try remembering what you typed?";
    }

    public String getFindHeader(TaskList tasks) {
        return "Search complete. These are the only things matching your vague input:\n"
                + tasks.listTasks();
    }

    public String showError(String message) {
        return "!Error! \n" + message;
    }

    public String getContactAdded(Contact contact, int size) {
        return "Wow, a new contact. Someone actually tolerates you:\n"
                + contact
                + "\nYou now have " + size + " contact(s). Networking era?";
    }

    public String getDeleteContact(Contact selectedContact, int size) {
        return "Contact removed. Another bridge burned?\n"
                + selectedContact
                + "\nYou’re left with " + size + " contact(s). Choose wisely next time.";
    }

    public String getNoContactsFound() {
        return "No contacts found. Either spell properly or accept reality.";
    }

    public String getFindContactHeader(ContactList matchingContacts) {
        return "Search done. These are your so-called contacts:\n"
                + matchingContacts.listContacts();
    }

    public String getNoContacts() {
        return "Zero contacts. Not even a spam caller?";
    }

    public String getContactList(ContactList contacts) {
        return "Here’s the full list of people who haven’t blocked you yet:\n"
                + contacts.listContacts();
    }
}
