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
 * Entry point and main controller for the Ragebait application.
 *
 * This class:
 * - Initializes UI, storage handlers, and data lists
 * - Loads persisted tasks and contacts
 * - Processes user input through the Parser
 * - Delegates command execution
 */
public class Ragebait {

    /** Default file path used for storing task data */
    private static final String TASK_FILE_PATH = "data/ragebaitTasks.txt";

    /** Default file path used for storing contact data */
    private static final String CONTACTS_FILE_PATH = "data/ragebaitContacts.txt";

    /** Responsible for persisting task data */
    private final Storage taskStorage;

    /** Collection of tasks managed by the application */
    private final TaskList tasks;

    /** Handles user interaction and message formatting */
    private final UI ui;

    /** Responsible for persisting contact data */
    private final ContactStorage contactStorage;

    /** Collection of contacts managed by the application */
    private final ContactList contacts;

    /** Shared execution context passed to commands */
    private final Context context;

    /**
     * Constructs a Ragebait application instance.
     *
     * Initializes UI, storage handlers, and loads saved data.
     * If loading fails, empty lists are created instead.
     */
    public Ragebait() {
        ui = new UI();
        taskStorage = new Storage(TASK_FILE_PATH);
        tasks = initialiseTasks(TASK_FILE_PATH);
        contactStorage = new ContactStorage(CONTACTS_FILE_PATH);
        contacts = initialiseContacts(CONTACTS_FILE_PATH);
        context = new Context(tasks, taskStorage, contacts, contactStorage);
    }

    /**
     * Loads tasks from storage.
     *
     * If loading fails, an empty TaskList is returned.
     *
     * @param filePath Path to the task storage file.
     * @return Initialized TaskList.
     */
    public TaskList initialiseTasks(String filePath) {
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(taskStorage.load().getAllTasks());
        } catch (RagebaitException e) {
            ui.showError("Task storage failed to load. Guess we’re starting from scratch.");
            e.printStackTrace();
            loadedTasks = new TaskList();
        }
        return loadedTasks;
    }

    /**
     * Loads contacts from storage.
     *
     * If loading fails, an empty ContactList is returned.
     *
     * @param filePath Path to the contact storage file.
     * @return Initialized ContactList.
     */
    public ContactList initialiseContacts(String filePath) {
        ContactList loadedContacts;
        try {
            loadedContacts = new ContactList(contactStorage.load().getAllContacts());
        } catch (RagebaitException e) {
            ui.showError("Contact storage failed to load. Social reset activated.");
            e.printStackTrace();
            loadedContacts = new ContactList();
        }
        return loadedContacts;
    }

    /**
     * Processes user input and returns the resulting response.
     *
     * The input is parsed into a Command and executed.
     * Any parsing or execution errors are returned as formatted messages.
     *
     * @param input Raw user input.
     * @return Response message.
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
     * Returns the application's welcome message.
     *
     * @return Welcome message.
     */
    public String getWelcomeMessage() {
        return ui.getWelcome();
    }
}
