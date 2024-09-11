package com.ridango.game.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.ridango.game.model.Cocktail;
import com.ridango.game.model.GameState;

@Service
@SessionScope
public class LogicService {
    
    private final CocktailService cocktailService;

    public LogicService(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    //Starts game with a random cocktail name
    public GameState startGame(HttpSession session){
        Cocktail randomCocktail = cocktailService.getRandomCocktail();
        GameState gameState = new GameState(randomCocktail);
        session.setAttribute("gameState", gameState);
        return gameState;
    }

    public GameState getGameState(HttpSession session) {
        return (GameState) session.getAttribute("gameState");
    }

    public GameState makeGuess(HttpSession session, String playerGuess) {
        GameState gameState = getGameState(session);
        String hiddenWord = "";
        boolean isGuessCorrect = false;
        char charGuess = playerGuess.charAt(0);

        // iterate the cocktail name and update the hidden word
        for (int i = 0; i < gameState.getCocktail().getName().length(); i++) {
            char originalChar = gameState.getCocktail().getName().charAt(i);
            if (originalChar == ' ') {
                hiddenWord += ' ';
            } else if (originalChar == charGuess) {
                hiddenWord += charGuess;
                isGuessCorrect = true;
            } else {
                hiddenWord += gameState.getHiddenWord().charAt(i);
            }
        }

        gameState.addGuessedLetter(charGuess); // add entered letter to be tracked on screen.

        if (!isGuessCorrect) {
            gameState.setGuesses(); // Increase guess counter
        } else {
            gameState.addPoints();
            gameState.updateHiddenWord(hiddenWord); // update hidden word
        }

        // check if the player has won by revealing the entire word
        if (hiddenWord.equals(gameState.getCocktail().getName())) {
            gameState.setGameWon(true);
        }
        session.setAttribute("gameState", gameState);
        return gameState;
    }

    public void showHint(HttpSession session) {
        GameState gameState = getGameState(session);
        gameState.setHintShown();
        session.setAttribute("gameState", gameState);
    }
}
