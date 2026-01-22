public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by.split("by")[1].trim();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
