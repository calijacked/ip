package ragebait.command;

import ragebait.storage.Storage;
import ragebait.ui.UI;
import ragebait.task.TaskList;

/**
 * Command to mark a task as not done in the TaskList.
 * Used when the user wants to undo the completion of a task.
 */
public class UnmarkCommand extends Command {
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
        if (index < 0 || index >= tasks.size()) {
            ui.showMessage("THIS DOES NOT EXIST!");
            return;
        }
        tasks.get(index).markUndone();
        ui.showMessage("WHY ARE YOU SKIVING.");
        ui.showMessage(tasks.get(index).toString());
    }
}
