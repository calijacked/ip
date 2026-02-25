package ragebait.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a collection of Contact objects in Ragebait.
 *
 * Provides methods to add, remove, retrieve, and list contacts.
 * Ensures the internal list cannot be modified directly by callers.
 * Supports creating an empty list or initializing with existing contacts.
 */
public class ContactList {

    /** Internal list storing contacts. */
    private final List<Contact> contacts;

    /**
     * Constructs an empty ContactList.
     */
    public ContactList() {
        contacts = new ArrayList<>();
    }

    /**
     * Constructs a ContactList with an existing list of contacts.
     * Creates a copy to prevent external modifications.
     *
     * @param contacts List of contacts to initialize the ContactList.
     */
    public ContactList(List<Contact> contacts) {
        this.contacts = new ArrayList<>(contacts);
    }

    /**
     * Adds a contact to the list.
     *
     * @param c The Contact to add.
     */
    public void add(Contact c) {
        contacts.add(c);
    }

    /**
     * Removes the contact at the specified index.
     *
     * @param index 0-based index of the contact to remove.
     * @return The removed Contact.
     */
    public Contact remove(int index) {
        return contacts.remove(index);
    }

    /**
     * Retrieves the contact at the specified index.
     *
     * @param index 0-based index of the contact to retrieve.
     * @return The Contact at the specified index.
     */
    public Contact get(int index) {
        return contacts.get(index);
    }

    /**
     * Returns the number of contacts in the list.
     *
     * @return The size of the contact list.
     */
    public int size() {
        return contacts.size();
    }

    /**
     * Checks if the contact list is empty.
     *
     * @return True if the list has no contacts, false otherwise.
     */
    public boolean isEmpty() {
        return contacts.isEmpty();
    }

    /**
     * Returns an unmodifiable view of all contacts.
     *
     * @return A list of all contacts that cannot be modified directly.
     */
    public List<Contact> getAllContacts() {
        return Collections.unmodifiableList(contacts);
    }

    /**
     * Returns a human-readable string listing all contacts.
     *
     * Each contact is numbered starting from 1.
     * If the list is empty, returns a rage-level message indicating no contacts exist.
     *
     * @return Formatted string of all contacts or "No contacts!" if empty.
     */
    public String listContacts() {
        if (contacts.isEmpty()) {
            return "No contacts! ARE YOU KIDDING ME, EMPTY LIST AGAIN?!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contacts.size(); i++) {
            sb.append(i + 1).append(". ").append(contacts.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
