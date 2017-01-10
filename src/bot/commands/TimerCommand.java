package bot.commands;

import javafx.scene.media.AudioClip;

import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
 * Created by Jo on 07.01.2017.
 * <p>
 * Timer command: valid form: "timer 00:01:10" -> timer hh:mm:ss
 */
public class TimerCommand extends Command {

    private long sleepTimeInMillis;

    public TimerCommand() {
    }

    @Override
    public String getTrigger() {
        return "timer";
    }

    /**
     * @param s String to check whether it is the timer command or not.
     * @return true if yes, otherwise false.
     */
    @Override
    public boolean isValid(String s) {
        if (s.startsWith(getTrigger() + " ")) {
            s = s.substring(getTrigger().length()).trim();
            Pattern p = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
            if (p.matcher(s).matches()) {
                stringToMillis(s);
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a string into millis and add them to sleepTimeToMillis.
     *
     * @param s A string to convert into millis (in the form "dd:dd:dd" (d for digit).
     */
    private void stringToMillis(String s) {
        String[] tmp = s.split(":");
        for (int i = 0; i < tmp.length; i++) {
            switch (i) {
                case 0:
                    sleepTimeInMillis += Integer.parseInt(tmp[0]) * 60 * 60 * 1000; //hours to millis
                    break;
                case 1:
                    sleepTimeInMillis += Integer.parseInt(tmp[1]) * 60 * 1000; //minutes to millis
                    break;
                case 2:
                    sleepTimeInMillis += Integer.parseInt(tmp[2]) * 1000; //seconds to millis
                    break;
            }
        }
    }

    /**
     * Executes the command timer. Sleeps for a various time and after this, play a sound.
     */
    @Override
    public void execute() {
        long sleepTime = sleepTimeInMillis;
        sleepTimeInMillis = 0; //reset
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleepTime);
                    AudioClip timerSound = new AudioClip(getClass().getResource("/res/timer.wav").toURI().toString());
                    timerSound.play();
                } catch (InterruptedException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
