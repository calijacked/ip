package ragebait.parser;

import ragebait.command.AddContactCommand;
import ragebait.command.AddTaskCommand;
import ragebait.command.Category;
import ragebait.command.Command;
import ragebait.command.CommandType;
import ragebait.command.ContactCommand;
import ragebait.command.DeleteContactCommand;
import ragebait.command.DeleteTaskCommand;
import ragebait.command.ExitCommand;
import ragebait.command.FindContactCommand;
import ragebait.command.FindTaskCommand;
import ragebait.command.ListContactCommand;
import ragebait.command.ListTaskCommand;
import ragebait.command.MarkTaskCommand;
import ragebait.command.TaskCommand;
import ragebait.command.UnmarkTaskCommand;
import ragebait.exception.RagebaitException;
import ragebait.task.TaskType;

/**
 * Responsible for converting raw user input into executable Command objects.
 *
 * The Parser handles:
 * - Extracting the command category (task/contact) and command type.
 * - Validating that required arguments are present.
 *
 * This class does not validate deeper business rules like
 * proper date formats or email correctness; that is delegated
 * to the respective Command classes.
 *
 * If the user messes up, the parser throws rage-level RagebaitExceptions
 * with clear instructions for fixing the disaster.
 */
public class Parser {

    private static final String BYE = "bye";
    private static final int COMMAND_PARTS_LIMIT = 3;
    private static final int USER_INDEX_OFFSET = 1;
    private static final String EMPTY = "";

    /**
     * Parses a raw user input string into the corresponding Command object.
     *
     * @param fullCommand The complete user input.
     * @return The Command object representing the user's intention.
     * @throws RagebaitException If the input is empty, unknown, or missing required arguments.
     */
    public static Command parse(String fullCommand) throws RagebaitException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new RagebaitException("Command cannot be empty! Type something or GET LOST.");
        }

        String trimmed = fullCommand.trim();
        String[] parts = trimmed.split(" ", COMMAND_PARTS_LIMIT);

        if (parts[0].equalsIgnoreCase(BYE)) {
            return new ExitCommand();
        }

        Category category = Category.convertToCategory(parts[0].toLowerCase());

        if (parts.length < 2) {
            throw new RagebaitException(
                    "You forgot the actual command after " + category + "! Try paying attention!"
            );
        }

        CommandType commandType = CommandType.convertToCommandType(parts[1].toLowerCase());
        String args = parts.length > 2 ? parts[2].trim() : EMPTY;

        switch (category) {
        case task:
            return parseTaskCommand(commandType, args);
        case contact:
            return parseContactCommand(commandType, args);
        default:
            throw new RagebaitException("Unknown category! Use task or contact, genius.");
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
            requireArgs(args, "The description of a todo cannot be empty. Do you even type?");
            return new AddTaskCommand(TaskType.TODO, args);
        case deadline:
            requireArgs(args, "The description and by date of a deadline cannot be empty. Focus!");
            return new AddTaskCommand(TaskType.DEADLINE, args);
        case event:
            requireArgs(args, "The description, from date, and to date of an event cannot be empty. Pay attention!");
            return new AddTaskCommand(TaskType.EVENT, args);
        case find:
            requireArgs(args, "Provide a keyword to search for a task! Seriously?");
            return new FindTaskCommand(args);
        default:
            throw new RagebaitException("Unknown command! Are you making this up?");
        }
    }

    private static ContactCommand parseContactCommand(CommandType commandType, String args) throws RagebaitException {
        switch (commandType) {
        case list:
            return new ListContactCommand();
        case add:
            requireArgs(args, "The details of the contact cannot be empty! Enter something, will ya?");
            return new AddContactCommand(args);
        case delete:
            requireArgs(args, "Specify an existing contact! Check using contact list, duh!");
            return new DeleteContactCommand(parseIndex(args));
        case find:
            requireArgs(args, "Provide a keyword to search for a contact! Did you forget?");
            return new FindContactCommand(args);
        default:
            throw new RagebaitException("Unknown command! Stop breaking my parser!");
        }
    }

    /**
     * Ensures that the provided arguments are not empty.
     *
     * @param args the arguments string
     * @param errorMessage the rage-level error message to throw if args are empty
     * @throws RagebaitException if args are null or empty
     */
    private static void requireArgs(String args, String errorMessage) throws RagebaitException {
        if (args == null || args.trim().isEmpty()) {
            throw new RagebaitException(errorMessage);
        }
    }

    /**
     * Converts a user-provided index to a 0-based integer.
     *
     * @param args the user input index string
     * @return zero-based index
     * @throws RagebaitException if the input is not a valid integer
     */
    private static int parseIndex(String args) throws RagebaitException {
        try {
            return Integer.parseInt(args) - USER_INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new RagebaitException("Task number must be a valid integer! Stop typing garbage!");
        }
    }
}
