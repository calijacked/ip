package ragebait.task;

/**
 * Represents the type of a task in the Ragebait application.
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
     * @return Symbol of the task type.
     */
    public String getSymbol() {
        return symbol;
    }

    public static TaskType convertToTaskType(String symbol) {
        for (TaskType type : values()) {
            if (type.symbol.equals(symbol)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid task type");
    }
}
