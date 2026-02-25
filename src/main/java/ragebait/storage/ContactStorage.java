package ragebait.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;

public class ContactStorage {

    private static final String SEPARATOR = " \\| ";
    private final String filePath;

    public ContactStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads contacts from storage file.
     * Creates parent directories if they don't exist.
     *
     * @return ContactList containing all successfully loaded contacts.
     * @throws RagebaitException If reading file fails or line is malformed.
     */
    public ContactList load() throws RagebaitException {
        ContactList contacts = new ContactList();
        File file = new File(filePath);

        // Ensure parent directories exist
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        // If file doesn't exist yet, return empty list
        if (!file.exists()) {
            return contacts;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Contact contact = parseContactLine(line);
                    contacts.add(contact);
                } catch (RagebaitException e) {
                    throw new RagebaitException("Aborting load. Corrupted line: " + line, e);
                }
            }
        } catch (IOException e) {
            throw new RagebaitException("Error reading contact storage file: " + filePath, e);
        }

        return contacts;
    }

    /**
     * Saves all contacts to the storage file.
     *
     * @param contacts ContactList containing all contacts to save.
     * @throws RagebaitException If writing to the file fails.
     */
    public void save(ContactList contacts) throws RagebaitException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact c : contacts.getAllContacts()) {
                bw.write(c.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RagebaitException("Error saving contacts to file: " + filePath, e);
        }
    }

    /**
     * Parses a single line from the storage file into a Contact object.
     *
     * Expected format: name | phone | email
     *
     * @param line The line representing a contact in storage format.
     * @return Contact object
     * @throws RagebaitException If line format is invalid.
     */
    private Contact parseContactLine(String line) throws RagebaitException {
        String[] parts = line.split(SEPARATOR);
        if (parts.length != 3) {
            throw new RagebaitException("Corrupted contact line: " + line);
        }
        String name = parts[0].trim();
        String phone = parts[1].trim();
        String email = parts[2].trim();

        return new Contact(name, phone, email);
    }
}
