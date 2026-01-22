import java.util.ArrayList;

public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String toString() {
        return (isDone ? "[X] "  : "[ ] ") + description; // mark done task with X
    }

}
