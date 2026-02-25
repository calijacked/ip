package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.ui.UI;

/**
 * Represents an executable command in the application.
 *
 * All concrete command classes must implement this interface.
 * A command is responsible for performing its logic using the provided
 * Context and returning a user-facing message via the UI.
 *
 * If something goes wrong during execution, a RagebaitException
 * should be thrown with a message that clearly explains what the
 * user messed up this time.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param ui The UI component used to generate user-facing messages.
     * @param context The execution context containing shared application state.
     * @return A message to be displayed to the user.
     * @throws RagebaitException If execution fails due to invalid input or other user-induced chaos.
     */
    String execute(UI ui, Context context) throws RagebaitException;
}
