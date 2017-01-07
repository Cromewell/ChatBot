package bot.commands;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
 * Created by Jo on 07.01.2017.
 *
 * valid form: "timer 00:01:10" -> timer hh:mm:ss
 */
public class TimerCommand extends Command{

    private long sleepTimeInMillis;

    public TimerCommand(String trigger) {
        super(trigger);
    }

    @Override
    public String getTrigger() {
        return super.getTrigger();
    }

    @Override
    public boolean isValid(String s) {
        if(s.startsWith(getTrigger()+" ")){
            s = s.substring(getTrigger().length()).trim();
            Pattern p = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
            if(p.matcher(s).matches()){
                String[] tmp = s.split(":");
                for(int i  = 0; i < tmp.length; i++){
                    switch(i) {
                        case 0:
                            sleepTimeInMillis += Integer.parseInt(tmp[0]) * 60 * 60 * 1000;
                            break;
                        case 1:
                            sleepTimeInMillis += Integer.parseInt(tmp[1]) * 60 * 1000;
                            break;
                        case 2:
                            sleepTimeInMillis += Integer.parseInt(tmp[2]) * 1000;
                            break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        long sleepTime = sleepTimeInMillis;
        sleepTimeInMillis = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(sleepTime);
                    Thread.sleep(sleepTime);
                    Media sound = new Media(getClass().getResource("/res/timer.wav").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                } catch (InterruptedException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
