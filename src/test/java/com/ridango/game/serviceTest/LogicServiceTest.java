package com.ridango.game.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ridango.game.model.Cocktail;
import com.ridango.game.model.GameState;
import com.ridango.game.service.CocktailService;
import com.ridango.game.service.LogicService;

public class LogicServiceTest {
    
    @Mock
    private CocktailService cocktailService;

    @InjectMocks
    private LogicService logicService;

    private Cocktail mockCocktail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCocktail = new Cocktail("Mojito", "Bake a banana and add some milk", "Breakfast", "Molten");
    }

    @Test
    void testStartGame() {
        when(cocktailService.getRandomCocktail()).thenReturn(mockCocktail);

        GameState gameState = logicService.startGame(null);
        assertNotNull(gameState);
        assertEquals("Mojito", gameState.getCocktail().getName());
        assertEquals("******", gameState.getHiddenWord());
        assertFalse(gameState.isGameWon());
    }
    
}
