package ragebait.command;

import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to list all tasks in the TaskList.
 *
 * If the task list is empty, a rage-level message is returned
 * to remind the user that they have no tasks and should get a life.
 * Otherwise, a formatted list of tasks is displayed via the UI.
 */
public class ListTaskCommand extends TaskCommand {

    /** Constant representing an empty task list. */
    private static final int NO_TASKS = 0;

    /**
     * Executes the list task command.
     *
     * @param ui The UI used to generate feedback messages.
     * @param context The execution context containing tasks.
     * @return A message listing all tasks or indicating no tasks exist.
     */
    @Override
    public String execute(UI ui, Context context) {
        TaskList tasks = context.tasks;

        if (tasks.size() == NO_TASKS) {
            return ui.getNoTasks();
        } else {
            return ui.getListHeader(tasks);
        }
    }
}
