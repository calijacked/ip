package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(tasks.listTasks());
    }
}
