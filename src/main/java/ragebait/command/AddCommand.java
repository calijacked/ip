package ragebait.command;

import ragebait.storage.Storage;
import ragebait.task.*;
import ragebait.ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Command to add a new task (ToDo, Deadline, or Event) to the TaskList.
 * Parses the user input and creates the appropriate Task object.
 */
public class AddCommand extends Command {
    private final String type;
    private final String args;

    /**
     * Constructs an AddCommand with the specified task type and arguments.
     *
     * @param type Type of the task ("todo", "deadline", or "event").
     * @param args Arguments for the task (description and optional date/time).
     */
    public AddCommand(String type, String args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Parses the input arguments and adds the corresponding Task to the TaskList.
     * Displays messages via UI about the added task or any errors in input.
     *
     * @param tasks TaskList to add the new task to.
     * @param ui UI to display messages to the user.
     * @param storage Storage for saving tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            Task t = null;

            switch (type) {
                case "todo":
                    t = new ToDo(args);  // ToDos do not need dates
                    break;

                case "deadline":
                    if (!args.contains("/by")) {
                        ui.showMessage("Please include a /by date!");
                        return;
                    }
                    String[] dParts = args.split("/by", 2);
                    DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime by = LocalDateTime.parse(dParts[1].trim(), dFormatter);
                    t = new Deadline(dParts[0].trim(), by);
                    break;

                case "event":
                    if (!args.contains("/from") || !args.contains("/to")) {
                        ui.showMessage("Please include a /from and /to date!");
                        return;
                    }
                    String[] eParts = args.split("/from", 2);
                    String desc = eParts[0].trim();
                    String[] fromTo = eParts[1].split("/to", 2);
                    DateTimeFormatter eFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime from = LocalDateTime.parse(fromTo[0].trim(), eFormatter);
                    LocalDateTime to = LocalDateTime.parse(fromTo[1].trim(), eFormatter);
                    t = new Event(desc, from, to);
                    break;
            }

            if (t != null) {
                tasks.add(t);
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(t.toString());
                ui.showMessage("Now you have " + tasks.size() + " task(s) in the list.");
            }

        } catch (Exception e) {
            ui.showMessage("Invalid input or date format! Use dd/MM/yyyy HHmm.");
        }
    }
}
