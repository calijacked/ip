package ragebait.command;

import ragebait.exception.RagebaitException;

public enum Category {
    TASK,
    CONTACT;

    public static Category convertToCategory(String input) throws RagebaitException {
        try {
            return Category.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new RagebaitException("Unknown category: " + input);
        }
    }
}

