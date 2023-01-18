package it.unibo.t2sgame.core.impl;

import it.unibo.t2sgame.core.api.GameEngine;
import it.unibo.t2sgame.core.api.GameEngineFactory;

public class JEngineFactory implements GameEngineFactory {

    @Override
    public GameEngine createEngine() {
        // [TODO] : Use builder pattern to create the game engine's istance
        return new JEngine();
    }
    
}
