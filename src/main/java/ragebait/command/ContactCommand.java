package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.ui.UI;

/**
 * Represents an abstract base class for all contact-related commands.
 *
 * Any command that operates on contacts should extend this class.
 * This ensures a clear separation between contact commands and
 * other command categories such as task commands.
 *
 * Subclasses must implement the execute method and define
 * the specific contact-related behavior. If execution fails,
 * they are expected to throw a RagebaitException with a message
 * that makes it painfully obvious what went wrong.
 */
public abstract class ContactCommand implements Command {

    /**
     * Executes a contact-related command.
     *
     * @param ui The UI component used to generate user-facing messages.
     * @param context The execution context containing shared application state, including contact data and storage.
     * @return A message to be displayed to the user.
     * @throws RagebaitException If execution fails due to invalid input or contact-related errors.
     */
    @Override
    public abstract String execute(UI ui, Context context) throws RagebaitException;
}
