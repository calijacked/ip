package ragebait.command;

import ragebait.exception.RagebaitException;

/**
 * Represents the different types of commands supported
 * by the Ragebait application.
 *
 * These command types are not limited to tasks. Some commands
 * are shared across multiple categories such as task and contact.
 * For example, list, delete, and find may operate on either,
 * depending on the active Category.
 *
 * If the user types something that is not defined here,
 * a RagebaitException will be thrown to gently remind them
 * that imagination is not a supported feature.
 */
public enum CommandType {

    /**
     * Lists all entities in the current category.
     * This may refer to tasks or contacts.
     */
    list,

    /**
     * Exits the application.
     */
    bye,

    /**
     * Deletes an entity by its index in the current category.
     * Works for both tasks and contacts.
     */
    delete,

    /**
     * Marks a task as completed by its index.
     * Only applicable to tasks.
     */
    mark,

    /**
     * Unmarks a previously completed task by its index.
     * Only applicable to tasks.
     */
    unmark,

    /**
     * Creates a new To-Do task.
     */
    todo,

    /**
     * Creates a new Deadline task.
     */
    deadline,

    /**
     * Creates a new Event task.
     */
    event,

    /**
     * Finds entities containing a keyword.
     * Works for both tasks and contacts.
     */
    find,

    /**
     * Adds a new contact.
     */
    add;

    /**
     * Converts a string input into the corresponding CommandType.
     *
     * The input must exactly match one of the defined enum constants.
     *
     * @param input The command word entered by the user.
     * @return The matching CommandType enum.
     * @throws RagebaitException If the input does not match any supported command type.
     */
    public static CommandType convertToCommandType(String input) throws RagebaitException {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new RagebaitException(
                    "Unknown command: \"" + input
                            + "\". That’s not in the system. Try using an actual command."
            );
        }
    }
}
