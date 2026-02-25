// AI Assisted. Used ChatGPT to generate a formatter to highlight error messages to let user see the structue easily.
package ragebait.ui;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Provides utility to convert a plain error message into a styled TextFlow.
 *
 * This class highlights different parts of the message according to rules:
 * - Commands (/name, /phone, /email, /from, /to, /by) are highlighted in orange.
 * - Keywords like "contact", "deadline", "event" are highlighted in green.
 * - Dates, times, email hints, and similar patterns are highlighted in blue.
 * - Numbers (like "8") and other error words are highlighted in red.
 *
 * The output TextFlow can be directly used in JavaFX UI components.
 */
public class ErrorMessageFormatter {

    /**
     * Converts a plain text error message into a TextFlow with color-coded words.
     * Each word is checked against specific rules to determine its highlight:
     * - Commands stay orange.
     * - Keywords are green.
     * - Date/time/email patterns are blue.
     * - Numbers or default error words are red.
     *
     * Trailing punctuation such as periods, commas, semicolons, and colons
     * are always colored red and separated from the main word.
     *
     * @param message the error message as a plain string
     * @return a TextFlow containing the message with colored highlights
     */
    public static TextFlow format(String message) {
        TextFlow flow = new TextFlow();

        String[] words = message.split(" ");

        for (String word : words) {
            String punctuation = "";
            String mainWord = word;

            // Separate trailing punctuation like . , ; :
            if (word.matches(".*[.,;:]$")) {
                int lastChar = word.length() - 1;
                punctuation = String.valueOf(word.charAt(lastChar));
                mainWord = word.substring(0, lastChar);
            }

            Text mainText = new Text(mainWord);

            // ---------------- Highlight rules ----------------

            if (mainWord.equals("/by") || mainWord.equals("/from") || mainWord.equals("/to")
                    || mainWord.equals("/name") || mainWord.equals("/phone") || mainWord.equals("/email")) {
                mainText.getStyleClass().add("error-tag"); // orange
            } else if (mainWord.equalsIgnoreCase("contact")
                    || mainWord.equalsIgnoreCase("deadline")
                    || mainWord.equalsIgnoreCase("event")) {
                mainText.getStyleClass().add("error-command"); // green keywords
            } else if (mainWord.contains("d/M/yyyy") || mainWord.contains("HHmm")
                    || mainWord.contains("<") || mainWord.contains(">")
                    || mainWord.contains("@") || mainWord.contains(".com")) {
                mainText.getStyleClass().add("error-datetime"); //blue
            } else if (mainWord.equalsIgnoreCase("eight") || mainWord.equals("8")) {
                mainText.getStyleClass().add("error-datetime");
            } else {
                mainText.getStyleClass().add("error-text");
            }

            flow.getChildren().add(mainText);

            // Punctuation always red
            if (!punctuation.isEmpty()) {
                Text punctText = new Text(punctuation);
                punctText.getStyleClass().add("error-text");
                flow.getChildren().add(punctText);
            }

            // Space after each word
            flow.getChildren().add(new Text(" "));
        }

        return flow;
    }
}
