package ragebait.task;

import ragebait.exception.RagebaitException;

/**
 * Represents the type of a task in the Ragebait application.
 * Each TaskType has a symbol used for display and storage.
 */
public enum TaskType {
    /** To-do task type */
    TODO("T"),

    /** Deadline task type */
    DEADLINE("D"),

    /** Event task type */
    EVENT("E");

    /** Symbol representing the task type in file storage or display */
    private final String symbol;

    /**
     * Constructs a TaskType with the specified symbol.
     *
     * @param symbol Symbol representing this task type.
     */
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol associated with this task type.
     *
     * @return Task type symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Converts a string symbol to its corresponding TaskType.
     * Used primarily for loading tasks from storage.
     *
     * @param symbol Symbol string to convert (e.g., "T", "D", "E").
     * @return Corresponding TaskType.
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
