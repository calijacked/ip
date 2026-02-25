package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as not completed (undo mark) in the TaskList.
 *
 * The task is identified by its 0-based index.
 * If the index is invalid or the task is already unmarked,
 * a rage-level RagebaitException is thrown to lecture the user.
 */
public class UnmarkTaskCommand extends TaskCommand {

    /** Minimum valid task index (0-based). */
    private static final int MIN_INDEX = 0;

    /** Index of the task to unmark (0-based indexing). */
    private final int index;

    /**
     * Constructs an UnmarkTaskCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to unmark.
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark task command.
     *
     * Marks the specified task as not completed and updates storage.
     *
     * @param ui The UI used to generate feedback messages.
     * @param context The execution context containing tasks and storage.
     * @return A message confirming the task has been unmarked.
     * @throws RagebaitException If the task index is out of range
     *                           or the task is already unmarked.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage taskStorage = context.taskStorage;

        int endIndex = tasks.size();

        if (index < MIN_INDEX || index >= endIndex) {
            throw new RagebaitException(
                    "Cannot unmark a task that does not exist! PAY ATTENTION NEXT TIME."
            );
        }

        Task selectedTask = tasks.get(index);

        if (!selectedTask.isMarked()) {
            throw new RagebaitException(
                    "Task is already unmarked! Are you even looking at the list?"
            );
        }

        selectedTask.markUndone();
        taskStorage.save(tasks);

        return ui.getUnmarked(selectedTask);
    }

    /**
     * Returns the index of the task to be unmarked.
     *
     * @return The 0-based task index.
     */
    public int getIndex() {
        return index;
    }
}
