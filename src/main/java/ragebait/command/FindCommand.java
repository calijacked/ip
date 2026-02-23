package ragebait.command;

import java.util.ArrayList;

import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;


/**
 * Finds and lists all tasks whose description contains the given keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword Keyword to search in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getAllTasks()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.getNoTasksFound();
        } else {
            ui.getFindHeader(matchingTasks);
        }
    }
}


