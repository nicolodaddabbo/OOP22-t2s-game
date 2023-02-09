package it.unibo.t2sgame.core.gameloop.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.gameloop.api.GameLoop;

/**
 * This decoration class adds a synchronization stuff to {@link decorated}
 * GameLoop.
 * Instead of using a variabile time step (which indicates how much advancing
 * the "game time") to pass as parameter when updating the game
 * once in the cycle,
 * this decoration calls a series of updating based on a fixed size time step.
 * The synchronization stuff consists repeating "updateGame" more time during a
 * game loop's cycle advancing by little fixed time steps, in order to fill the
 * gap between "game time" and the "real time".
 * Every computer, from the faster to the slower, updates the game the same
 * times in a cycle of game loop,
 * independing from the amount of frames rendered.
 * This synchronization allows better collision and physics precision.
 */
public class SynchronizedGameLoop extends GameLoopDecorator {
    /*
     * Indicates the "granularity" used to update the game.
     * This granularity is express in nano seconds.
     */
    private static final long NS_UPDATE_PERDIOD = (long) (7 * 1E6);

    private final StopWatch timer = new StopWatch().start();
    /*
     * Indicates how much real time has passed from the last game loop's cycle
     * procession.
     */
    private long lag;

    /**
     * Creating a new GameLoop which decorates {@link decorated} with the logic's
     * implementation.
     * 
     * @param decorated the base game loop to decorate
     */
    public SynchronizedGameLoop(final GameLoop decorated) {
        super(decorated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame() {
        this.lag = this.lag + this.timer.getElapsedNanos();
        this.timer.restart();
        while (!this.isSync()) {
            super.updateGame();
            this.lag = this.lag - NS_UPDATE_PERDIOD;
        }
    }

    /*
     * Check if the "game time" is synchronized with the "real time"
     */
    private boolean isSync() {
        return this.lag < NS_UPDATE_PERDIOD;
    }

}
