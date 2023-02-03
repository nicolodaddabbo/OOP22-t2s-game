package it.unibo.t2sgame.core.engine.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.engine.api.GameLoop;
import it.unibo.t2sgame.core.engine.api.GameLoopDecorator;

/**
 * This decoration class adds a fps lock to {@link decorated} GameLoop.
 * This will results in better performance and less CPU/GPU usage.
 */
public class FpsLockedGameLoop extends GameLoopDecorator {
    /*
     * This long indicates the perod of updating the states systems in nanoseconds
     * 17.3 ms = 60  fps
     * 7.3  ms = 144 fps
     */
    private static final long NS_FRAME_PERIOD = (long) (7.3 * 1E6);

    private StopWatch timer = new StopWatch().start();

    public FpsLockedGameLoop(GameLoop decorated) {
        super(decorated);
    }

    @Override
    public void render() {
        super.render();
        this.waitForNextFrame();
        // Reset the frame synchronizer timer
        this.timer.restart();
    }

    /**
     * A waiter function usefull to optimize performance.
     * As soon as the thread has told to scene to render, it sleeps
     * for a time which is equal to the frame period procession.
     * This will cause an fps lock during the game loop.
     */
    private void waitForNextFrame() {
        var timeToSleep = NS_FRAME_PERIOD - this.timer.getElapsedNanos();
        if (timeToSleep > 0) {
            try {
                // Sleeping time has to be converted in milliseconds
                Thread.sleep((long) ((timeToSleep) / 1E6));
            } catch (final InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

}
