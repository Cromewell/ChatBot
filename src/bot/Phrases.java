package bot;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jo on 30.12.2016.
 * Holds the responses and the known vocabulary of the bot.
 */
class Phrases {
    //responses
    static final ArrayList<String> GREETINGS = new ArrayList<>(Arrays.asList("Hello, %user%!", "Hey, %user% :)", "Hi there!", "Nice to meet you, %user%!"));
    static final ArrayList<String> GOODBYES = new ArrayList<>(Arrays.asList("Have a nice day, %user%!", "Ciao, %user% :)", "See you, %user%!", "Was nice to meet you, %user%!"));
    static final ArrayList<String> ANSWERS_TO_HOW_ARE_YOU = new ArrayList<>(Arrays.asList("I'm fine :)", "Just a normal day :)", "Better than ever!", "Pretty good!"));
    static final ArrayList<String> ANSWERS_TO_WHAT_ARE_YOU_DOING = new ArrayList<>(Arrays.asList("I'm writing with you ;)", "Doing a little math while writing with you..", "Writing!", "Just what I'm supposed to do ;p"));
    static final ArrayList<String> DIDNT_UNDERSTAND = new ArrayList<>(Arrays.asList("Sorry, I don't understand :s", "What?", "Didn't get it, sry :/", "I don't know, what to answer.. :s", "o.O?"));
    static final ArrayList<String> NO_PROBLEM = new ArrayList<>(Arrays.asList("No Problem, %user%", "You're welcome!", "Pleasure :)!", ":)"));
    static final ArrayList<String> LAUGHING = new ArrayList<>(Arrays.asList("Hahahaha", "MUHAHAHAHAHA!!", ":D!", "Hehe ^.^"));

    //vocabulary
    static final ArrayList<String> HOW_ARE_YOU = new ArrayList<>(Arrays.asList("how are you", "how r u", "how r you", "how are u", "how're you", "how're u"));
    static final ArrayList<String> WHAT_ARE_YOU_DOING = new ArrayList<>(Arrays.asList("what are you doing", "what r u doing", "what r u doin", "what are u doing", "what r you doing", "what are you doin", "what r you doin", "what are u doin", "what're you doing", "what're u doing", "what're u doin", "what're you doin"));
    static final ArrayList<String> EXIT = new ArrayList<>(Arrays.asList("exit", "goodbye", "ciao", "bye", "im out", "i'm out"));
    static final ArrayList<String> WHAT_TIME_IS_IT = new ArrayList<>(Arrays.asList("what time is it", "what is the time", "what's the time"));
    static final ArrayList<String> LOL = new ArrayList<>(Arrays.asList("lol", "lool", "haha", "lel", "lul", "hehe"));

}
