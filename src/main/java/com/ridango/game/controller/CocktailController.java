package com.ridango.game.controller;

import com.ridango.game.model.Cocktail;
import com.ridango.game.service.CocktailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/cocktail") // This gets a single random cocktail with instructions. (strDrink & strInstructions)
    public Cocktail getRandomCocktail() {
        return cocktailService.getRandomCocktail();
    }

    @GetMapping("/cocktails")// This gets 30 random and unique cocktails with instructions. 
    public Set<Cocktail> getUniqueCocktails() {
        return cocktailService.getUniqueCocktails();
    }
}
