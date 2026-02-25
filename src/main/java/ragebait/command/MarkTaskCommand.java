package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as completed in the TaskList.
 *
 * The task is selected using its 0-based index.
 * If the index is invalid or the task is already marked,
 * a rage-level RagebaitException is thrown to lecture the user.
 */
public class MarkTaskCommand extends TaskCommand {

    /** Minimum valid task index (0-based). */
    private static final int START_INDEX = 0;

    /** Index of the task to be marked (0-based). */
    private final int index;

    /**
     * Constructs a MarkTaskCommand for the task at the specified index.
     *
     * @param index The 0-based index of the task to mark.
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark task command.
     *
     * Marks the specified task as completed and updates storage.
     *
     * @param ui The UI used to generate feedback messages.
     * @param context The execution context containing tasks and storage.
     * @return A message confirming the task has been marked as done.
     * @throws RagebaitException If the task index is out of range
     *                           or the task is already marked.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage taskStorage = context.taskStorage;

        if (index < START_INDEX || index >= tasks.size()) {
            throw new RagebaitException(
                    "Specified task number does not exist! READ THE LIST NEXT TIME."
            );
        }

        Task selectedTask = tasks.get(index);

        if (selectedTask.isMarked()) {
            throw new RagebaitException(
                    "Task is already marked! Are you even paying attention?"
            );
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
