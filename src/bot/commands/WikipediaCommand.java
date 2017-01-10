package bot.commands;

import bot.utils.WebUtils;

/**
 * Created by Jo on 09.01.2017.
 * Wikipedia command: valid form: "wikipedia countryId words to wikipedia search for" -> "words to wikipedia search for" is the search text.
 * CountryId is either "de" or "en" and so is either wikipedia.de or wikipedia.en the wikipedia to search on.
 */
public class WikipediaCommand extends Command {

    private String searchFor;
    private String countryId;

    public WikipediaCommand() {
    }

    @Override
    public String getTrigger() {
        return "wikipedia";
    }

    @Override
    public boolean isValid(String toValidate) {
        if (toValidate.startsWith(getTrigger() + " de ") || toValidate.startsWith(getTrigger() + " en ")) {
            toValidate = toValidate.substring(getTrigger().length()).trim();
            if (toValidate.startsWith("de ")) {
                countryId = "de";
                toValidate = toValidate.substring(countryId.length()).trim();
            } else {
                countryId = "en";
                toValidate = toValidate.substring(countryId.length()).trim();
            }
            searchFor = WebUtils.adjustSearchText(toValidate);
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        WebUtils.openURL("https://" + countryId + ".wikipedia.org/wiki/" + searchFor);
    }
}
