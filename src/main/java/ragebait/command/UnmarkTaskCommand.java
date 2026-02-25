package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command to mark a task as not completed (undo mark) in the TaskList.
 * The task is identified by its 0-based index in the TaskList.
 */
public class UnmarkTaskCommand extends TaskCommand {

    /** Minimum valid task index. */
    private static final int MIN_INDEX = 0;

    /** Index of the task to unmark (0-based indexing). */
    private final int index;

    /**
     * Constructs an UnmarkCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to unmark.
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the selected task as not completed.
     *
     * <p>Checks if the index is within range and whether the task is already unmarked.
     * Saves the updated TaskList using Storage and returns a UI message indicating
     * the action performed.</p>
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The UI used to display feedback messages.
     * @param storage The Storage used for persisting the task list.
     * @return A string message from UI confirming the task was unmarked.
     * @throws RagebaitException If the index is invalid or the task is already unmarked.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage taskStorage = context.taskStorage;

        int endIndex = tasks.size();

        // Validate index before accessing list
        if (index < MIN_INDEX || index >= endIndex) {
            throw new RagebaitException("Cannot unmark a task that does not exist.");
        }

        Task selectedTask = tasks.get(index);

        // Ensure task is currently marked
        if (!selectedTask.isMarked()) {
            throw new RagebaitException("Task is already unmarked.");
        }

        // Mark task as undone and persist changes
        selectedTask.markUndone();
        taskStorage.save(tasks);

        return ui.getUnmarked(selectedTask);
    }

    /**
     * Returns the index of the task to be unmarked.
     *
     * @return The 0-based index of the task.
     */
    public int getIndex() {
        return index;
    }
}
