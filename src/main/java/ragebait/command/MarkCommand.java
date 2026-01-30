package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as completed in the TaskList.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand for the task at the specified index.
     *
     * @param index Index of the task to mark (0-based).
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as done and displays a message.
     *
     * @param tasks TaskList containing tasks.
     * @param ui UI for displaying messages.
     * @param storage Storage (not used for this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.showMessage("THIS DOES NOT EXIST BRO");
            return;
        }
        tasks.get(index).markDone();
        ui.showMessage("NICE LA! You managed to accomplish something in your life for once.");
        ui.showMessage(tasks.get(index).toString());
    }

    /**
     * Returns the index of the task to mark.
     *
     * @return Task index.
     */
    public int getIndex() {
        return index;
    }
}
