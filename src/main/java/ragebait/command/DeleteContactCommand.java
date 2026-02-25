package ragebait.command;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;
import ragebait.storage.ContactStorage;
import ragebait.ui.UI;

/**
 * Command to delete a contact from the contact list.
 *
 * The contact is removed based on its 0-based index.
 * If the index is out of bounds, a RagebaitException is thrown
 * with a rage-level message to remind the user that
 * not all imaginary numbers exist in real life.
 */
public class DeleteContactCommand extends ContactCommand {

    /**
     * The minimum valid index for contacts.
     */
    private static final int START_RANGE = 0;

    /**
     * The 0-based index of the contact to delete.
     */
    private final int index;

    /**
     * Constructs a DeleteContactCommand for the contact at the specified index.
     *
     * @param index 0-based index of the contact to delete.
     */
    public DeleteContactCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete contact command.
     *
     * Removes the contact at the specified index from the contact list,
     * updates storage, and returns a UI message confirming deletion.
     *
     * @param ui The UI component used to generate feedback messages.
     * @param context The execution context containing contacts and storage.
     * @return A message confirming contact deletion.
     * @throws RagebaitException If the index is out of bounds.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        ContactList contacts = context.contacts;
        ContactStorage contactStorage = context.contactStorage;
        int endRange = contacts.size();

        if (index < START_RANGE || index >= endRange) {
            throw new RagebaitException(
                    "I CAN'T DELETE! THIS CONTACT DOES NOT EXIST! PAY ATTENTION NEXT TIME."
            );
        }

        Contact selectedContact = contacts.get(index);
        String result = ui.getDeleteContact(selectedContact, endRange - 1);

        contacts.remove(index);
        contactStorage.save(contacts);

        return result;
    }
}
