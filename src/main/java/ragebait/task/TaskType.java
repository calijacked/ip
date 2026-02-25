package ragebait.task;

import ragebait.exception.RagebaitException;

/**
 * Enumerates the supported task types.
 *
 * Each TaskType is associated with a short symbol
 * used for display and file storage.
 */
public enum TaskType {

    /** Represents a to-do task. */
    TODO("T"),

    /** Represents a deadline task. */
    DEADLINE("D"),

    /** Represents an event task. */
    EVENT("E");

    /** Symbol used to represent the task type. */
    private final String symbol;

    /**
     * Constructs a TaskType with the specified symbol.
     *
     * @param symbol Symbol associated with this task type.
     */
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol associated with this task type.
     *
     * @return The task type symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Converts a symbol string into the corresponding TaskType.
     *
     * The input is trimmed and converted to uppercase before comparison.
     *
     * @param symbol Symbol to convert (for example, "T", "D", or "E").
     * @return Matching TaskType.
     * @throws RagebaitException If the symbol does not match any defined TaskType.
     */
    public static TaskType convertToTaskType(String symbol) throws RagebaitException {
        String normalised = symbol.trim().toUpperCase();
        for (TaskType type : TaskType.values()) {
            if (type.getSymbol().equals(normalised)) {
                return type;
            }
        }
        throw new RagebaitException(
                "Invalid task type symbol detected in storage: " + normalised
                        + ". Did you even read the format?"
        );
    }
}
