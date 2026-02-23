package ragebait.task;

import ragebait.exception.RagebaitException;

/**
 * Represents the type of task in the Ragebait application.
 * Each task type has a symbol used for display and file storage.
 */
public enum TaskType {
    /**
     * To-do task type
     */
    TODO("T"),

    /**
     * Deadline task type
     */
    DEADLINE("D"),

    /**
     * Event task type
     */
    EVENT("E");

    /**
     * Symbol representing the task type
     */
    private final String symbol;

    /**
     * Constructs a TaskType with the given symbol.
     *
     * @param symbol Symbol for the task type.
     */
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representing the task type.
     *
     * @return Symbol representing the task type.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Converts a string symbol into the corresponding TaskType.
     *
     * @param symbol Symbol string to convert.
     * @return Matching TaskType.
     * @throws RagebaitException If the symbol does not match any TaskType.
     */
    public static TaskType convertToTaskType(String symbol) throws RagebaitException {
        String normalised = symbol.trim().toUpperCase();
        for (TaskType type : TaskType.values()) {
            if (type.getSymbol().equals(normalised)) {
                return type;
            }
        }
        throw new RagebaitException("Corrupted storage file: invalid task type -> " + normalised);
    }
}