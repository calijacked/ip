package main.java.command;

import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage);
    public boolean isExit() { return false; }
}