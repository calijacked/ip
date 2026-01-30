package main.java.command;

import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.UI;

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
