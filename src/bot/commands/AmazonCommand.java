package bot.commands;

import bot.utils.WebUtils;

/**
 * Created by Jo on 09.01.2017.
 * Opens amazon in the browser with the searching results for the search parameter.
 * Amazon command: valid form: "amazon words to amazon search for" -> "words to amazon search for" is the search text.
 */
public class AmazonCommand extends Command{

    private String searchFor;
    private String country;

    public AmazonCommand() {
    }

    @Override
    public String getTrigger() {
        return "amazon";
    }

    @Override
    public boolean isValid(String s) {
        if(s.startsWith(getTrigger()+" de ")||s.startsWith(getTrigger()+" com ")){
            s = s.substring(getTrigger().length()).trim();
            if(s.startsWith("de ")){
                country = "de";
                s = s.substring(country.length()).trim();
            }else{
                country = "com";
                s = s.substring(country.length()).trim();
            }
            searchFor = WebUtils.adjustSearchText(s);
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        WebUtils.openURL("https://www.amazon."+country+"/s/ref=nb_sb_noss_2?__mk_de_DE=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords="+searchFor);
    }
}