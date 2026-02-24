package ragebait.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactList {
    private final List<Contact> contacts;

    public ContactList() {
        contacts = new ArrayList<>();
    }

    public ContactList(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void add(Contact c) {
        contacts.add(c);
    }

    public Contact remove(int index) {
        return contacts.remove(index);
    }

    public Contact get(int index) {
        return contacts.get(index);
    }

    public int size() {
        return contacts.size();
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }

    public List<Contact> getAllContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public String listContacts() {
        if (contacts.isEmpty()) {
            return "No contacts!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contacts.size(); i++) {
            sb.append(i + 1).append(". ").append(contacts.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
