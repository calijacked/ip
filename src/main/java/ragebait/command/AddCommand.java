package ragebait.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ragebait.exception.RagebaitException;
import ragebait.storage.Storage;
import ragebait.task.Deadline;
import ragebait.task.Event;
import ragebait.task.Task;
import ragebait.task.TaskList;
import ragebait.task.TaskType;
import ragebait.task.ToDo;
import ragebait.ui.UI;

/**
 * Command to add a new task (ToDo, Deadline, or Event) to the TaskList.
 * Parses the user input and creates the appropriate Task object.
 */
public class AddCommand extends Command {
    public static final String TAG_BY = " /by ";
    public static final String TAG_FROM = " /from ";
    public static final String TAG_TO = " /to ";
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
                if (!args.contains(TAG_BY)) {
                    throw new RagebaitException("Please include a /by datetime exactly like this: \n"
                            + "deadline {DESCRIPTTON} /by d/M/yyyy HHmm");
                }
                String[] dParts = args.split(TAG_BY, 2);
                String dDescription = dParts[0].trim();
                DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime by;
                try {
                    by = LocalDateTime.parse(dParts[1].trim(), dFormatter);
                } catch (DateTimeParseException e) {
                    throw new RagebaitException("Invalid by datetime format! Use d/M/yyyy HHmm");
                }
                task = new Deadline(dDescription, by);
                break;

            case EVENT:
                if (!args.contains(TAG_FROM) || !args.contains(TAG_TO)) {
                    throw new RagebaitException("Please include a /from datetime and /to datetime exactly like this: \n"
                            + "event {DESCRIPTION} /from d/M/yyyy HHmm /to d/M/yyyy HHmm");
                }
                String[] eParts = args.split(TAG_FROM, 2);
                String eDescription = eParts[0].trim();
                String[] fromTo = eParts[1].split(TAG_TO, 2);
                DateTimeFormatter eFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime from;
                LocalDateTime to;
                try {
                    from = LocalDateTime.parse(fromTo[0].trim(), eFormatter);
                } catch (DateTimeParseException e) {
                    throw new RagebaitException("Invalid from datetime format! Use d/M/yyyy HHmm");
                }
                try {
                    to = LocalDateTime.parse(fromTo[1].trim(), eFormatter);
                } catch (DateTimeParseException e) {
                    throw new RagebaitException("Invalid to datetime format! Use d/M/yyyy HHmm");
                }
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
            throw e;
        }
    }
}
