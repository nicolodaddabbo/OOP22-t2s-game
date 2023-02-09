package it.unibo.t2sgame.core.gameloop.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.gameloop.api.GameLoop;

/**
 * This Gameloop's decoration adds a frequency lock to {@link decorated}
 * GameLoop.
 * This decoration let the engine's thread to sleep for a time based on the
 * difference
 * between the minimum period {@link NS_CYCLE_PERIOD} and the time spent during
 * the game loop's cycle procession.
 * Considering that every frame is processed once during every game loop's
 * cycle,
 * even the frame rate will be locked.
 */
public class FrequencyLockedGameLoop extends GameLoopDecorator {

    /*
     * Indicates the minimum period the computer takes to complete a game loop's
     * cycle.
     */
    private static final long NS_CYCLE_PERIOD = (long) (7 * 1E6);

    private StopWatch timer = new StopWatch().start();

    /**
     * {@inheritDoc}
     */
    public FrequencyLockedGameLoop(final GameLoop decorated) {
        super(decorated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        super.render();
        this.waitForNextCycle();
        // Reset the frame synchronizer timer
        this.timer.restart();
    }

    /**
     * A waiter function usefull to optimize performance.
     * As soon as the thread has told to scene to render, it sleeps
     * for a time which is equal to the frame period procession.
     * This will cause an fps lock during the game loop.
     */
    private void waitForNextCycle() {
        var timeToSleep = NS_CYCLE_PERIOD - this.timer.getElapsedNanos();
        if (timeToSleep > 0) {
            try {
                // Sleeping time has to be converted in milliseconds
                Thread.sleep((long) ((timeToSleep) / 1E6));
            } catch (final InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

}