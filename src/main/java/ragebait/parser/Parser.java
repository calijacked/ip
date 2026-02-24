package ragebait.parser;

import ragebait.command.AddCommand;
import ragebait.command.Command;
import ragebait.command.CommandType;
import ragebait.command.DeleteCommand;
import ragebait.command.ExitCommand;
import ragebait.command.FindCommand;
import ragebait.command.ListCommand;
import ragebait.command.MarkCommand;
import ragebait.command.UnmarkCommand;
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

    private static final int COMMAND_PARTS_LIMIT = 2;
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

        String[] parts = fullCommand.trim().split(" ", COMMAND_PARTS_LIMIT);
        CommandType commandType = CommandType.convertToCommandType(parts[0].toLowerCase());

        String args = parts.length > 1 ? parts[1].trim() : EMPTY;

        switch (commandType) {
        case list:
            return new ListCommand();

        case bye:
            return new ExitCommand();

        case mark:
            requireArgs(args, "Specify an existing task number! Check using command list!");
            return new MarkCommand(parseIndex(args));

        case unmark:
            requireArgs(args, "Specify an existing task number! Check using command list!");
            return new UnmarkCommand(parseIndex(args));

        case delete:
            requireArgs(args, "Specify an existing task number! Check using command list!");
            return new DeleteCommand(parseIndex(args));

        case todo:
            requireArgs(args, "The description of a todo cannot be empty.");
            return new AddCommand(TaskType.TODO, args);

        case deadline:
            requireArgs(args, "The description and by date of a deadline cannot be empty.");
            return new AddCommand(TaskType.DEADLINE, args);

        case event:
            requireArgs(args, "The description, from date, and to date of an event cannot be empty.");
            return new AddCommand(TaskType.EVENT, args);

        case find:
            requireArgs(args, "Please provide a keyword to search!");
            return new FindCommand(args);

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
