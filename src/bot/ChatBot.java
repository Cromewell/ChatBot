package bot;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Jo on 30.12.2016.
 *
 */
public class ChatBot{

    private String userName;
    private TextArea chat;
    private TextField inputField;
    private Button send;
    private ImageView face;
    private String in = "";
    private String botName = "Alica";
    private long writeTime = 500;
    private long thinkingTime = 2000;
    private PauseTransition pause;

    public ChatBot(TextArea chat, TextField inputField, Button send, ImageView face) throws InterruptedException {
        this.chat = chat;
        this.inputField = inputField;
        this.send = send;
        this.face = face;

        send.setOnAction(e-> {
            in = readInput();
            try {
                if(in.length() > 0) {
                    chat.appendText(userName+": "+in+"\n");
                    handleInput(in);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            inputField.clear();
        });
        chat.appendText(botName+": What's your name? :)\n");

        //Get username
        TextInputDialog dialog = new TextInputDialog("Name");
        dialog.setTitle("What's your name?");
        dialog.setGraphic(null);
        dialog.setHeaderText("Please enter your name :)");
        dialog.showAndWait();
        userName = dialog.getResult();

        chat.appendText(botName+": Ok, your name is "+userName+"? Just making a note...\n");
        pause = new PauseTransition(Duration.millis(thinkingTime));
        pause.setOnFinished(e->greetUser());
        pause.play();
        //TODO
        //Save amount of each name chatted with?
        //so, for instance: "chatted with 12 Jons already! Was one of them you?"
    }

    public void greetUser() {
        Collections.shuffle(Phrases.GREETINGS);
        chat.appendText(botName+": "+Phrases.GREETINGS.get(0).replace("%user%", userName)+"\n");
        pause.setOnFinished(null);
    }

    public String readInput(){
        return inputField.getText();
    }

    private void handleInput(String input) throws InterruptedException {
        input = input.toLowerCase();
        pause.setDuration(Duration.millis(writeTime));

        if(matches(input, Phrases.HOW_ARE_YOU)){
            answer(Phrases.ANSWERS_TO_HOW_ARE_YOU);
        }else if(matches(input, Phrases.WHAT_ARE_YOU_DOING)){
            answer(Phrases.ANSWERS_TO_WHAT_ARE_YOU_DOING);
        }else if(matches(input, Phrases.EXIT)){
            answer(Phrases.GOODBYES);
            pause.setDuration(Duration.millis(thinkingTime));
            Platform.runLater(() -> face.setImage(new Image("res/sad.png", 16*30, 16*30, false, false)));
            pause.setOnFinished(e-> System.exit(0));

        }else if(matches(input, Phrases.WHAT_TIME_IS_IT)){
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            pause.setOnFinished(e->chat.appendText(botName+": It is " + df.format(new Date())+"\n"));
        }else if(input.contains("thank you")|input.contains("ty")|input.contains("thanks")|input.contains("thx")){
            answer(Phrases.NO_PROBLEM);
        }else if(input.equals("why?")){
            pause.setOnFinished(e->chat.appendText(botName+": why why?\n"));
            pause.play();
        }else if(input.equals("why why why?")){
            pause.setOnFinished(e->chat.appendText(botName+": why why why why?\n"));
        }else if(matches(input, Phrases.LOL)){
            answer(Phrases.LAUGHING);
            pause.setDuration(Duration.millis(thinkingTime));
            Platform.runLater(() -> face.setImage(new Image("res/laughing.png", 16*30, 16*30, false, false)));
            pause.setOnFinished(e-> face.setImage(new Image("res/smile.png", 16*30, 16*30, false, false)));
        }else{
            pause.setDuration(Duration.millis(thinkingTime));
            Platform.runLater(() -> face.setImage(new Image("res/confused.png", 16*30, 16*30, false, false)));
            pause.setOnFinished(e->{
                answer(Phrases.DIDNT_UNDERSTAND);
                face.setImage(new Image("res/smile.png", 16*30, 16*30, false, false));
            });
        }
        pause.play();
    }

    private boolean matches(String string, ArrayList<String> strings) {
        for(String s: strings){
            if(string.contains(s)){
                return true;
            }
        }
        return false;
    }

    private void answer(ArrayList<String> list) {
        Collections.shuffle(list);
        String answer = list.get(0);
        if(answer.contains("%user%")){
            answer = answer.replace("%user%", userName);
        }
        String finalAnswer = answer;
        PauseTransition pause = new PauseTransition(Duration.millis(writeTime));
        pause.setOnFinished(e->chat.appendText(botName+": "+ finalAnswer +"\n"));
        pause.play();
    }
}
