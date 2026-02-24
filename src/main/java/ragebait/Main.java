package ragebait;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ragebait.ui.MainWindow;

/**
 * Main JavaFX application class for the Ragebait chatbot GUI.
 *
 * Initializes the GUI using FXML and injects a Ragebait instance
 * into the controller to handle user input and responses.
 */
public class Main extends Application {

    /** Core Ragebait application instance used by the GUI */
    private final Ragebait ragebait = new Ragebait();

    /**
     * Entry point for the JavaFX application.
     * Loads the FXML layout, sets up the scene, and displays the stage.
     *
     * @param stage Primary stage provided by JavaFX
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Ragebait Chatbot");

            // Inject the Ragebait instance into the controller
            MainWindow controller = fxmlLoader.getController();
            controller.setRagebait(ragebait);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
