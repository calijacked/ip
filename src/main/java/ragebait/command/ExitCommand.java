package ragebait.command;

import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;
import ragebait.storage.ContactStorage;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents the command to exit the Ragebait application.
 *
 * When executed, this command saves the current state of tasks and contacts
 * to persistent storage and returns a farewell message for the user.
 *
 * If saving fails for some reason, a RagebaitException will be thrown
 * with a message that basically screams, "Fix your storage!"
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command.
     *
     * Saves all tasks and contacts to their respective storage components
     * and returns a UI-generated goodbye message.
     *
     * @param ui The UI used to generate a farewell message.
     * @param context The execution context containing tasks, contacts, and storage.
     * @return A farewell message to display to the user.
     * @throws RagebaitException If saving tasks or contacts to storage fails.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage tasksStorage = context.taskStorage;
        ContactList contacts = context.contacts;
        ContactStorage contactsStorage = context.contactStorage;

        tasksStorage.save(tasks);
        contactsStorage.save(contacts);

        return ui.getGoodbye();
    }
}
