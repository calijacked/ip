package ragebait.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ragebait.Ragebait;
import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.*;
import ragebait.ui.UI;

/**
 * Command to add a new task (ToDo, Deadline, or Event) to the TaskList.
 * Parses the user input and creates the appropriate Task object.
 */
public class AddCommand extends Command {
    private Task task = null;
    private final TaskType type;
    private final String args;

    /**
     * Constructs an AddCommand with the specified task type and arguments.
     *
     * @param type Type of the task ("todo", "deadline", or "event").
     * @param args Arguments for the task (description and optional date/time).
     */
    public AddCommand(TaskType type, String args) {
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
    public void execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException {
        try {
            switch (type) {
            case TODO:
                task = new ToDo(args);
                break;

            case DEADLINE:
                if (!args.contains("/by")) {
                    throw new RagebaitException("Please include a /by date!");
                }
                String[] dParts = args.split("/by", 2);
                String dDescription = dParts[0].trim();
                DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime by = LocalDateTime.parse(dParts[1].trim(), dFormatter);
                task = new Deadline(dDescription, by);
                break;

            case EVENT:
                if (!args.contains("/from") || !args.contains("/to")) {
                    throw new RagebaitException("Please include a /from and /to date!");
                }
                String[] eParts = args.split("/from", 2);
                String eDescription = eParts[0].trim();
                String[] fromTo = eParts[1].split("/to", 2);
                DateTimeFormatter eFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime from = LocalDateTime.parse(fromTo[0].trim(), eFormatter);
                LocalDateTime to = LocalDateTime.parse(fromTo[1].trim(), eFormatter);
                task = new Event(eDescription, from, to);
                break;
            default:
                throw new RagebaitException("Unknown Type!");
            }

            if (task != null) {
                tasks.add(task);
                ui.getTaskAdded(task, tasks.size());
            }
        } catch (RagebaitException e) {
            throw new RagebaitException("Invalid input or date format! Use dd/MM/yyyy HHmm.");
        }
    }
}
