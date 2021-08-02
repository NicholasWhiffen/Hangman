import java.util.Arrays;
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        int keepGoing = 1;
        String[] usedWords;
        usedWords = new String [831];
        while(keepGoing == 1) {
            Scanner inputDevice = new Scanner(System.in);
            System.out.println("Select difficulty");
            System.out.println("Easy:   1\nMedium: 2\nHard:   3");
            System.out.print(">> ");
            int difficulty = inputDevice.nextInt();

            //Check word
            int wordCheck = 0;
            Word word = new Word(difficulty);
            while(wordCheck == 0){

                for(int i = 0; i < usedWords.length; ++i){
                    if(word.getWord().equals(usedWords[i])){
                        usedWords[i] = word.getWord();
                        word = new Word(difficulty);
                        break;

                    }else if (i == usedWords.length - 1){
                        wordCheck = 1;
                    }
                }
            }

            //Print word length
            char[] letters = word.getWord().toCharArray();
            char[] hints = new char[letters.length];
            Arrays.fill(hints, '_');
            System.out.println(hints);

            //Check guess
            int wrongGuess = 0;
            int remainingLetters = letters.length;
            char[] updatedHints = new char[letters.length];
            while (wrongGuess != 6) {
                System.out.println("Enter guess");
                System.out.print(">> ");
                String guess = inputDevice.next();
                while (guess.length() > 1) {
                    System.out.println("Guess must be 1 letter only");
                    System.out.print(">> ");
                    guess = inputDevice.next();
                }
                for(int i = 0; i < letters.length; ++i){
                    if(letters[i] == guess.charAt(0)) {
                        remainingLetters -= 1;
                        updatedHints[i] = guess.charAt(0);
                    }
                }
                if(hints == updatedHints){
                    wrongGuess += 1;
                } else{
                    hints = updatedHints;
                }
                System.out.println(hints);
                if(remainingLetters == 0){
                    break;
                }
            }
        }
    }
}

