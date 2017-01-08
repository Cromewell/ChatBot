package bot;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jo on 30.12.2016.
 * Holds the responses and the known vocabulary of the bot.
 * As list, even if it holds only one word, to make it easy to add words.
 */
class Phrases {
    //responses
    static final ArrayList<String> GREETINGS = new ArrayList<>(Arrays.asList("Hello, %user%!", "Hey, %user% :)", "Hi there!", "Nice to meet you, %user%!"));
    static final ArrayList<String> ANSWERS_TO_HOW_ARE_YOU = new ArrayList<>(Arrays.asList("I'm fine :)", "Just a normal day :)", "Better than ever!", "Pretty good!"));
    static final ArrayList<String> ANSWERS_TO_WHAT_ARE_YOU_DOING = new ArrayList<>(Arrays.asList("I'm writing with you ;)", "Doing a little math while writing with you..", "Writing!", "Just what I'm supposed to do ;p"));
    static final ArrayList<String> GOODBYES = new ArrayList<>(Arrays.asList("Have a nice day, %user%!", "Ciao, %user% :)", "See you, %user%!", "Was nice to meet you, %user%!"));
    static final ArrayList<String> NO_PROBLEM = new ArrayList<>(Arrays.asList("No Problem, %user%", "You're welcome!", "Pleasure :)!", ":)"));
    static final ArrayList<String> LAUGHING = new ArrayList<>(Arrays.asList("Hahahaha", "MUHAHAHAHAHA!!", ":D!", "Hehe ^.^"));
    static final ArrayList<String> JOKES = new ArrayList<>(Arrays.asList("I dreamt I was forced to eat a giant marshmallow.\n  When I woke up, my pillow was gone.", "Two Elephants meet a totally naked guy.\n  After a while one elephant says to the other:\n  “I really don’t get how he can feed himself with that thing!”", "My dog used to chase people on a bike a lot.\n  It got so bad, finally I had to take his bike away.",
            "Oh darling, since you’ve started dieting,\n  you’ve become such a passionate kisser…\n\n  What do you mean, passionate?\n  I’m looking for food remains!", "I'd like to buy a new boomerang please.\n  Also, can you tell me how to throw the old one away?", "When my wife starts to sing\n  I always go out and do some garden work\n  so our neighbors can see there's no domestic violence going on."));
    static final ArrayList<String> BOTS_AGE = new ArrayList<>(Arrays.asList("My creator first began to work on me on the 30.12.2016"));
    static final ArrayList<String> ANSWERS_TO_CONFIRMATIONS = new ArrayList<>(Arrays.asList("Lalala :)", "~~", "~~~", "~~~~~", "~~~~~~~", "~~~~~~~~~~~"));
    static final ArrayList<String> WISE_WORDS = new ArrayList<>(Arrays.asList( "Cogito ergo sum...", "Astonishment is the root of philosophy.", "When one teaches, two learn.", "Gratitude is the sign of noble souls", "You are what you are.", "When all are wrong, everyone is right.", "There is no wealth but life.", "Poverty is the mother of crime.", "Nobody ever drowned in his own sweat.", "Love is friendship set on fire.",
            "Bad literature is a form of treason", "A grownup is a child with layers on.", "If we don't end war, war will end us.", "All great achievements require time."));

    static final ArrayList<String> DIDNT_UNDERSTAND = new ArrayList<>(Arrays.asList("Sorry, I don't understand :s", "What?", "Didn't get it, sry :/", "I don't know, what to answer.. :s", "o.O?"));

    //vocabulary
    static final ArrayList<String> HELLO = new ArrayList<>(Arrays.asList("hi", "hello", "hey", "nice to meet you"));
    static final ArrayList<String> HOW_ARE_YOU = new ArrayList<>(Arrays.asList("how are you"));
    static final ArrayList<String> WHAT_ARE_YOU_DOING = new ArrayList<>(Arrays.asList("what are you doing", "what r u doin", "what are you doin", "what r you doin", "what are u doin", "what're u doin", "what're you doin"));
    static final ArrayList<String> EXIT = new ArrayList<>(Arrays.asList("exit", "goodbye", "ciao", "bye", "i am out"));
    static final ArrayList<String> THANKS = new ArrayList<>(Arrays.asList("thank you", "ty", "thanks", "thx"));
    static final ArrayList<String> LOL = new ArrayList<>(Arrays.asList("lol", "lool", "haha", "lel", "lul", "hehe"));
    static final ArrayList<String> TELL_ME_A_JOKE = new ArrayList<>(Arrays.asList("tell me a joke", "more jokes"));
    static final ArrayList<String> HOW_OLD_ARE_YOU = new ArrayList<>(Arrays.asList("how old are you", "when were you born", "what is your age", "what's your age"));
    static final ArrayList<String> CONFIRMATIONS = new ArrayList<>(Arrays.asList("nice", "okay", "that was funny", "cool", "wow", "that is good", "that's good", "thats good", "true", "yeah", "yes", "np", "good"));
    static final ArrayList<String> NEED_OF_WISDOM = new ArrayList<>(Arrays.asList("tell me wise words", "wise words", "what is wisdom", "do you have an advise", "advise", "wisdom", "if i had wisdom"));

    static final ArrayList<String> WHAT_TIME_IS_IT = new ArrayList<>(Arrays.asList("what time is it", "what is the time", "what's the time"));

}
