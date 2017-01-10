package bot;

import bot.commands.*;
import bot.utils.FileUtils;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Jo on 30.12.2016.
 * Bot class manages the gui input and updates the gui.
 */
class ChatBot {

    //TODO:
    //-Save amount of each name chatted with? -> so, for instance: "chatted with 12 Johns already! Was one of them you?"
    //-extend voc
    //-learn function?
    //-databank?
    //-more commands: schedule-book, weather(https://www.wunderground.com/weather/api/), calc, spotify (https://github.com/thelinmichael/spotify-web-api-java), contact
    //-custom scripts for extending voc?

    private String userName;
    private TextArea chat;
    private TextField inputField;
    private ImageView face;
    private PauseTransition pause;
    private final String botName = "Kurt";
    //following longs measured in milliseconds
    private static final long WRITE_TIME = 500;
    private static final long THINKING_TIME = 1700;
    private static final long FACE_TIME = 3000;
    //commands
    private final Command[] cmds = {new TimerCommand(), new GoogleCommand(), new AmazonCommand(), new WikipediaCommand()}; //array of commands
    private Learner learner;

    ChatBot(TextArea chat, TextField inputField, Button send, ImageView face) throws InterruptedException {
        this.chat = chat;
        this.inputField = inputField;
        this.face = face;

        //initalize learner:
        FileUtils.createFiles();
        learner = new Learner(new File("vocabulary.txt"));

        /*
          Reads the inputfield when send is clicked.
          Appends the message, if its length is > 0 and clears the textfield.
         */
        send.setOnAction(e -> {
            String in = readInput();
            try {
                if (in.length() > 0) {
                    chat.appendText(userName + ": " + in + "\n");
                    handleInput(in);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            inputField.clear();
        });
        //enable pressing enter to send message:
        inputField.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.ENTER) {
                send.fire();
            }
        });
        chat.appendText(botName + ": What's your name? :)\n");

        //Get username:
        TextInputDialog dialog = new TextInputDialog("Name");
        dialog.setTitle("What's your name?");
        dialog.setGraphic(null);
        dialog.setHeaderText("Please enter your name :)");
        dialog.showAndWait();
        userName = dialog.getResult();

