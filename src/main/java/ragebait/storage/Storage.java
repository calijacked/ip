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
import ragebait.task.*;

/**
 * Handles loading and saving tasks to persistent storage.
 * Manages reading tasks from a file at startup and writing them back whenever tasks change.
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
     * @param filePath Path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file. Creates the file and its parent directories if they do not exist.
     * Skips corrupted lines with a warning.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public TaskList load() throws RagebaitException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        // Ensure /data folder exists and creates the folder if it does not
        file.getParentFile().mkdirs();

        // File does not exist, start with an empty list
        if (!file.exists()) {
            return tasks;
        }

        try {
            // If a save file exist
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            // Stores the next line into string variable line and loops until EOF
            while ((line = br.readLine()) != null) {
                // Read the saved file and add the tasks to the task list
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
     * Saves all tasks to the storage file, overwriting its previous contents.
     *
     * @param tasks ArrayList of Task objects to save.
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
     * Converts a line from the storage file into a Task object.
     * Supports ToDo, Deadline, and Event tasks.
     *
     * @param line Line from the storage file representing a task.
     * @return Task object corresponding to the line.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    public Task parseTask(String line) throws RagebaitException {
        String[] parts = line.split(SEPERATOR);

        if (parts.length < MIN_TASK_PARTS || parts.length > MAX_TASK_PARTS) {
            throw new RagebaitException("Line is corrupted and is not in correct format");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        TaskType type = TaskType.convertToTaskType(parts[0]); // T or D or E
        boolean isDone = parts[1].equals(MARKED_DONE); // 1 or 0
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
