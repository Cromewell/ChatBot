package bot.commands;

import bot.utils.WebUtils;

/**
 * Created by Jo on 08.01.2017.
 * Google command: valid form: "google words to google for" -> "words to google for" is the search text.
 */
public class GoogleCommand extends Command {

    private String searchFor;

    public GoogleCommand() {
    }

    @Override
    public String getTrigger() {
        return "google";
    }

    /**
     * Validates the input.
     *
     * @param toValidate String to validate.
     * @return valid or not.
     */
    @Override
    public boolean isValid(String toValidate) {
        if (toValidate.startsWith(getTrigger() + " ")) {
            toValidate = toValidate.substring(getTrigger().length()).trim();
            searchFor = WebUtils.adjustSearchText(toValidate);
            return true;
        }
        return false;
    }

    /**
     * Searches the string the user added as parameter.
     */
    @Override
    public void execute() {
        WebUtils.openURL("https://www.google.de/webhp#q=" + searchFor);
    }
}
