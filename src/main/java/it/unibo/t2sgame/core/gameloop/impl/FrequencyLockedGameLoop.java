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

    private static final double NANO_TO_MILLIS = 1E6;

    /*
     * Indicates the minimum period the computer takes to complete a game loop's
     * cycle.
     */
    private static final long NS_CYCLE_PERIOD = (long) (7 * NANO_TO_MILLIS);

    private final StopWatch timer = new StopWatch().start();

    /**
     * Creating a new GameLoop which decorates {@link decorated} with the logic's
     * implementation.
     * 
     * @param decorated the base game loop to decorate
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
        final var timeToSleep = NS_CYCLE_PERIOD - this.timer.getElapsedNanos();
        if (timeToSleep > 0) {
            try {
                // Sleeping time has to be converted in milliseconds
                Thread.sleep((long) ((timeToSleep) / NANO_TO_MILLIS));
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
