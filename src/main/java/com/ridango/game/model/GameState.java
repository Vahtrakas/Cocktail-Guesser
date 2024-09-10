package com.ridango.game.model;

import java.util.HashSet;
import java.util.Set;

public class GameState {

    private Cocktail cocktail;
    private String hiddenWord;
    private int guesses;
    private boolean isGameWon;
    private boolean hintShown;
    private Set<Character> guessedLetters = new HashSet<>();

    public GameState(Cocktail cocktail) {
        this.cocktail = cocktail;
        this.hiddenWord = new String(new char[cocktail.getName().length()]).replace("\0", "*"); // Changes cocktail name letters to *.
        this.guesses = 0; // Sets number of guesses to 0.
        this.isGameWon = false; // Game is not won at the start of the game.
        this.hintShown = false; // Hint has not been shown at the start of the game.
    }

    public Cocktail getCocktail(){
        return cocktail;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void updateHiddenWord(String newHiddenWord) { // get new hidden name of cocktail
        this.hiddenWord = newHiddenWord;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses() { // To increment the number of guesses player has done.
        this.guesses++;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }

    public boolean isGameOver(){
        return guesses >= 5 || isGameWon;
    }    

    public boolean isHintShown() {
        return hintShown;
    }

    public void setHintShown() {
        this.hintShown = true;
    }    

    public void addGuessedLetter(char letter) {
        this.guessedLetters.add(letter);
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }
}
