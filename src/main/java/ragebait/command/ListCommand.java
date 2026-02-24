package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that lists all tasks in the TaskList.
 *
 * If the task list is empty, a message indicating no tasks is returned.
 * Otherwise, all tasks are formatted and displayed via the UI.
 */
public class ListCommand extends Command {

    /** Constant representing an empty task list */
    private static final int NO_TASKS = 0;

    /**
     * Executes the list command by displaying all tasks currently in the TaskList.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI used to display tasks or messages.
     * @param storage The Storage responsible for persisting tasks (not used here).
     * @return A formatted string representing the list of tasks, or a message if empty.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.size() == NO_TASKS) {
            return ui.getNoTasks();
        } else {
            return ui.getListHeader(tasks);
        }
    }
}
