package com.ridango.game.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ridango.game.model.Cocktail;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashSet;
import java.util.Set;

@Service
public class CocktailService {

    private final RestTemplate restTemplate;

    public CocktailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Cocktail getRandomCocktail() { // This receives a random cocktail.
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php"; // URL to get a random cocktail.
        String jsonResponse = restTemplate.getForObject(url, String.class);
        Cocktail cocktail = new Cocktail(null, null);

        if(jsonResponse != null) {
            JSONObject response = new JSONObject(jsonResponse);
            JSONArray drinksArray = response.optJSONArray("drinks");

            if (drinksArray != null && drinksArray.length() > 0) {
                JSONObject drink = drinksArray.getJSONObject(0);
                cocktail.setName(drink.getString("strDrink"));
                cocktail.setInstructions(drink.getString("strInstructions"));
            }
        }
        return cocktail;
    }

    public Set<Cocktail> getUniqueCocktails() { // This receives 30 different cocktails from getRandomCocktail(). Set only allows 1 of each cocktail, no duplicates.
        Set<Cocktail> uniqueCocktails = new HashSet<>();
        while(uniqueCocktails.size() < 30){
            Cocktail randomCocktail = getRandomCocktail();
            if (randomCocktail != null) {
                uniqueCocktails.add(randomCocktail);                
            }
        }
        return uniqueCocktails;
    }
    
}
