package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Represents a command that deletes a task from the TaskList.
 *
 * The task is removed based on its 0-based index.
 * If the index is invalid, a RagebaitException is thrown
 * with a rage-level message to remind the user that not everything
 * they imagine exists.
 */
public class DeleteTaskCommand extends TaskCommand {

    /** Minimum valid task index (0-based). */
    private static final int START_RANGE = 0;

    /** Index of the task to delete (0-based). */
    private final int index;

    /**
     * Constructs a DeleteTaskCommand for the task at the specified index.
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
     * Executes the delete task command.
     *
     * Removes the task at the specified index from the TaskList,
     * updates storage, and returns a UI message confirming deletion.
     *
     * @param ui The UI component used to generate feedback messages.
     * @param context The execution context containing tasks and storage.
     * @return A message confirming task deletion.
     * @throws RagebaitException If the index is out of bounds.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage taskStorage = context.taskStorage;
        int endRange = tasks.size();

        if (index < START_RANGE || index >= endRange) {
            throw new RagebaitException(
                    "I CAN'T DELETE! THIS DOES NOT EXIST! READ THE LIST NEXT TIME."
            );
        }

        Task selectedTask = tasks.get(index);
        String result = ui.getDelete(selectedTask, endRange - 1);

        tasks.remove(index);
        taskStorage.save(tasks);

        return result;
    }
}
