package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as not completed in the TaskList.
 * This command is used to undo a previously marked task.
 */
public class UnmarkCommand extends Command {

    /** Minimum valid task index. */
    private static final int START_RANGE = 0;

    /** Index of the task to be unmarked (0-based indexing). */
    private final int index;

    /**
     * Constructs an UnmarkCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the task to be unmarked.
     *
     * @return The 0-based task index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Executes the unmark command by marking the selected task as not completed.
     *
     * @param tasks The TaskList containing tasks.
     * @param ui The UI used to display feedback messages.
     * @param storage The Storage used for persistence (not used in this command).
     * @throws RagebaitException If the task index is out of range or
     *                           if the task is already unmarked.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException {
        int endRange = tasks.size();

        if (index < START_RANGE || index >= endRange) {
            throw new RagebaitException("I CAN'T UNMARK AN INVISIBLE TASK");
        }
        Task selectedTask = tasks.get(index);

        if (!selectedTask.isMarked()) {
            throw new RagebaitException("Task is already unmarked!");
        }

        selectedTask.markUndone();
        storage.save(tasks);
        return ui.getUnmarked(selectedTask);
    }
}
