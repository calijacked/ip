package ragebait.command;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;
import ragebait.storage.ContactStorage;
import ragebait.ui.UI;

/**
 * Command to delete a contact from the contact list.
 * The contact is removed based on its 0-based index.
 */
public class DeleteContactCommand extends ContactCommand {

    private final int index;

    /**
     * Constructs a DeleteContactCommand for the contact at the specified index.
     *
     * @param index 0-based index of the contact to delete.
     */
    public DeleteContactCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        ContactList contacts = context.contacts;
        ContactStorage contactStorage = context.contactStorage;
        int endRange = contacts.size();

        if (index < 0 || index >= endRange) {
            throw new RagebaitException("I CAN'T DELETE! THIS CONTACT DOES NOT EXIST!");
        }

        Contact selectedContact = contacts.get(index);
        String result = ui.getDeleteContact(selectedContact, endRange - 1); // You may need a method in UI

        contacts.remove(index);
        contactStorage.save(contacts);

        return result;
    }
}

