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

/**
 * Represents a dialog box in the Ragebait GUI.
 * Each dialog box contains a speaker's display picture and a text label.
 * Can be flipped so that the image appears on the left (for Ragebait responses)
 * or on the right (for user input).
 */
public class DialogBox extends HBox {

    /** Label containing the dialog text */
    @FXML
    private Label dialog;

    /** ImageView representing the speaker's face */
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor that loads the FXML layout and initializes the dialog box.
     *
     * @param text Text content of the dialog
     * @param img Image representing the speaker
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
     * Flips the dialog box horizontally so that the ImageView is on the left
     * and the text label is on the right. Used for Ragebait messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box representing user input.
     * Image is displayed on the right, text on the left.
     *
     * @param text Text of the user message
     * @param img Image representing the user
     * @return Configured DialogBox instance
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box representing Ragebait's response.
     * Image is displayed on the left, text on the right.
     *
     * @param text Text of the Ragebait message
     * @param img Image representing Ragebait
     * @return Configured DialogBox instance
     */
    public static DialogBox getRagebaitDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
