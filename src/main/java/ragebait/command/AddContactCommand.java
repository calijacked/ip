package ragebait.command;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;
import ragebait.storage.ContactStorage;
import ragebait.ui.UI;

public class AddContactCommand extends ContactCommand {
    public static final String NAME = "/name";
    public static final String PHONE_NUMBER = "/phone";
    public static final String EMAIL = "/email";
    private final String args;

    public AddContactCommand(String args) {
        this.args = args;
    }
    public String execute(UI ui, Context context) throws RagebaitException {
        ContactList contacts = context.contacts;
        ContactStorage contactStorage = context.contactStorage;

        Contact contact = createContact();

        contacts.add(contact);
        contactStorage.save(contacts);

        return ui.getContactAdded(contact, contacts.size());
    }

    private Contact createContact() throws RagebaitException {
        if (!args.contains(NAME) || !args.contains(PHONE_NUMBER) || !args.contains(EMAIL)) {
            System.out.println(args);
            throw new RagebaitException("Invalid format. Use: /name <NAME> /phone <PHONE> /email <EMAIL>");
        }
        // Split by the markers (assume strict order: /name, /phone, /email)
        String[] nameSplit = args.split(NAME);

        String[] phoneSplit = nameSplit[1].split(PHONE_NUMBER);
        String[] emailSplit = phoneSplit[1].split(EMAIL);

        String name = phoneSplit[0].trim();
        String phone = emailSplit[0].trim();
        String email = emailSplit[1].trim();
        return new Contact(name, phone, email);
    }
}
