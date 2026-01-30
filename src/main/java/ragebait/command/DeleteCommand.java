package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to delete a task from the TaskList.
 * Removes the task at the specified index and displays messages via UI.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand for the task at the specified index.
     *
     * @param index Index of the task to delete (0-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the task to delete.
     *
     * @return Task index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Deletes the task at the given index from the TaskList.
     * Displays messages about the removed task and the updated task count.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui UI for displaying messages to the user.
     * @param storage Storage for saving tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.showMessage("I CAN'T DELETE! THIS DOES NOT EXIST!");
            return;
        }
        ui.showMessage("Are you done? or just lazy? Removed:");
        ui.showMessage(tasks.get(index).toString());
        tasks.remove(index);
        ui.showMessage("Now you have " + tasks.size() + " task(s) in the list.");
    }
}
