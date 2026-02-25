package ragebait.command;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.ui.UI;

/**
 * Command to search for contacts whose names contain a given keyword.
 *
 * Performs a case-insensitive search across all contacts and
 * collects matching contacts into a temporary list.
 *
 * If no contacts match, the UI will return a rage-infused
 * "no results found" message.
 */
public class FindContactCommand extends ContactCommand {

    /**
     * Keyword used to search for contacts.
     */
    private final String keyword;

    /**
     * Constructs a FindContactCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in contact names.
     */
    public FindContactCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find contact command.
     *
     * @param ui The UI used to generate feedback messages.
     * @param context The execution context containing contacts.
     * @return A message listing the matching contacts or a no contacts found message if there are no matches.
     */
    @Override
    public String execute(UI ui, Context context) {
        ContactList contacts = context.contacts;
        ContactList matchingContacts = new ContactList();

        for (Contact contact : contacts.getAllContacts()) {
            if (contact.getName().toLowerCase().contains(keyword.toLowerCase())) {
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
