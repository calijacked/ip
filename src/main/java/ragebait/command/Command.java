package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents an abstract user command in the Ragebait application.
 * All concrete command classes should extend this class and implement
 * the {@link #execute(TaskList, UI, Storage)} method.
 */
public abstract class Command {

    /**
     * Executes the command using the provided TaskList, UI, and Storage.
     * Each concrete command must implement its own execution logic.
     *
     * @param tasks   The TaskList containing current tasks.
     * @param ui      The UI object for displaying messages to the user.
     * @param storage The Storage used to persist task data.
     * @return A message describing the result of executing the command.
     * @throws RagebaitException If an error occurs during command execution,
     *                           such as invalid task index or missing arguments.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException;
}
