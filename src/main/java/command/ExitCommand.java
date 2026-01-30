package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage("Finally! I was getting tired of you!");
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
