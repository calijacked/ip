public class Event extends Task{
    protected  String to;
    protected  String from;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.to = to.split("to")[1].trim();
        this.from = from.split("from")[1].trim();
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to  + ")";

    }
}


