package bot.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Jo on 10.01.2017.
 * Holds file utilities.
 */
public class FileUtils {

    /**
     * @param text Appends this string
     * @param file to this file.
     */
    public static void write(String text, File file) {
        try {
            text += "\n";
            byte[] fileText = read(file).getBytes();
            byte[] appendText = text.toLowerCase().getBytes();
            byte[] combined = new byte[fileText.length + appendText.length];
            for (int i = 0; i < combined.length; ++i) {
                combined[i] = i < fileText.length ? fileText[i] : appendText[i - fileText.length];
            }
            Files.write(file.toPath(), combined);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file Reads this file
     * @return and returns the content as string.
     */
    public static String read(File file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file.toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Creates vocabulary.txt, if it doesn't already exists.
     */
    public static void createFiles() {
        File voc = new File(System.getProperty("user.dir") + "/vocabulary.txt");
        if (!voc.exists()) {
            try {
                voc.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
