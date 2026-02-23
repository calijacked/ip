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
 * Represents a command that adds a new Task to the TaskList.
 * The task can be a TODO, DEADLINE, or EVENT.
 * Parses the user input arguments and creates the appropriate Task object.
 */
public class AddCommand extends Command {

    /** Tag used to separate description and deadline datetime. */
    public static final String TAG_BY = " /by ";

    /** Tag used to indicate the start datetime of an event. */
    public static final String TAG_FROM = " /from ";

    /** Tag used to indicate the end datetime of an event. */
    public static final String TAG_TO = " /to ";

    /** The task to be created and added to the list. */
    private Task task = null;

    /** The type of task to be created. */
    private final TaskType type;

    /** The raw argument string containing description and date/time details. */
    private final String args;

    /**
     * Creates an AddCommand with the specified task type and arguments.
     *
     * @param type The TaskType indicating whether the task is TODO, DEADLINE, or EVENT.
     * @param args The raw argument string containing the task description
     *             and any required date/time information.
     */
    public AddCommand(TaskType type, String args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Executes the command by parsing the arguments, creating the appropriate Task,
     * and adding it to the given TaskList.
     *
     * For DEADLINE tasks, the expected format is:
     * deadline {DESCRIPTION} /by d/M/yyyy HHmm
     *
     * For EVENT tasks, the expected format is:
     * event {DESCRIPTION} /from d/M/yyyy HHmm /to d/M/yyyy HHmm
     *
     * @param tasks The TaskList to add the new task to.
     * @param ui The UI used to display feedback messages.
     * @param storage The Storage responsible for saving tasks.
     * @throws RagebaitException If required fields are missing or if date/time parsing fails.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws RagebaitException {

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
            assert !to.isBefore(from) : "Event end datetime must not be before start datetime";;
            task = new Event(eDescription, from, to);
            break;

        default:
            throw new RagebaitException("Unknown Type!");
        }
        tasks.add(task);
        storage.save(tasks);
        return ui.getTaskAdded(task, tasks.size());
    }
}
