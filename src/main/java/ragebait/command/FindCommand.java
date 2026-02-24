package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that searches for tasks containing
 * a specified keyword in their description.
 */
public class FindCommand extends Command {

    /** The keyword used to search task descriptions. */
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by filtering tasks that contain
     * the specified keyword and displaying the matching results.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The UI used to display messages.
     * @param storage The Storage responsible for persisting tasks.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getAllTasks()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
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
