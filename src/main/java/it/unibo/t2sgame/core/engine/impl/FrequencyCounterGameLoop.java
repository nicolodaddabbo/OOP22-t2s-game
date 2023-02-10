package it.unibo.t2sgame.core.engine.impl;

import it.unibo.t2sgame.common.StopWatch;

/**
 * This GameLoop's decoration allows to show the frequency (Hz) of
 * {@link decorated} game loop's cycle procession.
 * Knowing how much cycles are done every second, and considering that every
 * frame
 * is processed once per cycle, this decoration allows to show the frame rate of
 * the game.
 */
class FrequencyCounterGameLoop extends GameLoopDecorator {

    private static final long NS_UPDATE_FPS_COUNT = (long) (1000 * 1E6);

    private final StopWatch timer = new StopWatch().start();

    private int counter;

    /**
     * Creating a new GameLoop which decorates {@link decorated} with the logic's
     * implementation.
     * 
     * @param decorated the base game loop to decorate
     */
    FrequencyCounterGameLoop(final GameLoop decorated) {
        super(decorated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        super.render();
        if (this.timer.getElapsedNanos() >= NS_UPDATE_FPS_COUNT) {
            this.getEngine().getScene().ifPresent(s -> s.renderFPS(counter));
            reset();
        }
        this.counter++;
    }

    private void reset() {
        this.counter = 0;
        this.timer.restart();
    }

}
