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

/**
 * Handles persistent storage of contacts for the Ragebait application.
 *
 * Supports loading contacts from a file and saving them back.
 * Ensures directories exist and throws rage-level exceptions when
 * the file is missing, corrupted, or unwritable.
 */
public class ContactStorage {

    /** Regex separator used for file storage. */
    private static final String SEPARATOR = " \\| ";

    /** Path to the storage file. */
    private final String filePath;

    /**
     * Constructs a ContactStorage for the given file path.
     *
     * @param filePath Path to the file where contacts will be loaded/saved.
     */
    public ContactStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads contacts from the storage file.
     *
     * Creates parent directories if they do not exist.
     * Returns an empty ContactList if the file does not exist yet.
     *
     * @return ContactList containing all successfully loaded contacts.
     * @throws RagebaitException If reading the file fails or a line is corrupted.
     */
    public ContactList load() throws RagebaitException {
        ContactList contacts = new ContactList();
        File file = new File(filePath);

        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        if (!file.exists()) {
            return contacts; // Empty list, calm down
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
     * @param contacts ContactList containing all contacts to persist.
     * @throws RagebaitException If writing to the file fails.
     */
    public void save(ContactList contacts) throws RagebaitException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact c : contacts.getAllContacts()) {
                bw.write(c.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RagebaitException("Error saving contacts to file: " + filePath
                    + ". Fix your disk or permissions!", e);
        }
    }

    /**
     * Parses a single line from storage into a Contact object.
     *
     * Expected format: name | phone | email
     *
     * @param line Line from the storage file.
     * @return Contact object created from the line.
     * @throws RagebaitException If the line format is invalid or corrupted.
     */
    private Contact parseContactLine(String line) throws RagebaitException {
        String[] parts = line.split(SEPARATOR);
        if (parts.length != 3) {
            throw new RagebaitException("Corrupted contact line detected: " + line + " WHAT DID YOU DO?!");
        }
        String name = parts[0].trim();
        String phone = parts[1].trim();
        String email = parts[2].trim();

        return new Contact(name, phone, email);
    }
}
