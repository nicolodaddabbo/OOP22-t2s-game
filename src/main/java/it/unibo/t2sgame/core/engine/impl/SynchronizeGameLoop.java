package it.unibo.t2sgame.core.engine.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.engine.api.GameLoop;
import it.unibo.t2sgame.core.engine.api.GameLoopDecorator;

/**
 * This decoration class adds a synchronization stuff to {@link decorated}
 * GameLoop.
 * The synchronization stuff consists repeating "updateGame" more time during a
 * cycle of game loop.
 * This will allow the non-use of the time step variable, ensuring greater
 * precision of the physics and collision systems during hypothetical frame rate
 * drops.
 */
public class SynchronizeGameLoop extends GameLoopDecorator {

    /*
     * This long indicates the perod of updating the states systems in nanoseconds
     */
    private static final long NS_UPDATE_PERDIOD = (long) (7 * 1E6);

    private final StopWatch timer = new StopWatch().start();
    private long lag = 0;

    public SynchronizeGameLoop(GameLoop decorated) {
        super(decorated);
    }

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
