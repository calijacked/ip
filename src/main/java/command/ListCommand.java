package main.java.command;

import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.UI;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(tasks.listTasks());
    }
}
