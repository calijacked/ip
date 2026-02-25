package ragebait.command;

import ragebait.contacts.ContactList;
import ragebait.storage.ContactStorage;
import ragebait.storage.Storage;
import ragebait.task.TaskList;


public class Context {
    public final TaskList tasks;
    public final Storage taskStorage;
    public final ContactList contacts;
    public final ContactStorage contactStorage;

    public Context(TaskList tasks, Storage taskStorage, ContactList contacts, ContactStorage contactStorage) {
        this.tasks = tasks;
        this.taskStorage = taskStorage;
        this.contacts = contacts;
        this.contactStorage = contactStorage;
    }
}
