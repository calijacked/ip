package ragebait.command;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.ui.UI;

public class FindContactCommand extends ContactCommand {
    private final String keyword;
    public FindContactCommand(String keyword) {
        this.keyword = keyword;
    }
    public String execute(UI ui, Context context) {
        ContactList contacts = context.contacts;
        ContactList matchingContacts = new ContactList();

        for (Contact contact : contacts.getAllContacts()) {
            if (contact.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingContacts.add(contact);
            }
        }

        if (matchingContacts.isEmpty()) {
            return ui.getNoContactsFound();
        } else {
            return ui.getFindContactHeader(matchingContacts);
        }
    }
}
