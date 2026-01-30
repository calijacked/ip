package ragebait.parser;

import ragebait.command.*;

public class Parser {

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
