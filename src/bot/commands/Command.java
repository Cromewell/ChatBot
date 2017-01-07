package bot.commands;

/**
 * Created by Jo on 07.01.2017.
 *
 */
public abstract class Command {

    private String trigger;

    public Command(String trigger) {
        this.trigger = trigger;
    }

    public String getTrigger(){
        return trigger;
    }

    public abstract boolean isValid(String s);

    public abstract void execute();

}
