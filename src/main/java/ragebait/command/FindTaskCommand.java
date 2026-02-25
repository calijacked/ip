package ragebait.command;

import ragebait.storage.ContactStorage;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that searches for tasks containing
 * a specified keyword in their description.
 *
 * The search is case-insensitive and returns all matching tasks.
 */
public class FindTaskCommand extends TaskCommand {

    /** The keyword used to filter task descriptions */
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by filtering tasks that contain
     * the specified keyword.
     *
     * Returns a formatted message from the UI depending on whether
     * matching tasks were found.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI used to display the search results.
     * @param storage The Storage (not used in this command).
     * @return A string message representing search results or no matches.
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
