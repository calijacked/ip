package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Abstract class representing a user command in the Ragebait application.
 * All commands should extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command on the given tasks using UI and storage.
     *
     * @param tasks TaskList to operate on.
     * @param ui    UI for user interaction.
     * @param storage Storage for persisting task data.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException;

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return true if the command exits the program; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
