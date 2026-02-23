package ragebait.command;

import ragebait.exception.RagebaitException;

/**
 * Represents the different types of commands supported
 * by the Ragebait application.
 */
public enum CommandType {

    /** Lists all tasks. */
    list,

    /** Exits the application. */
    bye,

    /** Deletes a task. */
    delete,

    /** Marks a task as completed. */
    mark,

    /** Unmarks a completed task. */
    unmark,

    /** Creates a todo task. */
    todo,

    /** Creates a deadline task. */
    deadline,

    /** Creates an event task. */
    event,

    /** Finds tasks containing a keyword. */
    find;

    /**
     * Converts a string input into the corresponding CommandType.
     *
     * @param input The command word entered by the user.
     * @return The matching CommandType.
     * @throws RagebaitException If the input does not match any command type.
     */
    public static CommandType convertToCommandType(String input) throws RagebaitException {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new RagebaitException("Unknown command!");
        }
    }
}
