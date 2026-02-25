package ragebait.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;

/**
 * Represents a dialog box in the Ragebait GUI.
 * Each dialog box contains a speaker's display picture and a text label or formatted TextFlow.
 * The dialog box can be flipped so that the image appears on the left (for bot responses)
 * or on the right (for user input).
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    // ----------------- CONSTRUCTORS -----------------

    /**
     * Private constructor for a standard text message dialog box.
     * Loads the FXML layout and sets the text and display image.
     *
     * @param text the message to display in the dialog box
     * @param img the display picture of the speaker
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Private constructor for a dialog box with a formatted TextFlow,
     * such as color-coded error messages.
     * The TextFlow is styled and arranged with the image.
     *
     * @param flow the TextFlow containing formatted text
     * @param img the display picture of the speaker
     */
    private DialogBox(TextFlow flow, Image img) {
        // AI Assisted. Used ChatGPT to generate this constructor
        // to highlight error messages to let user see the structue easily.
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // clear the HBox children to control order manually
        getChildren().clear();
        flow.getStyleClass().add("text-flow-bubble");
        flow.setPrefHeight(Region.USE_COMPUTED_SIZE);
        flow.setMinHeight(Region.USE_PREF_SIZE);
        flow.setMaxHeight(Region.USE_PREF_SIZE);
        // Add image first, then TextFlow (image left, text right)
        getChildren().addAll(flow, displayPicture);
        // Set image
        displayPicture.setImage(img);
    }

    // ----------------- STATIC FACTORY METHODS -----------------

    /**
     * Creates a dialog box for user messages with the image on the right.
     *
     * @param text the message text
     * @param img the user's display picture
     * @return a DialogBox instance representing the user's message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Ragebait bot messages with the image on the left.
     *
     * @param text the message text
     * @param img the bot's display picture
     * @return a DialogBox instance representing the bot's message
     */
    public static DialogBox getRagebaitDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates a dialog box for formatted error messages with the image on the left.
     *
     * @param flow the formatted TextFlow (e.g., color-coded error)
     * @param img the bot's display picture
     * @return a DialogBox instance representing the error message
     */
    public static DialogBox getErrorDialog(TextFlow flow, Image img) {
        DialogBox db = new DialogBox(flow, img);
        db.flip();
        return db;
    }

    // ----------------- HELPER METHODS -----------------

    /**
     * Flips the dialog box horizontally.
     * Used for bot messages so that the image appears on the left.
     * The alignment is set to top left, and the dialog label is styled.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }
}
