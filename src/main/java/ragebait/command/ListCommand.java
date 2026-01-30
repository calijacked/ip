package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to list all tasks currently stored in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the TaskList using the UI.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui UI used to display the list.
     * @param storage Storage (not used for this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(tasks.listTasks());
    }
}
