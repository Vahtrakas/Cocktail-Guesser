package com.ridango.game.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ridango.game.model.Cocktail;

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

        if(jsonResponse != null) {
            JSONObject response = new JSONObject(jsonResponse);
            JSONObject cocktail = response.getJSONArray("drinks").getJSONObject(0);
            String name = cocktail.getString("strDrink");
            String instructions = cocktail.getString("strInstructions");
            String category = cocktail.getString("strCategory");
            String glass = cocktail.getString("strGlass");
            return new Cocktail(name, instructions, category, glass);
        }
        return null;
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
