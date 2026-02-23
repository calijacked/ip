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
     * Constructs a Ragebait application with default storage file path.
     * Initializes UI, Storage, and TaskList.
     * If task loading fails, starts with an empty task list.
     */
    public Ragebait() {
        ui = new UI();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load().getAllTasks());
        } catch (RagebaitException e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main program loop to read and execute user commands until exit.
     * Continuously reads user input, parses commands, executes them, and handles exceptions.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(input); //Returns a Command object
                c.execute(tasks, ui, storage);
                isExit = c.isExit(); //ExitCommand will set this to true
            } catch (RagebaitException e) {
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