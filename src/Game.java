import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Game {
    public static void main(String[] args) {
        playMusic("bgMusic.wav");
        boolean keepGoing = true;
        String[] usedWords;
        usedWords = new String[831];
        while (keepGoing) {
            Scanner inputDevice = new Scanner(System.in);
            System.out.println("Select difficulty");
            System.out.println("Easy:   1\nMedium: 2\nHard:   3\nEXIT:   4");
            System.out.print(">> ");
            int difficulty = inputDevice.nextInt();
            if (difficulty == 4) {
                break;
            }
            //Check word
            int wordCheck = 0;
            Word word = new Word(difficulty);
            while (wordCheck == 0) {

                for (int i = 0; i < usedWords.length; ++i) {
                    if (word.getWord().equals(usedWords[i])) {
                        usedWords[i] = word.getWord();
                        word = new Word(difficulty);
                        break;

                    } else if (i == usedWords.length - 1) {
                        wordCheck = 1;
                    }
                }
            }

            //Print word length
            int wrongGuess = 0;
            HangmanDisplay hangman = new HangmanDisplay(wrongGuess);
            char[] letters = word.getWord().toCharArray();
            char[] hints = new char[letters.length];
            Arrays.fill(hints, '_');
            System.out.println(hints);

            //Check guess
            int remainingLetters = letters.length;
            char[] updatedHints = new char[letters.length];
            Arrays.fill(updatedHints, '_');
            while (wrongGuess != 6) {
                System.out.println("Enter guess");
                System.out.print(">> ");
                String guess = inputDevice.next();
                while (guess.length() > 1) {
                    System.out.println("Guess must be 1 letter only");
                    System.out.print(">> ");
                    guess = inputDevice.next();
                }
                for (int i = 0; i < letters.length; ++i) {
                    if (letters[i] == guess.charAt(0)) {
                        remainingLetters -= 1;
                        updatedHints[i] = guess.charAt(0);
                    }
                }
                boolean letterWrong;
                letterWrong = Arrays.equals(hints, updatedHints);

                if (letterWrong) {
                    wrongGuess += 1;
                } else {
                    System.arraycopy(updatedHints, 0, hints, 0, letters.length);
                }
                new HangmanDisplay(wrongGuess);
                System.out.println(hints);
                if (remainingLetters == 0) {
                    break;
                }
            }
            System.out.println("\nThe word was: " + word.getWord());
            if (wrongGuess == 6) {
                System.out.println("You Lose\n");
            } else {
                System.out.println("Winner!\n");
            }
        }
    }

    public static void playMusic(String musicFile) {
        Clip clip;

        try {
            AudioInputStream input = AudioSystem.getAudioInputStream(new File(musicFile));
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

