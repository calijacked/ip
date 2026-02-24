package ragebait;

import ragebait.command.Command;
import ragebait.exception.RagebaitException;
import ragebait.parser.Parser;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.ui.UI;

/**
 * Main class for the Ragebait application.
 *
 * This class initializes the UI, Storage, and TaskList.
 * It handles user input and delegates command execution via the Parser.
 * It serves as the main controller for the application.
 */
public class Ragebait {

    /** Default file path used for storing task data */
    private static final String FILE_PATH = "data/ragebait.txt";

    /** Responsible for persisting task data to storage */
    private final Storage storage;

    /** Collection of tasks currently managed by the application */
    private final TaskList tasks;

    /** Handles user interaction and message formatting */
    private final UI ui;

    /**
     * Constructs a Ragebait application instance.
     *
     * Initializes the UI, storage handler, and task list.
     * Attempts to load tasks from storage. If loading fails, starts with an empty task list
     * and prints an error.
     */
    public Ragebait() {
        ui = new UI();
        storage = new Storage(FILE_PATH);

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load().getAllTasks());
        } catch (RagebaitException e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            e.printStackTrace();
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /**
     * Processes user input and returns the application's response.
     *
     * Parses the input into a Command using the Parser, executes the command,
     * and returns the output message. Any errors during parsing or execution
     * are caught and returned as an error message.
     *
     * @param input The raw user input command
     * @return The application's response message
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (RagebaitException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the welcome message displayed when the application starts.
     *
     * @return The welcome message string
     */
    public String getWelcomeMessage() {
        return ui.getWelcome();
    }
}
