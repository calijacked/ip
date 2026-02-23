package ragebait.exception;

/**
 * Represents a custom exception specific to the Ragebait application.
 *
 * <p>This exception is thrown when invalid user input or application-level
 * errors occur. It extends {@link Exception}, making it a checked exception
 * that must be handled or declared.</p>
 */
public class RagebaitException extends Exception {

    /**
     * Constructs a {@code RagebaitException} with a specified error message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public RagebaitException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code RagebaitException} with a specified error message
     * and underlying cause.
     *
     * @param message The detail message explaining the cause of the exception.
     * @param cause   The underlying cause of the exception.
     */
    public RagebaitException(String message, Throwable cause) {
        super(message, cause);
    }
}
