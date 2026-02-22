package ragebait.exception;

/**
 * A custom exception class for handling errors specific to the Ragebait application.
 * This class extends {@link Exception} and is used to represent errors that occur
 * within the Ragebait application.
 *
 * The {@code RagebaitException} allows you to throw exceptions with a detailed error
 * message.
 */
public class RagebaitException extends Exception {

    /**
     * Constructs a new {@code RagebaitException} with the specified detail message.
     * The detail message is a string that provides more information about the exception.
     *
     * @param message the detail message which is saved for later retrieval by the
     *                {@link #getMessage()} method.
     */
    public RagebaitException(String message) {
        super(message); // Call the constructor of the superclass (Exception)
    }

    public RagebaitException(String message, Throwable cause) {
        super(message, cause);
    }
}
