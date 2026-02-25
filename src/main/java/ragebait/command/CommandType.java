package ragebait.command;

import ragebait.exception.RagebaitException;

/**
 * Represents the different types of commands supported
 * by the Ragebait application.
 * Each command type corresponds to a specific action the user can perform.
 */
public enum CommandType {

    /** Lists all tasks in the task list. */
    list,

    /** Exits the application. */
    bye,

    /** Deletes a task by its index. */
    delete,

    /** Marks a task as completed by its index. */
    mark,

    /** Unmarks a previously completed task by its index. */
    unmark,

    /** Creates a new To-Do task. */
    todo,

    /** Creates a new Deadline task. */
    deadline,

    /** Creates a new Event task. */
    event,

    /** Finds tasks containing a keyword. */
    find,

    add;

    /**
     * Converts a string input into the corresponding CommandType.
     *
     * @param input The command word entered by the user.
     * @return The matching CommandType enum.
     * @throws RagebaitException If the input does not match any supported command type.
     */
    public static CommandType convertToCommandType(String input) throws RagebaitException {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new RagebaitException("Unknown command!");
        }
    }
}
