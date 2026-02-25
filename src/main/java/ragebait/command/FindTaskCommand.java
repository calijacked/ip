package ragebait.command;

import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to search for tasks whose descriptions contain a given keyword.
 *
 * Performs a case-insensitive search across all tasks and
 * collects matching tasks into a temporary list.
 *
 * If no tasks match, the UI will return a rage-infused
 * "no tasks found" message to remind the user that reading matters.
 */
public class FindTaskCommand extends TaskCommand {

    /** Keyword used to search task descriptions. */
    private final String keyword;

    /**
     * Constructs a FindTaskCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find task command.
     *
     * @param ui The UI used to generate feedback messages.
     * @param context The execution context containing tasks.
     * @return A message listing matching tasks or no tasks found message if there are no matches.
     */
    @Override
    public String execute(UI ui, Context context) {
        TaskList tasks = context.tasks;
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return ui.getNoTasksFound();
        } else {
            return ui.getFindHeader(matchingTasks);
        }
    }
}
