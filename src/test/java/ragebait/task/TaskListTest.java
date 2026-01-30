package ragebait.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddAndGetSize() {
        TaskList tasks = new TaskList();

        // Initially empty
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
        assertEquals("No tasks in the list!", tasks.listTasks());

        // Add tasks
        tasks.add(new ToDo("Do homework"));
        tasks.add(new ToDo("Go shopping"));

        String expected = "Here are the tasks in your list:\n" +
                "1.[T][ ] Do homework\n" +
                "2.[T][ ] Go shopping\n";

        assertEquals(expected, tasks.listTasks());
    }

    @Test
    public void testGetAllTasks() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("Test task");
        tasks.add(todo);

        assertTrue(tasks.getAllTasks().contains(todo));
        assertEquals(1, tasks.getAllTasks().size());
    }
}
