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
 * Anchor Pane Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String BYE = "bye";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Rectangle fadeOverlay;

    private Ragebait ragebait;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/angry.png"));
    private final Image ragebaitImage = new Image(this.getClass().getResourceAsStream("/images/troll.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setRagebait(Ragebait ragebait) {
        this.ragebait = ragebait;
        String welcomeMessage = ragebait.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
            DialogBox.getRagebaitDialog(welcomeMessage, ragebaitImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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
