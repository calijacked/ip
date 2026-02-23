package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as completed in the TaskList.
 * The task is selected using its index.
 */
public class MarkCommand extends Command {

    /** Minimum valid task index. */
    private static final int START_RANGE = 0;

    /** Index of the task to be marked (0-based indexing). */
    private final int index;

    /**
     * Constructs a MarkCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the selected task as completed.
     *
     * @param tasks The TaskList containing tasks.
     * @param ui The UI used to display feedback messages.
     * @param storage The Storage responsible for persisting data.
     * @throws RagebaitException If the task index is out of range or
     *                           if the task is already marked.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException {
        Task selectedTask = tasks.get(index);
        int endRange = tasks.size();

        if (index < START_RANGE || index >= endRange) {
            throw new RagebaitException("I CAN'T MARK SOMETHING THAT DOES NOT EXIST");
        }

        if (selectedTask.isMarked()) {
            throw new RagebaitException("Task is already marked!");
        }

        selectedTask.markDone();
        ui.getMarked(tasks.get(index));
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
