package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;
import ragebait.task.Task;

import java.util.ArrayList;

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
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found for keyword: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
    }

    public static class Commands {
    }
}
