package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.ui.UI;

/**
 * Represents an abstract base class for all task-related commands.
 *
 * Any command that operates on tasks should extend this class.
 * This provides a clear separation between task commands and
 * other command categories such as contact commands.
 *
 * Subclasses must implement the execute method and define
 * the specific task-related behavior. If execution fails,
 * they are expected to throw a RagebaitException with a
 * rage-level message to educate the user.
 */
public abstract class TaskCommand implements Command {

    /**
     * Executes a task-related command.
     *
     * @param ui The UI component used to generate user-facing messages.
     * @param context The execution context containing shared application state, including tasks and storage.
     * @return A message to be displayed to the user.
     * @throws RagebaitException If execution fails due to invalid input or task-related errors.
     */
    @Override
    public abstract String execute(UI ui, Context context) throws RagebaitException;
}
