package ragebait.exception;

/**
 * Custom checked exception for the Ragebait application.
 *
 * Thrown whenever the user messes up input, or when the application
 * encounters a situation it cannot handle. Each exception carries
 * a message that can be displayed to the user in a rage-flavored tone.
 */
public class RagebaitException extends Exception {

    /**
     * Constructs a RagebaitException with the specified error message.
     *
     * @param message The detail message explaining why Ragebait is mad at the user.
     */
    public RagebaitException(String message) {
        super(message);
    }

    /**
     * Constructs a RagebaitException with a message and a root cause.
     *
     * @param message The detail message explaining the rage-worthy problem.
     * @param cause   The underlying exception that triggered this rage.
     */
    public RagebaitException(String message, Throwable cause) {
        super(message, cause);
    }
}
