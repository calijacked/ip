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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input); //Returns a Command object
            c.execute(tasks, ui, storage);
        } catch (RagebaitException e) {
            ui.showError(e.getMessage());
        }
        return "1";
    }

    public String getWelcomeMessage() {
        return ui.getWelcome();
    }
}

