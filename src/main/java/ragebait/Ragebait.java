package ragebait;

import ragebait.command.Command;
import ragebait.command.Context;
import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;
import ragebait.parser.Parser;
import ragebait.storage.ContactStorage;
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
    private static final String TASK_FILE_PATH = "data/ragebaitTasks.txt";
    private static final String CONTACTS_FILE_PATH = "data/ragebaitContacts.txt";

    /** Responsible for persisting task data to storage */
    private final Storage taskStorage;

    /** Collection of tasks currently managed by the application */
    private final TaskList tasks;

    /** Handles user interaction and message formatting */
    private final UI ui;

    private final ContactStorage contactStorage;
    private final ContactList contacts;
    private final Context context;

    /**
     * Constructs a Ragebait application instance.
     *
     * Initializes the UI, storage handler, and task list.
     * Attempts to load tasks from storage. If loading fails, starts with an empty task list
     * and prints an error.
     */
    public Ragebait() {
        ui = new UI();
        taskStorage = new Storage(TASK_FILE_PATH);
        tasks = initialiseTasks(TASK_FILE_PATH);
        contactStorage = new ContactStorage(CONTACTS_FILE_PATH);
        contacts = initialiseContacts(CONTACTS_FILE_PATH);
        context = new Context(tasks, taskStorage, contacts, contactStorage);
    }

    public TaskList initialiseTasks(String filePath) {
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(taskStorage.load().getAllTasks());
        } catch (RagebaitException e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            e.printStackTrace();
            loadedTasks = new TaskList();
        }
        return loadedTasks;
    }

    public ContactList initialiseContacts(String filePath) {
        ContactList loadedContacts;
        try {
            loadedContacts = new ContactList(contactStorage.load().getAllContacts());
        } catch (RagebaitException e) {
            ui.showError("Error loading tasks. Starting with an empty list.");
            e.printStackTrace();
            loadedContacts = new ContactList();
        }
        return loadedContacts;
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
            return command.execute(ui, context);
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
