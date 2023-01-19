package it.unibo.t2sgame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.core.api.GameEngine;
import it.unibo.t2sgame.core.api.GameEngineFactory;
import it.unibo.t2sgame.core.api.GameFactory;
import it.unibo.t2sgame.core.impl.GameEngineFactoryImpl;
import it.unibo.t2sgame.core.impl.GameFactoryImpl;

public class GameEngineTest {
    private final GameEngineFactory gameEngineFactory = new GameEngineFactoryImpl();
    private final GameFactory gameFactory = new GameFactoryImpl();

    private void basics(final GameEngine engine){
        assertEquals(Optional.empty(), engine.getGame());
        // Setting a game to the game engine
        engine.setGame(this.gameFactory.createSinglePlayerGame());        
        assertNotEquals(Optional.empty(), engine.getGame());
        /*
         * Running the engine: 
         * this will generate an infinity loop which causes
         * the test to be failed.
         */
        engine.run();
    }

    @Test
    void testGameEngine(){
        var engine = this.gameEngineFactory.createEngine();
        this.basics(engine);
    }

    @Test
    void testGameEngineWithFpsLock(){

    }
}
