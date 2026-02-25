package ragebait.parser;

import ragebait.command.*;
import ragebait.exception.RagebaitException;
import ragebait.task.TaskType;

/**
 * Responsible for translating raw user input into executable Command objects.
 *
 * The Parser extracts the command keyword and its arguments,
 * validates basic command structure, and delegates semantic validation
 * to the respective Command classes.
 *
 * This class only validates:
 * - Whether a command exists
 * - Whether required arguments are present
 *
 * It does not validate deeper business rules (e.g., date correctness).
 */
public class Parser {
    private static final String BYE = "bye";
    private static final int COMMAND_PARTS_LIMIT = 3;
    private static final int USER_INDEX_OFFSET = 1;
    private static final String EMPTY = "";

    /**
     * Parses the given user input string and returns the corresponding Command.
     *
     * @param fullCommand the full raw input entered by the user
     * @return the Command object representing the user's intention
     * @throws RagebaitException if the command is unknown or required arguments are missing
     */
    public static Command parse(String fullCommand) throws RagebaitException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new RagebaitException("Command cannot be empty!");
        }
        String trimmed = fullCommand.trim();
        if (trimmed.equalsIgnoreCase(BYE)) {
            return new ExitCommand();
        }
        String[] parts = trimmed.split(" ", COMMAND_PARTS_LIMIT);
        // Task or Contact
        Category category = Category.convertToCategory(parts[0].toLowerCase());
        if (parts.length < 2) {
            throw new RagebaitException("Please specify a command after " + category + " !");
        }
        CommandType commandType = CommandType.convertToCommandType(parts[1].toLowerCase());
        // Only list can have empty args
        String args = parts.length > 2 ? parts[2].trim() : EMPTY;

        switch (category) {
        case task:
            return parseTaskCommand(commandType, args);

        case contact:
            return parseContactCommand(commandType, args);

        default:
            throw new RagebaitException("Unknown category! Use task or contact!");
        }
    }
    private static TaskCommand parseTaskCommand(CommandType commandType, String args) throws RagebaitException {
        switch (commandType) {
        case list:
            return new ListTaskCommand();

        case mark:
            requireArgs(args, "Specify an existing task number! Check using command task list!");
            return new MarkTaskCommand(parseIndex(args));

        case unmark:
            requireArgs(args, "Specify an existing task number! Check using command task list!");
            return new UnmarkTaskCommand(parseIndex(args));

        case delete:
            requireArgs(args, "Specify an existing task number! Check using command task list!");
            return new DeleteTaskCommand(parseIndex(args));

        case todo:
            requireArgs(args, "The description of a todo cannot be empty.");
            return new AddTaskCommand(TaskType.TODO, args);

        case deadline:
            requireArgs(args, "The description and by date of a deadline cannot be empty.");
            return new AddTaskCommand(TaskType.DEADLINE, args);

        case event:
            requireArgs(args, "The description, from date, and to date of an event cannot be empty.");
            return new AddTaskCommand(TaskType.EVENT, args);

        case find:
            requireArgs(args, "Please provide a keyword to search for a task!");
            return new FindTaskCommand(args);

        default:
            throw new RagebaitException("Unknown command!");
        }
    }

    private static ContactCommand parseContactCommand(CommandType commandType, String args) throws RagebaitException {
        switch (commandType) {
        case list:
            return new ListContactCommand();
        case add:
            requireArgs(args, "The details of the contact cannot be empty!");
            return new AddContactCommand(args);
        case delete:
            requireArgs(args, "Specify an existing contact! Check using contact list!");
            return new DeleteContactCommand(parseIndex(args));
        case find:
            requireArgs(args, "Please provide a keyword to search for a contact!");
            return new FindContactCommand(args);
        default:
            throw new RagebaitException("Unknown command!");
        }
    }

    /**
     * Ensures that arguments are present.
     *
     * @param args the arguments string
     * @param errorMessage message to throw if arguments are missing
     * @throws RagebaitException if args are empty
     */
    private static void requireArgs(String args, String errorMessage) throws RagebaitException {
        if (args == null || args.trim().isEmpty()) {
            throw new RagebaitException(errorMessage);
        }
    }

    /**
     * Parses a user-provided task index into zero-based format.
     *
     * @param args the user input index
     * @return zero-based index
     * @throws RagebaitException if index is not a valid number
     */
    private static int parseIndex(String args) throws RagebaitException {
        try {
            return Integer.parseInt(args) - USER_INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new RagebaitException("Task number must be a valid integer!");
        }
    }
}
