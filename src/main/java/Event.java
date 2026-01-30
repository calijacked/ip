package main.java;

public class Event extends Task {
    protected  String to;
    protected  String from;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.to = to.split("to")[1].trim();
        this.from = from.split("from")[1].trim();
    }

    // Used for loading from file
    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT);
        this.from = from.trim();
        this.to = to.trim();
        if (isDone) {
            markDone();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to  + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to;
    }
}


