package ragebait.command;

import ragebait.exception.RagebaitException;

/**
 * Represents the high-level command category supported by the application.
 *
 * Categories are used to determine whether a command operates on tasks
 * or contacts.
 *
 * If the user provides a category that does not exist, a RagebaitException
 * will be thrown with an appropriately judgmental message.
 */
public enum Category {

    /**
     * Category for task-related commands.
     */
    task,

    /**
     * Category for contact-related commands.
     */
    contact;

    /**
     * Converts a raw input string into a Category.
     *
     * This method attempts to match the input exactly to one of the
     * defined enum constants.
     *
     * @param input The raw category string provided by the user.
     * @return The corresponding Category.
     * @throws RagebaitException If the input does not match any category.
     */
    public static Category convertToCategory(String input) throws RagebaitException {
        try {
            return Category.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new RagebaitException(
                    "Unknown category: \"" + input
                            + "\". We only have 'task' or 'contact'. It's not a buffet."
            );
        }
    }
}
