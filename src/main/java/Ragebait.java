package main.java;

public class Ragebait {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Ragebait(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showMessage("Error loading tasks. Starting with an empty list.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);  // Returns a Command object
                c.execute(tasks, ui, storage);
                isExit = c.isExit();  // ExitCommand will set this to true
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.close();
    }

    public static void main(String[] args) {
        new Ragebait("data/ragebait.txt").run();
    }
}
