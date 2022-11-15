package com.github.zipcodewilmington;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
public class Hangman {

    Scanner scan = new Scanner(System.in);
    String[] wordsToGuess = {"pizza", "snowboard", "baseball", "sushi"};
    Character[] givenWord;
    Character[] displayWord;
    int playerGuesses;

    public Hangman(){
    }

    public static void main(String[] args) {
        Hangman newGame = new Hangman();
        newGame.runGame();
    }

    public void runGame(){
        gameSetUp();
        playerGuesses = 0;

        while (playerGuesses < givenWord.length + 2){
            printCurrentDisplay();
            char guess = playerGuess();
            round(guess);
            playerGuesses++;
            if (Arrays.equals(givenWord, displayWord)){
                playerWins();
                break;
            }
        }
        if (playerGuesses > givenWord.length + 1){
            playerLoses();
        }
        if (replayGame()){
            runGame();
        }
    }

    public String printArray(Character[] word){
        StringBuilder sb = new StringBuilder();
        for (Character c : word) {
            sb.append(c).append("");
        }
        return sb.toString();
    }
    public void gameSetUp() {
        int num = (int) (Math.random() * wordsToGuess.length);
        givenWord = new Character[wordsToGuess[num].length()];
        displayWord = new Character[wordsToGuess[num].length()];
        for (int i = 0; i < wordsToGuess[num].length(); i++) {
            givenWord[i] = wordsToGuess[num].charAt(i);
            displayWord[i] = '_';
        }
    }

    public Character playerGuess() {
        System.out.println("Dare you to guess a letter: ");
        return scan.nextLine().charAt(0);
    }
    public Boolean replayGame() {
        System.out.println("Bet you won't try and guess the next word! Type y if you want to give it another whirl");
        return (scan.nextLine().equalsIgnoreCase("y"));
    }

    public void printCurrentDisplay() {
        System.out.println(printArray(displayWord));
    }

    public void round(Character guess) {
        for (int i = 0; i < givenWord.length; i++){
            if (givenWord[i].equals(guess)){
                displayWord[i] = guess;
            }
        }
    }

    public void playerWins() {
        printCurrentDisplay();
        System.out.println("Atta boy! You won in " + playerGuesses + " guesses!");
    }

    public void playerLoses() {
        printCurrentDisplay();
        System.out.println("Oh no! Better luck next time!");
    }


}
