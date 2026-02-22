package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Command to exit the Ragebait application.
 * Saves the current task list to storage and signals the program to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving all tasks to storage and displaying a farewell message.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui UI for displaying messages to the user.
     * @param storage Storage used to persist the tasks.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage("Finally! I was getting tired of you!");
        storage.save(tasks);
    }

    /**
     * Indicates that this command will terminate the application.
     *
     * @return true always, since executing this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
