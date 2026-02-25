package ragebait.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddAndGetSize() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());

        ToDo todo1 = new ToDo("Finish homework");
        tasks.add(todo1);
        assertEquals(1, tasks.size());
        assertEquals(todo1, tasks.get(0));

        ToDo todo2 = new ToDo("Clean room");
        tasks.add(todo2);
        assertEquals(2, tasks.size());
        assertEquals(todo2, tasks.get(1));
    }

    @Test
    public void testAddNullTaskThrows() {
        TaskList tasks = new TaskList();
        assertThrows(IllegalArgumentException.class, () -> tasks.add(null));
    }

    @Test
    public void testRemove() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("Remove me");
        tasks.add(todo);

        Task removed = tasks.remove(0);
        assertEquals(todo, removed);
        assertEquals(0, tasks.size());
    }

    @Test
    public void testListTasks() {
        TaskList tasks = new TaskList();

        // Empty list
        assertEquals("Task list is empty.", tasks.listTasks());

        // Add tasks
        tasks.add(new ToDo("Do homework"));
        tasks.add(new ToDo("Go shopping"));

        String listOutput = tasks.listTasks();
        assertTrue(listOutput.contains("Do homework"));
        assertTrue(listOutput.contains("Go shopping"));
        assertTrue(listOutput.contains("1. "));
        assertTrue(listOutput.contains("2. "));
    }

    @Test
    public void testGetAllTasks() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("Test task");
        tasks.add(todo);

        assertTrue(tasks.getAllTasks().contains(todo));
        assertEquals(1, tasks.getAllTasks().size());
    }

    @Test
    public void testIsEmpty() {
        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());

        tasks.add(new ToDo("Not empty anymore"));
        assertTrue(!tasks.isEmpty());
    }
}
