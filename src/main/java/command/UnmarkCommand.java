package command;

import storage.Storage;
import ui.UI;
import task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.showMessage("THIS DOES NOT EXIST!");
            return;
        }
        tasks.get(index).markUndone();
        ui.showMessage("WHY ARE YOU SKIVING.");
        ui.showMessage(tasks.get(index).toString());
    }
}
