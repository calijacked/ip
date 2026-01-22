import java.util.ArrayList;

public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] "  : "[ ] ") + description; // mark done task with X
    }

}
