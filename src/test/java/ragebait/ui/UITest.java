package ragebait.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class UITest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private UI ui;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        ui = new UI();
    }

    @Test
    void testShowWelcome() {
        ui.showWelcome();

        String output = outputStream.toString();

        assertTrue(output.contains("Ragebait"));
    }

    @Test
    void testShowLine() {
        ui.showLine();

        String output = outputStream.toString();

        assertTrue(output.contains("_"));
    }

    @Test
    void testShowError() {
        ui.showError("Error!");

        String output = outputStream.toString();

        assertTrue(output.contains("Error!"));
    }
}
