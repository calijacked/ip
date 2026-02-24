package ragebait;

import javafx.application.Application;

/**
 * Launcher class for the Ragebait application.
 *
 * This class exists to work around JavaFX classpath issues when
 * launching the application. It simply delegates to the Main class.
 */
public class Launcher {

    /**
     * Main entry point for launching the Ragebait JavaFX application.
     *
     * @param args Command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
