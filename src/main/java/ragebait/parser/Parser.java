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
 * Parses user input into the corresponding Command objects for the Ragebait application.
 * Converts a raw input string into a specific Command that can be executed.
 */
public class Parser {
    // --- Syntax Constants ---
    public static final int CUTOFF = 1;
    public static final int OFFSET = 1;
    public static final String BLANK = "";
    /**
     * Parses a full command string entered by the user and returns the corresponding Command object.
     *
     * @param fullCommand The raw input string entered by the user.
     * @return A Command object corresponding to the parsed input.
     * @throws IllegalArgumentException If the command is unknown or required arguments are missing.
     */
    public static Command parse(String fullCommand) throws RagebaitException {
        CommandType command;
        // Get the command
        String[] parts = fullCommand.split(" ", 2);
        try {
            command = CommandType.convertToCommandType(parts[0].toLowerCase());
        } catch (RagebaitException e) {
            throw e;
        }
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
            return new AddCommand(TaskType.TODO, args);
        case deadline:
            if (args.isEmpty()) {
                throw new RagebaitException("No description and by date provided!!");
            }
            return new AddCommand(TaskType.DEADLINE, args);
        case event:
            if (args.isEmpty()) {
                throw new RagebaitException("No description, by and from date provided!!");
            }
            return new AddCommand(TaskType.EVENT, args);
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
