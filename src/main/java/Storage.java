package main.java;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Load tasks when chatbot starts
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(filePath);

        try {
            // Handle folder-does-not-exist
            file.getParentFile().mkdirs();

            // Handle file-does-not-exist
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
                    // Stretch goal: corrupted line → skip
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error loading data.");
        }

        return tasks;
    }

    // Save whenever task list changes
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

    // Convert file line → Task object
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                return new Deadline(description, parts[3], isDone);
            case "E":
                return new Event(description, parts[3], parts[4], isDone);
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }
}
