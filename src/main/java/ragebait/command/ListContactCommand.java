package ragebait.command;

import ragebait.contacts.ContactList;
import ragebait.ui.UI;

/**
 * Command to list all contacts in the contact list.
 *
 * If the contact list is empty, a rage-level message is returned
 * to remind the user that the list is barren.
 * Otherwise, a formatted list of contacts is returned via the UI.
 */
public class ListContactCommand extends ContactCommand {

    /** Represents an empty contact list size. */
    private static final int NO_CONTACTS = 0;

    /**
     * Executes the list contact command.
     *
     * @param ui The UI used to generate feedback messages.
     * @param context The execution context containing contacts.
     * @return A message listing all contacts or indicating no contacts exist.
     */
    @Override
    public String execute(UI ui, Context context) {
        ContactList contacts = context.contacts;

        if (contacts.size() == NO_CONTACTS) {
            return ui.getNoContacts();
        } else {
            return ui.getContactList(contacts);
        }
    }
}
