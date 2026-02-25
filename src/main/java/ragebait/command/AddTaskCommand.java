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
 * Represents a command that creates and adds a new Task to the TaskList.
 *
 * Depending on the TaskType, this command parses user input and creates
 * a ToDo, Deadline, or Event task.
 *
 * Expected input formats:
 * TODO: todo DESCRIPTION
 * DEADLINE: deadline DESCRIPTION /by d/M/yyyy HHmm
 * EVENT: event DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm
 *
 * If the user decides to freestyle the format instead of following
 * instructions, a RagebaitException will be thrown accordingly.
 */
public class AddTaskCommand extends TaskCommand {

    /**
     * Tag used to separate description and deadline datetime.
     */
    public static final String TAG_BY = " /by ";

    /**
     * Tag used to indicate the start datetime of an event.
     */
    public static final String TAG_FROM = " /from ";

    /**
     * Tag used to indicate the end datetime of an event.
     */
    public static final String TAG_TO = " /to ";

    /**
     * Date-time pattern used for parsing user input.
     */
    private static final String DATE_TIME_PATTERN = "d/M/yyyy HHmm";

    /**
     * Formatter for parsing date-time strings.
     */
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    /**
     * The type of task to be created.
     */
    private final TaskType type;

    /**
     * The raw argument string containing description and date/time details.
     */
    private final String args;

    /**
     * Constructs an AddTaskCommand.
     *
     * @param type The type of task to create.
     * @param args The raw argument string provided by the user.
     */
    public AddTaskCommand(TaskType type, String args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Executes the command by creating the appropriate task,
     * adding it to the task list, saving it to storage,
     * and returning a confirmation message.
     *
     * @param ui The UI used to generate feedback.
     * @param context The execution context containing task list and storage.
     * @return A formatted message confirming task addition.
     * @throws RagebaitException If input validation or parsing fails.
     */
    @Override
    public String execute(UI ui, Context context) throws RagebaitException {
        TaskList tasks = context.tasks;
        Storage taskStorage = context.taskStorage;
        Task task = createTask();

        tasks.add(task);
        taskStorage.save(tasks);

        return ui.getTaskAdded(task, tasks.size());
    }

    /**
     * Creates a Task based on the task type and user arguments.
     *
     * @return The constructed Task.
     * @throws RagebaitException If the task type is unknown or if parsing fails due to invalid input.
     */
    private Task createTask() throws RagebaitException {
        switch (type) {
        case TODO:
            return new ToDo(args);

        case DEADLINE:
            return parseDeadline();

        case EVENT:
            return parseEvent();

        default:
            throw new RagebaitException("Unknown task type. Did you invent a new one or just break the parser?"
                );
        }
    }

    /**
     * Parses arguments and creates a Deadline task.
     *
     * Expected format:
     * DESCRIPTION /by d/M/yyyy HHmm
     *
     * @return A Deadline task.
     * @throws RagebaitException If the format is missing the /by tag or the datetime cannot be parsed.
     */
    private Task parseDeadline() throws RagebaitException {
        if (!args.contains(TAG_BY)) {
            throw new RagebaitException(
                    "Missing /by. It's literally in the format: deadline DESCRIPTION /by "
                            + DATE_TIME_PATTERN + ". Read."
            );
        }

        String[] parts = args.split(TAG_BY, 2);
        String description = parts[0].trim();
        LocalDateTime by = parseDateTime(parts[1].trim(), "by");

        return new Deadline(description, by);
    }

    /**
     * Parses arguments and creates an Event task.
     *
     * Expected format:
     * DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm
     *
     * @return An Event task.
     * @throws RagebaitException If required tags are missing, the datetime cannot be parsed,or the end is before start.
     **/
    private Task parseEvent() throws RagebaitException {
        if (!args.contains(TAG_FROM) || !args.contains(TAG_TO)) {
            throw new RagebaitException(
                    "Missing /from or /to. The format is not decorative. Use: event DESCRIPTION /from "
                            + DATE_TIME_PATTERN + " /to " + DATE_TIME_PATTERN + "."
            );
        }

        String[] parts = args.split(TAG_FROM, 2);
        String description = parts[0].trim();
        String[] fromTo = parts[1].split(TAG_TO, 2);

        LocalDateTime from = parseDateTime(fromTo[0].trim(), "from");
        LocalDateTime to = parseDateTime(fromTo[1].trim(), "to");

        if (to.isBefore(from)) {
            throw new RagebaitException("Your event ends before it starts. Time travel isn't supported. Fix your dates."
            );
        }

        return new Event(description, from, to);
    }

    /**
     * Parses a date-time string using the standard format.
     *
     * @param input The raw date-time string.
     * @param field The field name used for error reporting.
     * @return Parsed LocalDateTime.
     * @throws RagebaitException If parsing fails due to invalid format.
     */
    private LocalDateTime parseDateTime(String input, String field)
            throws RagebaitException {
        try {
            return LocalDateTime.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RagebaitException(
                    "Invalid " + field + " datetime. Use "
                            + DATE_TIME_PATTERN
                            + ". Not whatever that was."
            );
        }
    }
}
