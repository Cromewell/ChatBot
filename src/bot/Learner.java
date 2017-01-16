package bot;

import bot.utils.FileUtils;
import java.io.File;

/**
 * Created by Jo on 10.01.2017.
 * A learner makes the bot able to learn new sentences and answers.
 */
class Learner {

    private File file;

    /**
     * Loads the Vocabulary on init, if file has content.
     *
     * @param vocFile The vocabulary.txt
     */
    Learner(File vocFile) {
        this.file = vocFile;
        if (!FileUtils.read(file).equals("")) {
            loadSentences();
        }
    }

    /**
     * Writes the given vocabulary and response into the vocabulary.txt file.
     *
     * @param vocabulary Vocabulary to write into file.
     * @param response   Response to write into file.
     */
    void learnSentence(String vocabulary, String response) {
        FileUtils.write(vocabulary + "::" + response, file); //save learned (divided by "::" as marker)
        loadSentences(); //make learned available
    }

    /**
     * Loads the vocabulary.txt file and adds the content to the bots vocabulary.
     */
    private void loadSentences() {
        String sentences = FileUtils.read(file);
        String[] couples = splitSentences(sentences);
        for (String couple : couples) {
            String[] vocabularyAndResponse = couple.split("::");
            addToVoc(vocabularyAndResponse);
        }
    }

    /**
     * Returns the sentences split by each new line.
     *
     * @param sentences Sentence to split.
     * @return Split sentence.
     */
    private String[] splitSentences(String sentences) {
        return sentences.split("\\n");
    }

    /**
     * @param vocabularyAndResponse Adds this vocabulary to the bot.
     */
    private void addToVoc(String[] vocabularyAndResponse) {
        Phrases.CUSTOM_VOCABULARY.add(vocabularyAndResponse[0]);
        Phrases.CUSTOM_RESPONSES.add(vocabularyAndResponse[1]);
    }
}
