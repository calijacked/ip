package ragebait.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ragebait.command.*;
import ragebait.command.ListTaskCommand;
import ragebait.exception.RagebaitException;

public class ParserTest {

    @Test
    public void testParseKnownCommands() throws RagebaitException {
        assertTrue(Parser.parse("list") instanceof ListTaskCommand);
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("todo read book") instanceof AddTaskCommand);
        assertTrue(Parser.parse("deadline submit /by 01/01/2026 1200") instanceof AddTaskCommand);
    }

    @Test
    public void testParseMarkCommand() throws RagebaitException {
        MarkTaskCommand mark = (MarkTaskCommand) Parser.parse("mark 1");
        assertEquals(0, mark.getIndex());
    }

    @Test
    public void testParseUnmarkCommand() throws RagebaitException {
        UnmarkTaskCommand unmark = (UnmarkTaskCommand) Parser.parse("unmark 2");
        assertEquals(1, unmark.getIndex());
    }

    @Test
    public void testParseDeleteCommand() throws RagebaitException {
        DeleteTaskCommand delete = (DeleteTaskCommand) Parser.parse("delete 3");
        assertEquals(2, delete.getIndex());
    }

    @Test
    public void testInvalidIndexFormat() {
        assertThrows(RagebaitException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void testMissingMarkArgument() {
        assertThrows(RagebaitException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void testMissingTodoDescription() {
        assertThrows(RagebaitException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void testMissingDeadlineArguments() {
        assertThrows(RagebaitException.class, () -> Parser.parse("deadline"));
    }

    @Test
    public void testMissingEventArguments() {
        assertThrows(RagebaitException.class, () -> Parser.parse("event"));
    }

    @Test
    public void testEmptyCommand() {
        assertThrows(RagebaitException.class, () -> Parser.parse(""));
    }

    @Test
    public void testUnknownCommandThrowsException() {
        RagebaitException exception = assertThrows(RagebaitException.class, () -> Parser.parse("fly away"));

        assertEquals("Unknown command!", exception.getMessage());
    }
}
