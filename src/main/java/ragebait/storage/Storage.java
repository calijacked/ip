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
 * Handles loading and saving tasks to persistent storage.
 * Manages reading tasks from a file at startup and writing tasks back whenever tasks change.
 */
public class Storage {
    static final int MIN_TASK_PARTS = 3;
    static final int MAX_TASK_PARTS = 5;
    private static final String SEPERATOR = " \\| ";
    private static final String MARKED_DONE = "1";

    /** Path to the file used for storing tasks */
    private final String filePath;

    /**
     * Constructs a Storage object with a given file path.
     *
     * @param filePath Path to the file for storing task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * Creates the file and parent directories if they do not exist.
     * Skips corrupted lines and throws an exception if corruption is detected.
     *
     * @return TaskList containing loaded Task objects.
     * @throws RagebaitException If storage loading fails or file data is corrupted.
     */
    public TaskList load() throws RagebaitException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        file.getParentFile().mkdirs();

        if (!file.exists()) {
            return tasks;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (RagebaitException e) {
                    throw new RagebaitException("Aborting operation. Corrupted line: " + line, e);
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error loading data.");
        }

        return tasks;
    }

    /**
     * Saves all tasks to the storage file, overwriting previous contents.
     *
     * @param tasks TaskList containing tasks to be saved.
     */
    public void save(TaskList tasks) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks.getAllTasks()) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     * Supports ToDo, Deadline, and Event task types.
     *
     * @param line Storage file line representing a task.
     * @return Parsed Task object.
     * @throws RagebaitException If the task type is unknown or format is invalid.
     */
    public Task parseTask(String line) throws RagebaitException {
        String[] parts = line.split(SEPERATOR);

        if (parts.length < MIN_TASK_PARTS || parts.length > MAX_TASK_PARTS) {
            throw new RagebaitException("Line is corrupted and is not in correct format");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        TaskType type = TaskType.convertToTaskType(parts[0]);
        boolean isDone = parts[1].equals(MARKED_DONE);
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
            throw new RagebaitException("Unknown task type found in file");
        }
    }
}