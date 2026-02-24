package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents the command to exit the Ragebait application.
 *
 * When executed, this command saves the current state of tasks
 * to persistent storage and returns a farewell message for the user.
 */
public class ExitCommand extends Command {

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
    public String execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException {
        storage.save(tasks);
        return ui.getGoodbye();
    }
}
