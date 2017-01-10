package bot.utils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Jo on 09.01.2017.
 * Holds a handful of utils for adjusting urls and so on.
 */
public class WebUtils {

    /**
     * Makes the string searchable.
     *
     * @param s String to adjust.
     * @return Adjusted string.
     */
    public static String adjustSearchText(String s) {
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
     * @param url Opens this string as url in the default browser.
     */
    public static void openURL(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
