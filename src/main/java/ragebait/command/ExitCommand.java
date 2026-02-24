package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that exits the Ragebait application.
 * Saves the current tasks before terminating the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving the current TaskList
     * and displaying a farewell message.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The UI used to display messages.
     * @param storage The Storage responsible for persisting tasks.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException {
        storage.save(tasks);
        return ui.getGoodbye();
    }
}
