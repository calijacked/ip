package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to mark a task as completed in the TaskList.
 */
public class MarkCommand extends Command {
    private static final int START_RANGE = 0;

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
        int endRange = tasks.size();
        if (index < START_RANGE || index >= endRange) {
            ui.showMessage("I CAN'T MARK SOMETHING THAT DOES NOT EXIST");
            return;
        }
        tasks.get(index).markDone();
        ui.getMarked(tasks.get(index));
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
