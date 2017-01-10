package bot.commands;

import bot.utils.WebUtils;

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

    public GoogleCommand(){}

    @Override
    public String getTrigger(){
        return "google";
    }

    /**
     * Validates the input.
     * @param s    String to validate.
     * @return    valid or not.
     */
    @Override
    public boolean isValid(String s){
        if(s.startsWith(getTrigger()+" ")){
            s = s.substring(getTrigger().length()).trim();
            searchFor = WebUtils.adjustSearchText(s);
            return true;
        }
        return false;
    }

    /**
     * Searches the string the user added as parameter.
     */
    @Override
    public void execute(){
        WebUtils.openURL("https://www.google.de/webhp#q="+searchFor);
    }
}
