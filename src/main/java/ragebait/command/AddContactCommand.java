package ragebait.command;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;
import ragebait.storage.ContactStorage;
import ragebait.ui.UI;

/**
 * Represents a command that adds a Contact to the ContactList.
 *
 * Expected argument format:
 * /name NAME /phone PHONE /email EMAIL
 *
 * This command parses the raw argument string, validates the extracted
 * name, phone number, and email, creates a new Contact, adds it
 * to the list, and persists the updated list using ContactStorage.
 *
 * If the input format is invalid or any validation fails, a
 * RagebaitException will be thrown with an appropriately
 * rage-inducing message reminding the user to read instructions properly.
 */
public class AddContactCommand extends ContactCommand {

    /**
     * Marker used to indicate the start of the contact name.
     */
    public static final String NAME = "/name";

    /**
     * Marker used to indicate the start of the contact phone number.
     */
    public static final String PHONE_NUMBER = "/phone";

    /**
     * Marker used to indicate the start of the contact email.
     */
    public static final String EMAIL = "/email";

    /**
     * Raw argument string provided by the user.
     */
    private final String args;

    /**
     * Constructs an AddContactCommand with the specified argument string.
     *
     * @param args Raw input arguments containing name, phone, and email markers.
     */
    public AddContactCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the add contact command.
     *
     * Parses and validates user input, creates a Contact,
     * adds it to the ContactList, saves it using ContactStorage,
     * and returns a UI message confirming the addition.
     *
     * @param ui The UI instance used to generate user-facing messages.
     * @param context The execution context containing shared data such as contacts and storage.
     * @return A confirmation message from the UI.
     * @throws RagebaitException If the input format is invalid or validation fails.
     */
    public String execute(UI ui, Context context) throws RagebaitException {
        ContactList contacts = context.contacts;
        ContactStorage contactStorage = context.contactStorage;

        Contact contact = createContact();

        contacts.add(contact);
        contactStorage.save(contacts);

        return ui.getContactAdded(contact, contacts.size());
    }

    /**
     * Parses the raw argument string and creates a Contact.
     *
     * Validates the presence of required markers, extracts the name,
     * phone number, and email in strict order (/name, /phone, /email),
     * and validates their formats.
     *
     * @return A newly constructed Contact.
     * @throws RagebaitException If any required marker is missing, or if the email or phone number format is invalid.
     */
    private Contact createContact() throws RagebaitException {
        if (!args.contains(NAME) || !args.contains(PHONE_NUMBER) || !args.contains(EMAIL)) {
            throw new RagebaitException(
                    "Wow. You had ONE job. Format it properly: contact /name <NAME> /phone <PHONE> /email <EMAIL>."
            );
        }

        // Split by the markers (assume strict order: /name, /phone, /email)
        String[] nameSplit = args.split(NAME);
        String[] phoneSplit = nameSplit[1].split(PHONE_NUMBER);
        String[] emailSplit = phoneSplit[1].split(EMAIL);

        String name = phoneSplit[0].trim();
        String phone = emailSplit[0].trim();
        String email = emailSplit[1].trim();

        if (!isValidEmail(email)) {
            throw new RagebaitException(
                    "That email? Really? \"" + email + "\" is not it. Try something that actually looks like an email."
            );
        }

        if (!isValidPhone(phone)) {
            throw new RagebaitException(
                    "Eight digits. Just eight. Not seven. Not nine. \"" + phone + "\" is embarrassing."
            );
        }

        return new Contact(name, phone, email);
    }

    /**
     * Validates the format of an email address.
     *
     * Uses a simple regular expression to check for a standard
     * username@domain pattern.
     *
     * @param email The email string to validate.
     * @return true if the email matches the expected pattern, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }

    /**
     * Validates the format of a phone number.
     *
     * The phone number must consist of exactly 8 digits.
     *
     * @param phone The phone number string to validate.
     * @return true if the phone number contains exactly 8 digits, false otherwise.
     */
    public static boolean isValidPhone(String phone) {
        String regex = "\\d{8}";
        return phone != null && phone.matches(regex);
    }
}
