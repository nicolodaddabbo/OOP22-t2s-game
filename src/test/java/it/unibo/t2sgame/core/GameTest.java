package it.unibo.t2sgame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.core.api.GameFactory;
import it.unibo.t2sgame.core.impl.GameFactoryImpl;

public class GameTest {
    private final GameFactory gameFactory = new GameFactoryImpl();

    private void basics(final Game g){
        g.nextWave();
        assertEquals(1, g.getState().getRound());
        g.nextWave();
        assertEquals(2, g.getState().getRound());
        g.nextWave();
        assertEquals(3, g.getState().getRound());
    }

    @Test
    void testSinglePlayerGame(){
        var game = this.gameFactory.createSinglePlayerGame();
        // Check if there is only one player
        assertTrue(game.getWorld().getPlayers().size() == 1);
        // Checking the basics
        basics(game);
    }
    @Test
    void testMultiPlayerGame(){
        var game = this.gameFactory.createMultiPlayerGame();
        // Check if there are more players
        assertTrue(game.getWorld().getPlayers().size() > 1);
        // Checking the basics
        basics(game);        
    }
}