        chat.appendText(botName + ": Ok, your name is " + userName + "? Just making a note...\n");
        pause = new PauseTransition(Duration.millis(THINKING_TIME));
        pause.setOnFinished(e -> greetUser());
        pause.play();
    }

    private void greetUser() {
        Collections.shuffle(Phrases.GREETINGS);
        chat.appendText(botName + ": " + Phrases.GREETINGS.get(0).replace("%user%", userName) + "\n");
        pause.setOnFinished(null);
    }

    /**
     * Reads from the textfield.
     *
     * @return The textfields text.
     */
    private String readInput() {
        return inputField.getText();
    }

    /**
     * Handles the user input string by checking for matches in the bot vocabulary and then answers the user.
     *
     * @param input String to handle.
     * @throws InterruptedException Failure..
     */
    private void handleInput(String input) throws InterruptedException {
        input = adjustEnglish(input.toLowerCase());

        pause.setDuration(Duration.millis(WRITE_TIME));
        pause.setOnFinished(null); //reset

        //check if input is a command
        for (Command cmd : cmds) {
            if (cmd.isValid(input)) {
                cmd.execute();
                return;
            }
        }
        //end checking

        if (input.equals("/learn")) {
            learn();
            return;
        } else if ((matches(input, Phrases.HOW_ARE_YOU))) {
            answer(Phrases.ANSWERS_TO_HOW_ARE_YOU);
        } else if (matches(input, Phrases.WHAT_ARE_YOU_DOING)) {
            answer(Phrases.ANSWERS_TO_WHAT_ARE_YOU_DOING);
        } else if (matches(input, Phrases.EXIT)) {
            answer(Phrases.GOODBYES);
            pause.setDuration(Duration.millis(THINKING_TIME));
            Platform.runLater(() -> face.setImage(new Image(getClass().getResource("/res/sad.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
            pause.setOnFinished(e -> System.exit(0));
        } else if (matches(input, Phrases.WHAT_TIME_IS_IT)) {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            pause.setOnFinished(e -> chat.appendText(botName + ": It is " + df.format(new Date()) + "\n"));
        } else if (matches(input, Phrases.THANKS)) {
            answer(Phrases.NO_PROBLEM);
        } else if (input.equals("why?")) {
            pause.setOnFinished(e -> chat.appendText(botName + ": why why?\n"));
            pause.play();
        } else if (input.equals("why why why?")) {
            pause.setOnFinished(e -> chat.appendText(botName + ": why why why why?\n"));
        } else if (matches(input, Phrases.LOL)) {
            answer(Phrases.LAUGHING);
            pause.setDuration(Duration.millis(FACE_TIME));
            Platform.runLater(() -> face.setImage(new Image(getClass().getResource("/res/laughing.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
            pause.setOnFinished(e -> face.setImage(new Image(getClass().getResource("/res/smile.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
        } else if (matches(input, Phrases.TELL_ME_A_JOKE)) {
            answer(Phrases.JOKES);
            pause.setDuration(Duration.millis(FACE_TIME));
            Platform.runLater(() -> face.setImage(new Image(getClass().getResource("/res/joking.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
            pause.setOnFinished(e -> face.setImage(new Image(getClass().getResource("/res/smile.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
        } else if (matches(input, Phrases.HOW_OLD_ARE_YOU)) {
            answer(Phrases.BOTS_AGE);
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            int day = Integer.parseInt(df.format(new Date()).substring(0, 2));
            int month = Integer.parseInt(df.format(new Date()).substring(3, 5));
            int yearsAlive = Integer.parseInt(df.format(new Date()).substring(6, 10)) - 2016;
            if (month <= 12 && day < 30) {
                yearsAlive--;
            }
            int finalYears = yearsAlive;
            pause.setOnFinished(e -> chat.appendText("  So I'm " + finalYears + "\n"));
        } else if (matches(input, Phrases.HELLO)) {
            answer(Phrases.GREETINGS);
        } else if (matches(input, Phrases.CONFIRMATIONS)) {
            answer(Phrases.ANSWERS_TO_CONFIRMATIONS);
        } else if (matches(input, Phrases.NEED_OF_WISDOM)) {
            answer(Phrases.WISE_WORDS);
            pause.setDuration(Duration.millis(FACE_TIME));
            Platform.runLater(() -> face.setImage(new Image(getClass().getResource("/res/wise.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
            pause.setOnFinished(e -> face.setImage(new Image(getClass().getResource("/res/smile.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
        } else if (matches(input, Phrases.CUSTOM_VOCABULARY)) {
            int index = Phrases.CUSTOM_VOCABULARY.indexOf(input);
            pause.setOnFinished(e -> chat.appendText(botName + ": " + Phrases.CUSTOM_RESPONSES.get(index) + "\n"));
        } else {
            pause.setDuration(Duration.millis(THINKING_TIME));
            Platform.runLater(() -> face.setImage(new Image(getClass().getResource("/res/confused.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false)));
            pause.setOnFinished(e -> {
                answer(Phrases.DIDNT_UNDERSTAND);
                face.setImage(new Image(getClass().getResource("/res/smile.png").toString(), face.getFitWidth(), face.getFitHeight(), false, false));
            });
        }
        pause.play();
    }

    /**
     * Lower the amount of combinations by spelling the abbreviations in full.
     *
     * @param input String to check.
     * @return Words fully spelled.
     */
    private String adjustEnglish(String input) {
        input = input.replaceAll("[!?.,]", "");
        if (input.contains(" u ")) {
            input = input.replace(" u ", " you ");
        }
        if (input.contains(" r ")) {
            input = input.replace(" r ", " are ");
        }
        if (input.endsWith(" u")) {
            input = input.substring(0, input.lastIndexOf(" u"));
            input = input + " you";
        }
        if (input.contains("'re ")) {
            input = input.replace("'re ", " are ");
        }
        if (input.contains(" i'm ")) {
            input = input.replace(" i'm ", "i am ");
        }
        if (input.startsWith("im ")) {
            input = input.substring(3);
            input = "i am " + input;
        }
        return input;
    }

    /**
     * Checks whether the given string is in the bots vocabulary or not.
     *
     * @param string  String to match.
     * @param strings The bots vocabulary.
     * @return true, if a match was found. Otherwise false.
     */
    private boolean matches(String string, ArrayList<String> strings) {
        for (String s : strings) {
            if (string.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays a random answer out of a suitable list.
     *
     * @param list List with answers.
     */
    private void answer(ArrayList<String> list) {
        Collections.shuffle(list);
        String answer = list.get(0);
        if (answer.contains("%user%")) {
            answer = answer.replace("%user%", userName);
        }
        String finalAnswer = answer;
        PauseTransition pause = new PauseTransition(Duration.millis(WRITE_TIME));
        pause.setOnFinished(e -> chat.appendText(botName + ": " + finalAnswer + "\n"));
        pause.play();
    }

    private void learn() {
        //Get vocabulary:
        TextInputDialog dialog = new TextInputDialog("Vocabulary");
        dialog.setTitle("Which sentence do you want to teach " + botName + "?");
        dialog.setGraphic(null);
        dialog.setHeaderText("Please enter the sentence:");
        dialog.showAndWait();
        String vocabulary = dialog.getResult();
        //Get Response:
        dialog = new TextInputDialog("Response");
        dialog.setTitle("What should " + botName + " response?");
        dialog.setGraphic(null);
        dialog.setHeaderText("Please enter the sentence:");
        dialog.showAndWait();
        String response = dialog.getResult();
        learner.learnSentence(vocabulary, response);
    }
}
