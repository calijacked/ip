package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that deletes a task from the TaskList.
 * The task is removed based on its index.
 */
public class DeleteCommand extends Command {

    /** The minimum valid task index. */
    private static final int START_RANGE = 0;

    /** The index of the task to be deleted (0-based). */
    private final int index;

    /**
     * Creates a DeleteCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the task to be deleted.
     *
     * @return The 0-based task index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Executes the delete command by removing the task at the given index
     * from the TaskList and displaying the updated task count.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The UI used to display messages.
     * @param storage The Storage responsible for persisting tasks.
     * @throws RagebaitException If the specified index is out of range.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException {
        Task selectedTask = tasks.get(index);

        int endRange = tasks.size();
        int newSize = endRange - 1;

        if (index < START_RANGE || index >= endRange) {
            throw new RagebaitException("I CAN'T DELETE! THIS DOES NOT EXIST!");
        }

        String result = ui.getDelete(selectedTask, newSize);
        storage.save(tasks);
        tasks.remove(index);
        return result;
    }
}
