package ragebait.command;

import ragebait.contacts.ContactList;
import ragebait.storage.ContactStorage;
import ragebait.storage.Storage;
import ragebait.task.TaskList;

/**
 * Represents the execution context shared across commands.
 *
 * This class acts as a central holder for application state,
 * bundling together task-related data and contact-related data.
 *
 * Instead of passing four separate parameters everywhere
 * and making constructors look like a mess, we wrap them
 * nicely into one Context object.
 *
 * All fields are immutable references to ensure that commands
 * cannot casually swap out core components and break everything.
 */
public class Context {

    /**
     * The list of tasks managed by the application.
     */
    public final TaskList tasks;

    /**
     * The storage component responsible for persisting tasks.
     */
    public final Storage taskStorage;

    /**
     * The list of contacts managed by the application.
     */
    public final ContactList contacts;

    /**
     * The storage component responsible for persisting contacts.
     */
    public final ContactStorage contactStorage;

    /**
     * Constructs a Context containing all shared application state.
     *
     * @param tasks The TaskList instance.
     * @param taskStorage The Storage instance for tasks.
     * @param contacts The ContactList instance.
     * @param contactStorage The ContactStorage instance.
     */
    public Context(TaskList tasks, Storage taskStorage,
                   ContactList contacts, ContactStorage contactStorage) {
        this.tasks = tasks;
        this.taskStorage = taskStorage;
        this.contacts = contacts;
        this.contactStorage = contactStorage;
    }
}
