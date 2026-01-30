package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage);
    public boolean isExit() { return false; }
}