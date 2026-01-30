package ragebait.storage;

import ragebait.task.Deadline;
import ragebait.task.Event;
import ragebait.task.Task;
import ragebait.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to persistent storage.
 * Manages reading tasks from a file at startup and writing them back whenever tasks change.
 */
public class Storage {

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
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            // Ensure folder exists
            file.getParentFile().mkdirs();

            // If file does not exist, create and return empty list
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println("Skipping corrupted line: " + line);
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
    public void save(ArrayList<Task> tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

            for (Task task : tasks) {
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
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                return new Deadline(description, parts[3], isDone);
            case "E":
                LocalDateTime fromDateTime = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime toDateTime = LocalDateTime.parse(parts[4], formatter);
                return new Event(description, fromDateTime, toDateTime, isDone);
            default:
                throw new IllegalArgumentException("Unknown ragebait.task type");
        }
    }
}
