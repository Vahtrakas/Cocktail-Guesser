package com.ridango.game.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ridango.game.model.GameState;
import com.ridango.game.service.LogicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/CocktailGuesser")
public class LogicController {
    
    private final LogicService logicService;

    public LogicController(LogicService logicService) {
        this.logicService = logicService;
    }

    @GetMapping("/start")
    public String startNewGame(HttpSession session, Model model) {
        GameState gameState = logicService.startGame(session);
        model.addAttribute("gameState", gameState);
        return "CocktailGuesser";
    }

    @PostMapping("/guess")
    public String makeGuess(@RequestParam("guess") String playerGuess, HttpSession session, Model model) {
        try {
            GameState gameState = logicService.makeGuess(session, playerGuess);
            model.addAttribute("gameState", gameState);
            if (gameState.isGameOver()) {
                if (gameState.isGameWon()) {
                    model.addAttribute("message", "You won this time! The word was: " + gameState.getCocktail().getName());
                } else {
                    model.addAttribute("message", "You lost this time. The word was: " + gameState.getCocktail().getName());
                }
            } else {
                model.addAttribute("message", "Keep Guessing!");
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
        }

        return "CocktailGuesser";
    }

    @PostMapping("/hint")
    public String showHint(HttpSession session, Model model) {
        logicService.showHint(session);
        GameState gameState = logicService.getGameState(session);
        model.addAttribute("gameState", gameState);
        model.addAttribute("hintMessage", "Drink category: " + gameState.getCocktail().getCategory() + ", Drink glass: " + gameState.getCocktail().getGlass());
        return "CocktailGuesser";
    }
    
}
