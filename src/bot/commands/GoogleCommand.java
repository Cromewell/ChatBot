package bot.commands;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Jo on 08.01.2017.
 * Google command: valid form: "google words to google for" -> "words to google for" is the search text.
 */
public class GoogleCommand extends Command{

    private String searchFor;

    public GoogleCommand(String trigger) {
        super(trigger);
    }

    /**
     * Validates the input.
     * @param s    String to validate.
     * @return    valid or not.
     */
    @Override
    public boolean isValid(String s) {
        if(s.startsWith(getTrigger()+" ")){
            s = s.substring(getTrigger().length()).trim();
            searchFor = adjustSearchText(s);
            return true;
        }
        return false;
    }

    /**
     * Makes the string searchable.
     * @param s    String to adjust.
     * @return    Adjusted string.
     */
    private String adjustSearchText(String s) {
        s = s.replace("%", "%25");
        s = s.replace(" ", "%20");
        s = s.replace("!", "%21");
        s = s.replace("\"", "%22");
        s = s.replace("#", "%23");
        s = s.replace("$", "%24");
        s = s.replace("&", "%26");
        s = s.replace("'", "%27");
        s = s.replace("(", "%28");
        s = s.replace(")", "%29");
        s = s.replace("*", "%2A");
        s = s.replace("+", "%2B");
        s = s.replace(",", "%2C");
        s = s.replace("-", "%2D");
        s = s.replace(".", "%2E");
        s = s.replace("/", "%2F");

        return s;
    }

    /**
     * Searches the string the user added as parameter.
     */
    @Override
    public void execute() {
        if(Desktop.isDesktopSupported())
        {
            try {
                Desktop.getDesktop().browse(new URI("https://www.google.de/webhp#q="+searchFor));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
