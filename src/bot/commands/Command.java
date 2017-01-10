package bot.commands;

/**
 * Created by Jo on 07.01.2017.
 *
 */
public abstract class Command{

    Command(){}

    public abstract String getTrigger();

    public abstract boolean isValid(String s);

    public abstract void execute();

}
