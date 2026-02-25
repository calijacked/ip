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
 * When executed, this command saves the current state of tasks
 * to persistent storage and returns a farewell message for the user.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command.
     *
     * This method saves the current tasks to the provided Storage instance
     * and returns a goodbye message obtained from the UI.
     *
     * @param tasks   The TaskList containing all tasks to be saved.
     * @param ui      The UI used to generate a farewell message.
     * @param storage The Storage responsible for persisting tasks.
     * @return A farewell message to display to the user.
     * @throws RagebaitException If saving tasks to storage fails.
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
