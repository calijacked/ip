package ragebait.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class UserInterfaceTest {

    private UI ui;

    @BeforeEach
    void setUp() {
        ui = new UI();
    }

    @Test
    void testShowWelcome() {
        String output = ui.getWelcome();
        assertTrue(output.contains("Ragebait"));
    }

    @Test
    void testShowLine() {
        String output = ui.showLine();
        assertTrue(output.contains("_"));
    }

    @Test
    void testShowError() {
        String output = ui.showError("Error!");
        assertTrue(output.contains("Error!"));
    }
}
