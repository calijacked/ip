package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents an abstract user command in the Ragebait application.
 * All concrete command classes should extend this class and implement
 * the execute method.
 */
public abstract class Command {

    /**
     * Executes the command using the given TaskList, UI, and Storage.
     *
     * @param tasks The TaskList to operate on.
     * @param ui The UI used for user interaction.
     * @param storage The Storage responsible for persisting task data.
     * @throws RagebaitException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException;

}
