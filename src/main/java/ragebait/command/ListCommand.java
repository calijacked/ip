package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to list all tasks currently stored in the TaskList.
 */
public class ListCommand extends Command {
    public static final int NO_TASKS = 0;
    /**
     * Displays all tasks in the TaskList using the UI.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui UI use d to display the list.
     * @param storage Storage (not used for this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.size() == NO_TASKS) {
            ui.getNoTasks();
        }
        else {
            ui.getListHeader(tasks);
        }
    }
}
