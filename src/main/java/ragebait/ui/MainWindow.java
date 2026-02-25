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
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import ragebait.Ragebait;
import ragebait.exception.RagebaitException;

/**
 * Controller class for the main GUI window of Ragebait.
 * Handles user interactions, displays dialog boxes for user and Ragebait messages,
 * manages the welcome message, and handles application exit behavior with a fade-out effect.
 */
public class MainWindow extends AnchorPane {

    /** Command string to exit the application */
    private static final String BYE = "bye";

    /** Scroll pane containing the dialog messages */
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

    /** Rectangle used for fade-out effect when exiting the application */
    @FXML
    private Rectangle fadeOverlay;

    /** Reference to the Ragebait chatbot logic */
    private Ragebait ragebait;

    /** Image representing the user in the dialog boxes */
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/angry.png"));

    /** Image representing Ragebait in the dialog boxes */
    private final Image ragebaitImage = new Image(this.getClass().getResourceAsStream("/images/troll.png"));

    /**
     * Initializes the main window after FXML is loaded.
     * Binds the scroll pane's vertical value to the height of the dialog container
     * so that it automatically scrolls to the latest message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Ragebait chatbot instance for this controller.
     * Also displays the welcome message when the application starts.
     *
     * @param ragebait the Ragebait chatbot instance to be used
     */
    public void setRagebait(Ragebait ragebait) {
        this.ragebait = ragebait;
        String welcomeMessage = ragebait.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getRagebaitDialog(welcomeMessage, ragebaitImage));
    }

    /**
     * Handles user input when the send button is pressed or Enter is hit.
     * Displays the user's message and Ragebait's response in dialog boxes.
     * If an exception occurs, displays a color-coded error message instead.
     * Clears the input field after processing.
     * If the user types the exit command, disables input and plays a fade-out animation
     * before closing the application.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = ragebait.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getRagebaitDialog(response, ragebaitImage)
            );
        } catch (RagebaitException e) {
            TextFlow formattedError = ErrorMessageFormatter.format(e.getMessage());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(formattedError, ragebaitImage)
            );
        }

        userInput.clear();
        String trimmed = input.trim();
        String[] parts = trimmed.split("\\s+");

        if (parts[0].equals(BYE)) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            // AI Assisted: Used ChatGPT to get fade transition to black before exiting
            FadeTransition fade = new FadeTransition(Duration.seconds(2.5), fadeOverlay);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setOnFinished(e -> Platform.exit());
            fade.play();
        }
    }
}
