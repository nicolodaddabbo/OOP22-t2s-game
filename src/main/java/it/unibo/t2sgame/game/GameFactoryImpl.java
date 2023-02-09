package it.unibo.t2sgame.game;

import it.unibo.t2sgame.game.logics.impl.StateImpl;
import it.unibo.t2sgame.game.model.api.WorldFactory;
import it.unibo.t2sgame.game.model.impl.WorldFactoryImpl;

/**
 * A GameFactory's implementation.
 * See {@link GameFactory}
 */
public class GameFactoryImpl implements GameFactory {

    private final WorldFactory worldFactory = new WorldFactoryImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Game createSinglePlayerGame() {
        return new T2SGame(new StateImpl(), this.worldFactory.createWorldWithOnePlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game createMultiPlayerGame() {
        return new T2SGame(new StateImpl(), this.worldFactory.createWorldWithPlayerAndCompanion());
    }
}
