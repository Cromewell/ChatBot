package bot.utils;

import javafx.scene.control.TextInputDialog;

/**
 * Created by Jo on 14.01.2017.
 * Window utils.
 */
public class WindowUtils {

    /**
     * Builds a dialog window.
     *
     * @param title            Title of the window.
     * @param header           Header text of the window.
     * @param defaultInputText Default input field text.
     * @return User input.
     */
    public static String buildTextDialog(String title, String header, String defaultInputText) {
        TextInputDialog dialog = new TextInputDialog(defaultInputText);
        dialog.setTitle(title);
        dialog.setGraphic(null);
        dialog.setHeaderText(header);
        dialog.showAndWait();
        return dialog.getResult();
    }
}
