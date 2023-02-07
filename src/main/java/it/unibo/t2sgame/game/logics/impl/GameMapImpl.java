package it.unibo.t2sgame.game.logics.impl;

import it.unibo.t2sgame.game.logics.api.GameMap;

public class GameMapImpl implements GameMap {
    private final double width;
    private final double height;

    public GameMapImpl(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }
}
