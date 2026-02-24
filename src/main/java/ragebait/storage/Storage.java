package ragebait.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ragebait.exception.RagebaitException;
import ragebait.task.Deadline;
import ragebait.task.Event;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.task.TaskType;
import ragebait.task.ToDo;

/**
 * Handles persistent storage of tasks for the Ragebait application.
 * Responsible for reading tasks from a file on startup and saving tasks
 * whenever the TaskList changes.
 */
public class Storage {

    private static final int MIN_TASK_PARTS = 3;
    private static final int MAX_TASK_PARTS = 5;
    private static final String SEPARATOR = " \\| ";
    private static final String MARKED_DONE = "1";

    /** Path to the file used for storing tasks */
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath Path to the file used for persisting tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file or parent directories do not exist, they are created.
     * Each line is parsed into a Task object. Corrupted lines throw an exception.
     *
     * @return TaskList containing all successfully loaded tasks.
     * @throws RagebaitException If a line is corrupted or cannot be parsed.
     */
    public TaskList load() throws RagebaitException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        // Ensure parent directories exist
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        // If file doesn't exist yet, return empty task list
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (RagebaitException e) {
                    throw new RagebaitException("Aborting load. Corrupted line: " + line, e);
                }
            }
        } catch (IOException e) {
            throw new RagebaitException("Error reading storage file: " + filePath, e);
        }

        return tasks;
    }

    /**
     * Saves all tasks in the TaskList to the storage file.
     * Overwrites any previous content in the file.
     *
     * @param tasks TaskList containing tasks to save.
     * @throws RagebaitException If writing to the file fails.
     */
    public void save(TaskList tasks) throws RagebaitException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getAllTasks()) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RagebaitException("Error saving tasks to file: " + filePath, e);
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     * Supports ToDo, Deadline, and Event tasks.
     *
     * @param line The line representing a task in storage format.
     * @return The Task object represented by the line.
     * @throws RagebaitException If the line format is invalid or task type is unknown.
     */
    public Task parseTask(String line) throws RagebaitException {
        String[] parts = line.split(SEPARATOR);

        if (parts.length < MIN_TASK_PARTS || parts.length > MAX_TASK_PARTS) {
            throw new RagebaitException("Corrupted task line: incorrect number of fields.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        TaskType type = TaskType.convertToTaskType(parts[0]);
        boolean isDone = MARKED_DONE.equals(parts[1]);
        String description = parts[2];

        switch (type) {
        case TODO:
            return new ToDo(description, isDone);
        case DEADLINE:
            LocalDateTime byDateTime = LocalDateTime.parse(parts[3], formatter);
            return new Deadline(description, byDateTime, isDone);
        case EVENT:
            LocalDateTime fromDateTime = LocalDateTime.parse(parts[3], formatter);
            LocalDateTime toDateTime = LocalDateTime.parse(parts[4], formatter);
            return new Event(description, fromDateTime, toDateTime, isDone);
        default:
            throw new RagebaitException("Unknown task type found in storage file.");
        }
    }
}
