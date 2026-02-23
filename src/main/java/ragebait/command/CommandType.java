package ragebait.command;

import ragebait.exception.RagebaitException;

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

    public static CommandType convertToCommandType(String input) throws RagebaitException {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new RagebaitException("Unknown command!");
        }
    }
}
