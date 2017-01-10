package bot.commands;

import bot.utils.WebUtils;

/**
 * Created by Jo on 09.01.2017.
 * Opens amazon in the browser with the searching results for the search parameter.
 * Amazon command: valid form: "amazon countryId words to amazon search for" -> "words to amazon search for" is the search text.
 * CountryId is either "de" or "com" and so is either amazon.de or amazon.com the amazon to search on.
 */
public class AmazonCommand extends Command {

    private String searchFor;
    private String countryId;

    public AmazonCommand() {
    }

    @Override
    public String getTrigger() {
        return "amazon";
    }

    @Override
    public boolean isValid(String toValidate) {
        if (toValidate.startsWith(getTrigger() + " de ") || toValidate.startsWith(getTrigger() + " com ")) {
            toValidate = toValidate.substring(getTrigger().length()).trim();
            if (toValidate.startsWith("de ")) {
                countryId = "de";
                toValidate = toValidate.substring(countryId.length()).trim();
            } else {
                countryId = "com";
                toValidate = toValidate.substring(countryId.length()).trim();
            }
            searchFor = WebUtils.adjustSearchText(toValidate);
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        WebUtils.openURL("https://www.amazon." + countryId + "/s/ref=nb_sb_noss_2?__mk_de_DE=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords=" + searchFor);
    }
}
