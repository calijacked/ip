package ragebait.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ragebait.contacts.Contact;
import ragebait.contacts.ContactList;
import ragebait.exception.RagebaitException;
import ragebait.storage.ContactStorage;
import ragebait.ui.UI;

public class AddContactCommandTest {

    private ContactList contacts;
    private UI ui;
    private ContactStorage storage;
    private Context context;

    @BeforeEach
    void setUp() {
        contacts = new ContactList(new ArrayList<>());
        ui = new UI();
        storage = new ContactStorage("testContacts.txt");
        context = new Context(null, null, contacts, storage);
    }

    @Test
    void testAddContactSuccess() throws RagebaitException {
        String input = "/name John Doe /phone 91234567 /email john@example.com";
        AddContactCommand cmd = new AddContactCommand(input);

        String result = cmd.execute(ui, context);
        assertTrue(result.contains("John Doe"));
        assertEquals(1, contacts.size());

        Contact added = contacts.get(0);
        assertEquals("John Doe", added.getName());
        assertEquals("91234567", added.getPhone());
        assertEquals("john@example.com", added.getEmail());
    }

    @Test
    void testMissingMarkers() {
        String input = "John Doe 91234567 john@example.com"; // no /name /phone /email

        AddContactCommand cmd = new AddContactCommand(input);
        RagebaitException e = assertThrows(RagebaitException.class, () -> cmd.execute(ui, context));
        assertTrue(e.getMessage().contains("Wow. You had ONE job"));
    }

    @Test
    void testInvalidEmail() {
        String input = "/name Jane /phone 81234567 /email jane_at_example.com";
        AddContactCommand cmd = new AddContactCommand(input);

        RagebaitException e = assertThrows(RagebaitException.class, () -> cmd.execute(ui, context));
        assertTrue(e.getMessage().contains("Really?"));
    }

    @Test
    void testInvalidPhone() {
        String input = "/name Jane /phone 123456 /email jane@example.com"; // too short
        AddContactCommand cmd = new AddContactCommand(input);

        RagebaitException e = assertThrows(RagebaitException.class, () -> cmd.execute(ui, context));
        assertTrue(e.getMessage().contains("Eight digits"));
    }

    @Test
    void testExtraSpacesTrimmed() throws RagebaitException {
        String input = "/name   Alice   /phone  87654321 /email  alice@test.com  ";
        AddContactCommand cmd = new AddContactCommand(input);

        String result = cmd.execute(ui, context);
        Contact added = contacts.get(0);

        assertEquals("Alice", added.getName());
        assertEquals("87654321", added.getPhone());
        assertEquals("alice@test.com", added.getEmail());
        assertTrue(result.contains("Alice"));
    }
}
