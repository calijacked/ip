package ragebait.parser;

import org.junit.jupiter.api.Test;
import ragebait.command.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseKnownCommands() {
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("todo read book") instanceof AddCommand);
        assertTrue(Parser.parse("deadline submit /by 01/01/2026 1200") instanceof AddCommand);
    }

    @Test
    public void testParseMarkCommand() {
        MarkCommand mark = (MarkCommand) Parser.parse("mark 1");
        assertEquals(0, mark.getIndex());
    }

    @Test
    public void testParseUnmarkCommand() {
        UnmarkCommand unmark = (UnmarkCommand) Parser.parse("unmark 2");
        assertEquals(1, unmark.getIndex());
    }

    @Test
    public void testParseDeleteCommand() {
        DeleteCommand delete = (DeleteCommand) Parser.parse("delete 3");
        assertEquals(2, delete.getIndex());
    }

    @Test
    public void testUnknownCommandThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Parser.parse("fly away");
        });
        assertEquals("Unknown command!", exception.getMessage());
    }
}
