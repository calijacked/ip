package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that lists all tasks in the TaskList.
 */
public class ListCommand extends Command {

    /** Represents an empty task list. */
    public static final int NO_TASKS = 0;

    /**
     * Executes the list command by displaying all tasks
     * currently stored in the TaskList.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The UI used to display the tasks.
     * @param storage The Storage responsible for persisting tasks.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.size() == NO_TASKS) {
            ui.getNoTasks();
        } else {
            ui.getListHeader(tasks);
        }
    }
}
