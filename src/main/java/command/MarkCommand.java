package main.java.command;

import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.UI;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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
}
