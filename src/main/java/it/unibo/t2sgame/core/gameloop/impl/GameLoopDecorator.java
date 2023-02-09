package it.unibo.t2sgame.core.gameloop.impl;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.gameloop.api.GameLoop;

/**
 * This abstract class is usefull for Decorator pattern's implementation.
 */
public abstract class GameLoopDecorator implements GameLoop {
    /**
     * The base GameLoop which will be decorated
     */
    private final GameLoop decorated;

    /**
     * Creating a new GameLoop which decorates {@link decorated} with the logic's
     * implementation
     * 
     * @param decorated the base game loop to decorate
     */
    protected GameLoopDecorator(final GameLoop decorated) {
        this.decorated = decorated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        this.decorated.processInput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.decorated.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame() {
        this.decorated.updateGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEngine getEngine() {
        return this.decorated.getEngine();
    }

}
