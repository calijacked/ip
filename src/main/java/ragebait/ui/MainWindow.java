package ragebait.ui;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ragebait.Ragebait;

/**
 * Controller class for the main GUI window of Ragebait.
 * Handles user interactions, displays dialog boxes for user and Ragebait messages,
 * and manages the welcome message and application exit behavior.
 */
public class MainWindow extends AnchorPane {

    /** Command string to exit the application */
    private static final String BYE = "bye";

    /** Scroll pane containing dialog messages */
    @FXML
    private ScrollPane scrollPane;

    /** Container for all dialog boxes */
    @FXML
    private VBox dialogContainer;

    /** Text field for user input */
    @FXML
    private TextField userInput;

    /** Button to send user input */
    @FXML
    private Button sendButton;

    /** Rectangle used for fade-out effect on exit */
    @FXML
    private Rectangle fadeOverlay;

    /** Reference to the Ragebait chatbot logic */
    private Ragebait ragebait;

    /** Image representing the user */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/angry.png"));

    /** Image representing Ragebait */
    private final Image ragebaitImage = new Image(this.getClass().getResourceAsStream("/images/troll.png"));

    /**
     * Initializes the main window after FXML is loaded.
     * Binds the scroll pane to the height of the dialog container so it scrolls automatically.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Ragebait instance into this controller.
     * Also displays the welcome message when the application starts.
     *
     * @param ragebait The Ragebait chatbot instance
     */
    public void setRagebait(Ragebait ragebait) {
        this.ragebait = ragebait;
        String welcomeMessage = ragebait.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getRagebaitDialog(welcomeMessage, ragebaitImage));
    }

    /**
     * Handles user input when the send button is pressed or Enter is hit.
     * Displays the user's message and Ragebait's response in dialog boxes.
     * Clears the input field after processing.
     * If the user types the exit command, disables input and plays a fade-out animation before closing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ragebait.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRagebaitDialog(response, ragebaitImage)
        );

        userInput.clear();

        if (input.equals(BYE)) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            FadeTransition fade = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setOnFinished(e -> Platform.exit());
            fade.play();
        }
    }
}
