package ragebait.contacts;

/**
 * Represents a single contact in the Ragebait contact list.
 *
 * Each contact has a name, phone number, and email address.
 * Provides basic getters and setters for updating contact details.
 * Also supports conversion to a file-friendly format.
 */
public class Contact {

    /** Contact's name. */
    private String name;

    /** Contact's phone number. */
    private String phone;

    /** Contact's email address. */
    private String email;

    /**
     * Constructs a new Contact with the specified details.
     * Leading and trailing whitespace is trimmed from all fields.
     *
     * @param name  The contact's name.
     * @param phone The contact's phone number.
     * @param email The contact's email address.
     */
    public Contact(String name, String phone, String email) {
        this.name = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
    }

    // Getters

    /** Returns the contact's name. */
    public String getName() {
        return name;
    }

    /** Returns the contact's phone number. */
    public String getPhone() {
        return phone;
    }

    /** Returns the contact's email address. */
    public String getEmail() {
        return email;
    }

    // Setters

    /** Updates the contact's phone number. */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Updates the contact's email address. */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a human-readable string representation of the contact.
     *
     * @return Formatted string: "Name: name | Phone: phone | Email: email"
     */
    @Override
    public String toString() {
        return "Name: " + name + " | Phone: " + phone + " | Email: " + email;
    }

    /**
     * Returns a string suitable for saving to a file.
     *
     * @return Formatted string: "name | phone | email"
     */
    public String toFileFormat() {
        return name + " | " + phone + " | " + email;
    }
}
