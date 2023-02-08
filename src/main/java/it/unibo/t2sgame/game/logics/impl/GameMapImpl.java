package it.unibo.t2sgame.game.logics.impl;

import it.unibo.t2sgame.game.logics.api.GameMap;
/**
 * class that implements interface GameMap.
 */
public class GameMapImpl implements GameMap {
    private final double width;
    private final double height;
    /**
     * constructor of GameMapImpl.
     * @param width of the GameMap
     * @param height of the GameMap
     */
    public GameMapImpl(final double width, final double height) {
        this.width = width;
        this.height = height;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }
}
