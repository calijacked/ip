package ragebait.command;

import ragebait.contacts.ContactList;
import ragebait.ui.UI;

public class ListContactCommand extends ContactCommand {
    private static final int NO_CONTACTS = 0;
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
