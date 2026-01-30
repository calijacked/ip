package main.java;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by.split("by")[1].trim();
    }

    // Used for loading from file
    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE);
        this.by = by.trim();
        if (isDone) {
            markDone();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by;
    }
}
