package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as completed in the TaskList.
 * The task is selected using its index.
 * Assumes the index is 0-based and valid integer input.
 */
public class MarkTaskCommand extends TaskCommand {

    /** Minimum valid task index. */
    private static final int START_INDEX = 0;

    /** Index of the task to be marked (0-based). */
    private final int index;

    /**
     * Constructs a MarkCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to mark.
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the selected task as completed.
     *
     * @param tasks The TaskList containing tasks.
     * @param ui The UI used to display feedback messages.
     * @param storage The Storage responsible for persisting data.
     * @return A message from the UI confirming the task has been marked.
     * @throws RagebaitException If the task index is out of range
     *                           or the task is already marked.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage taskStorage = context.taskStorage;

        // Validate index before accessing the list
        if (index < START_INDEX || index >= tasks.size()) {
            throw new RagebaitException("Specified task number does not exist!");
        }

        Task selectedTask = tasks.get(index);

        if (selectedTask.isMarked()) {
            throw new RagebaitException("Task is already marked!");
        }

        selectedTask.markDone();
        taskStorage.save(tasks);
        return ui.getMarked(selectedTask);
    }

    /**
     * Returns the index of the task to be marked.
     *
     * @return The 0-based task index.
     */
    public int getIndex() {
        return index;
    }
}
