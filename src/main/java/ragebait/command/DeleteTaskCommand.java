package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that deletes a task from the TaskList.
 * The task is removed based on its 0-based index.
 */
public class DeleteTaskCommand extends TaskCommand {

    /** Minimum valid task index (0-based). */
    private static final int START_RANGE = 0;

    /** Index of the task to delete (0-based). */
    private final int index;

    /**
     * Constructs a DeleteCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to delete.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the task to be deleted.
     *
     * @return 0-based task index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Executes the delete command by removing the task at the given index
     * from the TaskList and returning a confirmation message.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The UI used to display feedback messages.
     * @param storage The Storage responsible for persisting tasks.
     * @return Message confirming the task deletion and showing the new task count.
     * @throws RagebaitException If the index is out of valid range.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage taskStorage = context.taskStorage;
        int endRange = tasks.size();

        if (index < START_RANGE || index >= endRange) {
            throw new RagebaitException("I CAN'T DELETE! THIS DOES NOT EXIST!");
        }

        Task selectedTask = tasks.get(index);
        String result = ui.getDelete(selectedTask, endRange - 1);

        tasks.remove(index);
        taskStorage.save(tasks);

        return result;
    }
}
