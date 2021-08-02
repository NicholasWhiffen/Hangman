import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Word {
    public static String[] getWords(){
        File myObj = new File("word_list.txt");
        String[] words  = new String [831];
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (myReader.hasNextLine()) {
            String word = myReader.nextLine();
            words[i] = word;
            i += 1;
        }
        myReader.close();
        return words;
    }

    private String word;
    public String getWord() {
        return word;
    }
    public void setWordEasy(String[] words) {
        Random randomNumber = new Random();
        String word = words[randomNumber.nextInt(830)];
        while(word.length() > 4){
            word = words[randomNumber.nextInt(830)];
        }
        this.word = word;
    }

    public void setWordMedium(String[] words) {
        Random randomNumber = new Random();
        String word = words[randomNumber.nextInt(830)];
        while(word.length() != 5){
            word = words[randomNumber.nextInt(830)];
        }
        this.word = word;
    }

    public void setWordHard(String[] words) {
        Random randomNumber = new Random();
        String word = words[randomNumber.nextInt(830)];
        while(word.length() < 6){
            word = words[randomNumber.nextInt(830)];
        }
        this.word = word;
    }

    public Word(int difficulty) {
        switch (difficulty) {
            case 1 -> setWordEasy(getWords());
            case 2 -> setWordMedium(getWords());
            case 3 -> setWordHard(getWords());
        }
    }
}

