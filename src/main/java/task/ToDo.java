package main.java.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO);
        if (isDone) {
            markDone();
        }
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }
}
