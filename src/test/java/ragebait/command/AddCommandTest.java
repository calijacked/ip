package ragebait.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.TaskList;
import ragebait.task.TaskType;
import ragebait.ui.UI;

public class AddCommandTest {

    private TaskList tasks;
    private UI ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new UI();
        storage = new Storage("test.txt");
    }

    @Test
    public void testCreateTodoSuccess() throws RagebaitException {
        AddCommand cmd = new AddCommand(TaskType.TODO, "read book");
        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("read book"));
    }

    @Test
    public void testCreateDeadlineSuccess() throws RagebaitException {
        AddCommand cmd = new AddCommand(
                TaskType.DEADLINE,
                "submit /by 01/01/2026 1200");

        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("submit"));
    }

    @Test
    public void testCreateEventSuccess() throws RagebaitException {
        AddCommand cmd = new AddCommand(
                TaskType.EVENT,
                "meeting /from 1/1/2024 1210 /to 1/2/2024 2200");

        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("meeting"));
    }

    @Test
    public void testInvalidEventTimeFormat() {
        AddCommand cmd = new AddCommand(
                TaskType.EVENT,
                "lul /from 1/1/2024 1210 /to 1/2/2024 2401");

        assertThrows(RagebaitException.class, () -> cmd.execute(tasks, ui, storage));
    }

    @Test
    public void testEventEndBeforeStart() {
        AddCommand cmd = new AddCommand(
                TaskType.EVENT,
                "lul /from 1/1/2024 1210 /to 1/2/2022 2200");

        assertThrows(RagebaitException.class, () -> cmd.execute(tasks, ui, storage));
    }

    @Test
    public void testInvalidDeadlineDate() {
        AddCommand cmd = new AddCommand(
                TaskType.DEADLINE,
                "submit /by 32/01/2024 1200");

        assertThrows(RagebaitException.class, () -> cmd.execute(tasks, ui, storage));
    }

    @Test
    public void testMissingDeadlineTag() {
        AddCommand cmd = new AddCommand(
                TaskType.DEADLINE,
                "submit 01/01/2026 1200");

        assertThrows(RagebaitException.class, () -> cmd.execute(tasks, ui, storage));
    }

    @Test
    public void testMissingEventTags() {
        AddCommand cmd = new AddCommand(
                TaskType.EVENT,
                "meeting 1/1/2024 1200");

        assertThrows(RagebaitException.class, () -> cmd.execute(tasks, ui, storage));
    }
}
