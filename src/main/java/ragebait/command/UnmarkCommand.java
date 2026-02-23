package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as not done in the TaskList.
 * Used when the user wants to undo the completion of a task.
 */
public class UnmarkCommand extends Command {
    private static final int START_RANGE = 0;
    private final int index;

    /**
     * Constructs an UnmarkCommand for the task at the specified index.
     *
     * @param index Index of the task to unmark (0-based).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the task to unmark.
     *
     * @return Task index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Marks the task at the given index as not done and displays a message.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui UI used to display messages.
     * @param storage Storage for saving data (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int endRange = tasks.size();
        if (index < START_RANGE || index >= endRange) {
            ui.showMessage("I CAN'T UNMARK AN INVISIBLE TASK");
            return;
        }
        tasks.get(index).markUndone();
        ui.getUnmarked(tasks.get(index));
    }
}
