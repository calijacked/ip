package ragebait.parser;

import ragebait.command.*;

/**
 * Parses user input into the corresponding Command objects for the Ragebait application.
 * Converts a raw input string into a specific Command that can be executed.
 */
public class Parser {
    // --- Syntax Constants ---
    public static final String TAG_BY = " /by ";
    public static final String TAG_FROM = " /from ";
    public static final String TAG_TO = " /to ";
    public static final String EVENT_SPLIT_REGEX = " /from | /to ";
    /**
     * Parses   a full command string entered by the user and returns the corresponding Command object.
     *
     * @param fullCommand The raw input string entered by the user.
     * @return A Command object corresponding to the parsed input.
     * @throws IllegalArgumentException If the command is unknown or required arguments are missing.
     */
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "mark":
                if (args.isEmpty()) throw new IllegalArgumentException("Specify a ragebait.task number!");
                return new MarkCommand(Integer.parseInt(args) - 1);
            case "unmark":
                if (args.isEmpty()) throw new IllegalArgumentException("Specify a ragebait.task number!");
                return new UnmarkCommand(Integer.parseInt(args) - 1);
            case "delete":
                if (args.isEmpty()) throw new IllegalArgumentException("Specify a ragebait.task number!");
                return new DeleteCommand(Integer.parseInt(args) - 1);
            case "todo":
            case "deadline":
            case "event":
                if (args.isEmpty()) throw new IllegalArgumentException("No description provided!");
                return new AddCommand(command, args);
            case "find":
                if (args.isEmpty()) {
                    throw new IllegalArgumentException("Please provide a keyword to search!");
                }
                return new FindCommand(args);
            default:
                throw new IllegalArgumentException("Unknown command!");
        }
    }
}
