package bot;

import bot.utils.FileUtils;

import java.io.File;

/**
 * Created by Jo on 10.01.2017.
 * A learner makes the bot able to learn new sentences and answers.
 */
class Learner {

    private File file;

    Learner(File in) {
        this.file = in;
        if (!FileUtils.read(file).equals("")) {
            loadSentences();
        }
    }

    void learnSentence(String vocabulary, String response) {
        FileUtils.write(vocabulary + "::" + response, file); //save learned (divided by "::" as marker)
        loadSentences(); //make learned available
    }

    private void loadSentences() {
        String sentences = FileUtils.read(file);
        splitSentences(sentences);
    }

    private void splitSentences(String sentences) {
        String[] couples = sentences.split("\\n");
        for (String couple : couples) {
            String[] vocabularyAndResponse = couple.split("::");
            addToVoc(vocabularyAndResponse);
        }
    }

    /**
     * @param vocabularyAndResponse Adds this vocabulary to the bot.
     */
    private void addToVoc(String[] vocabularyAndResponse) {
        Phrases.CUSTOM_VOCABULARY.add(vocabularyAndResponse[0]);
        Phrases.CUSTOM_RESPONSES.add(vocabularyAndResponse[1]);
    }
}
