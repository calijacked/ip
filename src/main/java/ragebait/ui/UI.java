package ragebait.ui;
import javafx.application.Application;

import java.util.Scanner;

/**
 * Handles all user interactions in the Ragebait application.
 * Responsible for reading input from the user and displaying messages to the console.
 */
public class UI {
    /** Scanner for reading user input */
    private final Scanner sc;

    /**
     * Constructs a UI object and initializes the input scanner.
     */
    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello Fatty! I'm Ragebait! What can I do for you? :)");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a horizontal line separator in the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads a line of input from the user, trimming leading and trailing whitespace.
     *
     * @return User input as a trimmed String.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Closes the input scanner. Should be called when the application is exiting.
     */
    public void close() {
        sc.close();
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message Message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
