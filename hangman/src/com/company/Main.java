package com.company;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

class Main {

    public static void drawHangMan(int lives) {
        if(lives == 6){
            System.out.println("+---+\n     | \n     |\n     |\n    ===");
        }
        else if(lives == 5){
            System.out.println("+---+\n  0   | \n      |\n      |\n     ===");
        }
        else if(lives == 4){
            System.out.println("+---+\n  0   | \n  |   |\n      |\n     ===");
        }
        else if(lives == 3){
            System.out.println("+---+\n  0   | \n /|   |\n      |\n     ===");
        }
        else if(lives == 2){
            System.out.println("+---+\n  0   | \n /|\\  |\n      |\n     ===");
        }
        else if(lives == 1){
            System.out.println("+---+\n  0   | \n /|\\  |\n /    |\n     ===");
        }
        else {
            System.out.println("+---+\n  0   | \n /|\\  |\n / \\  |\n     ===");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File dictionary = new File("/home/jordan/IdeaProjects/hangman/src/com/company/dictionary.txt");
        Scanner textScanner = new Scanner(dictionary);
        Scanner input = new Scanner(System.in);
        String word = "";

        ArrayList<String> words = new ArrayList<>();
        while (textScanner.hasNext()) {
            words.add(textScanner.nextLine());
        }

        String userinput = "yes";

        do {
            String hidden_text = words.get((int) (Math.random() * words.size()));
            char[] textArray = hidden_text.toCharArray();
            char[] myAnswers = new char[textArray.length];
            String guesses = "";
            String missedLetter = "";
            boolean guessescontainsguess;

            for (int i = 0; i < textArray.length; i++) {
                myAnswers[i] = '?';
            }
            boolean finished = false;
            int lives = 6;

            while (finished == false) {
                System.out.println("H A N G M A N");

                String letter = input.next();

                guessescontainsguess = (guesses.indexOf(letter)) != -1;

                // stores every letter
                // guessed in guesses
                guesses += letter;



                // if letter already guessed
                if (guessescontainsguess == true) {
                    // already guessed letter
                    System.out.println("You ALREADY guessed "
                            + letter + ". \n");
                }

                // Check for valid input
                while (letter.length() != 1 || Character.isDigit((letter.charAt(0)))) {
                    System.out.println(" Please try again");
                    letter = input.next();
                }

                // Checks if letter is in the word
                boolean found = false;
                for (int i = 0; i < textArray.length; i++) {
                    if (letter.charAt(0) == textArray[i]) {
                        myAnswers[i] = textArray[i];
                        found = true;
                    }
                    else {
                        missedLetter +=letter;
                        break;
                    }
                }




                if (!found && !(guessescontainsguess == true)) {
                    lives--;
                    System.out.println("Wrong letter");
                }

                boolean done = true;

                for (int i = 0; i < myAnswers.length; i++) {
                    if (myAnswers[i] == '?') {
                        System.out.print(" _");
                        done = false;
                    } else {
                        System.out.print(" " + myAnswers[i]);
                    }
                }

                // removes duplicates from string
                int index = 0;
                char[] charArray = missedLetter.toCharArray();
                int n = missedLetter.length();
                for(int i = 0; i < n; i++){
                    // Check if  charArray[i] is present before it
                    int j;
                    for(j = 0; j < i; j++){
                        if (charArray[i] == charArray[j]) {
                                break;
                        }
                    }
                    // If not present, then add it to result
                    if(j == i) {
                        charArray[index++] = charArray[i];
                    }
                }
                System.out.println("\n" + "Lives Left: " + lives);
                drawHangMan(lives);
                System.out.println("Missed Letters: " + String.valueOf(Arrays.copyOf(charArray, index)));

                // Check if the game ends
                if (done) {
                    System.out.println("Congrats you Found the word");
                    System.out.println("Do you want to play again (yes or no)");
                    Scanner playAgain = new Scanner(System.in);
                    userinput = playAgain.nextLine();
                    finished = true;

                }
                if (lives <= 0) {
                    for(char element: textArray ){
                        word += element;
                    }
                    System.out.println("You lost the word I was thinking of is " + word);
                    System.out.println("Do you want to play again (yes or no)");
                    Scanner playAgain = new Scanner(System.in);
                    userinput = playAgain.nextLine();
                    finished = true;
                }
            }
        } while(userinput.equals("yes") || userinput.equals("y"));
    }
}