package bot.commands;

import bot.utils.WebUtils;

/**
 * Created by Jo on 09.01.2017.
 * Wikipedia command: valid form: "wikipedia words to wikipedia search for" -> "words to wikipedia search for" is the search text.
 */
public class WikipediaCommand extends Command{

    private String searchFor;

    public WikipediaCommand() {
    }

    @Override
    public String getTrigger() {
        return "wikipedia";
    }

    @Override
    public boolean isValid(String s) {
        if(s.startsWith(getTrigger()+" ")){
            s = s.substring(getTrigger().length()).trim();
            searchFor = WebUtils.adjustSearchText(s);
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        WebUtils.openURL("https://en.wikipedia.org/wiki/"+searchFor);
    }
}