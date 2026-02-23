package ragebait.command;

public enum CommandType {
    list,
    bye,
    delete,
    mark,
    unmark,
    todo,
    deadline,
    event,
    find;

    private final String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    public String getCommand() {
        return commandString;
    }

    public static CommandType convertToCommandType(String input) {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown command!");
        }
    }
}
