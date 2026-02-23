package ragebait.parser;

import ragebait.command.*;
import ragebait.exception.RagebaitException;
import ragebait.task.TaskType;

/**
 * Parses user input into the corresponding Command objects for the Ragebait application.
 * Converts a raw input string into a specific Command that can be executed.
 */
public class Parser {
    // --- Syntax Constants ---
    public static final int CUTOFF = 1;
    public static final int OFFSET = 1;
    public static final String BLANK = "";
    public static final String TAG_BY = " /by ";
    public static final String TAG_FROM = " /from ";
    public static final String TAG_TO = " /to ";
    public static final String EVENT_SPLIT_REGEX = " /from | /to ";
    /**
     * Parses a full command string entered by the user and returns the corresponding Command object.
     *
     * @param fullCommand The raw input string entered by the user.
     * @return A Command object corresponding to the parsed input.
     * @throws IllegalArgumentException If the command is unknown or required arguments are missing.
     */
    public static Command parse(String fullCommand) throws RagebaitException {
        // Get the command
        String[] parts = fullCommand.split(" ", 2);
        CommandType command = CommandType.convertToCommandType(parts[0].toLowerCase());
        // Separates single commands from other commands (mark, unmark, todo, event, deadline)
        String args = parts.length > CUTOFF ? parts[1].trim() : BLANK;

        switch (command) {
        case list:
            return new ListCommand();
        case bye:
            return new ExitCommand();
        case mark:
            if (args.isEmpty()) {
                throw new RagebaitException("Specify an existing task number! Check using command list!");
            }
            return new MarkCommand(Integer.parseInt(args) - OFFSET);
        case unmark:
            if (args.isEmpty()) {
                throw new RagebaitException("Specify an existing task number! Check using command list!");
            }
            return new UnmarkCommand(Integer.parseInt(args) - OFFSET);
        case delete:
            if (args.isEmpty()) {
                throw new RagebaitException("Specify an existing task number! Check using command list!");
            }
            return new DeleteCommand(Integer.parseInt(args) - OFFSET);
        case todo:
            if (args.isEmpty()) {
                throw new RagebaitException("No description provided!");
            }
            return new AddCommand(TaskType.convertToTaskType(command.getCommand()), args);
        case deadline:
            if (args.isEmpty()) {
                throw new RagebaitException("No description and by date provided!!");
            }
            return new AddCommand(TaskType.convertToTaskType(command.getCommand()), args);
        case event:
            if (args.isEmpty()) {
                throw new RagebaitException("No description, by and from date provided!!");
            }
            return new AddCommand(TaskType.convertToTaskType(command.getCommand()), args);
        case find:
            if (args.isEmpty()) {
                throw new RagebaitException("Please provide a keyword to search!");
            }
            return new FindCommand(args);
        default:
            throw new RagebaitException("Unknown command!");
        }
    }
}
