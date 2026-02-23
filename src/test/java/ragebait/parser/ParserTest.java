package ragebait.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ragebait.command.AddCommand;
import ragebait.command.DeleteCommand;
import ragebait.command.ExitCommand;
import ragebait.command.ListCommand;
import ragebait.command.MarkCommand;
import ragebait.command.UnmarkCommand;
import ragebait.exception.RagebaitException;



public class ParserTest {

    @Test
    public void testParseKnownCommands() throws RagebaitException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("todo read book") instanceof AddCommand);
        assertTrue(Parser.parse("deadline submit /by 01/01/2026 1200") instanceof AddCommand);
    }

    @Test
    public void testParseMarkCommand() throws RagebaitException {
        MarkCommand mark = (MarkCommand) Parser.parse("mark 1");
        assertEquals(0, mark.getIndex());
    }

    @Test
    public void testParseUnmarkCommand() throws RagebaitException {
        UnmarkCommand unmark = (UnmarkCommand) Parser.parse("unmark 2");
        assertEquals(1, unmark.getIndex());
    }

    @Test
    public void testParseDeleteCommand() throws RagebaitException {
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
