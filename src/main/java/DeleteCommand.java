package main.java;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.showMessage("I CAN'T DELETE! THIS DOES NOT EXIST!");
            return;
        }
        ui.showMessage("Are you done? or just lazy? Removed:");
        ui.showMessage(tasks.get(index).toString());
        tasks.remove(index);
        ui.showMessage("Now you have " + tasks.size() + " task(s) in the list.");
    }
}
