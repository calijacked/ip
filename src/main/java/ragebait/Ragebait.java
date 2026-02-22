package ragebait;

import ragebait.command.Command;
import ragebait.exception.RagebaitException;
import ragebait.parser.Parser;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Main class for the Ragebait application.
 * Initializes storage, UI, and task list, and runs the main program loop.
 */
public class Ragebait {
    static final String FILE_PATH = "data/ragebait.txt";
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructs a Ragebait application with a specified file path for storage.
     *
     */
    public Ragebait() {
        ui = new UI();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load().getAllTasks());
        } catch (RagebaitException e) {
            ui.showMessage("Error loading tasks. Starting with an empty list.");
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main program loop to read and execute user commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand); //Returns a Command object
                c.execute(tasks, ui, storage);
                isExit = c.isExit(); //ExitCommand will set this to true
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.close();
    }

    /**
     * Entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Ragebait().run();
    }
}
