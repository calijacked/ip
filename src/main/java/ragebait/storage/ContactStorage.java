package ragebait.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;

public class ContactStorage {
    private final String filePath;

    public ContactStorage(String filePath) {
        this.filePath = filePath;
    }

    public ContactList load() {
        ContactList contacts = new ContactList();
        File file = new File(filePath);
        if (!file.exists()) return contacts;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void save(ContactList contacts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact c : contacts.getAllContacts()) {
                bw.write(c.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
