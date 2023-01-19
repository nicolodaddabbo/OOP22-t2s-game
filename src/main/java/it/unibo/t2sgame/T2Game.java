package it.unibo.t2sgame;

import it.unibo.t2sgame.core.api.GameEngine;
import it.unibo.t2sgame.core.api.GameEngineFactory;
import it.unibo.t2sgame.core.api.GameFactory;
import it.unibo.t2sgame.core.impl.GameEngineFactoryImpl;
import it.unibo.t2sgame.core.impl.GameFactoryImpl;

public class T2Game {
    public static void main(final String... args) {
        new GameEngineFactoryImpl().createEngine()
            // Settnig a single player game
            .setGame(new GameFactoryImpl().createSinglePlayerGame())
            // Running the game loop
            .run();
    }
}
