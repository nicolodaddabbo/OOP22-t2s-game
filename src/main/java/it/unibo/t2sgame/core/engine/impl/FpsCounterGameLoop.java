package it.unibo.t2sgame.core.engine.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.engine.api.GameLoop;
import it.unibo.t2sgame.core.engine.api.GameLoopDecorator;

public class FpsCounterGameLoop extends GameLoopDecorator {

    private static final long NS_UPDATE_FPS_COUNT = (long) (1000 * 1E6);

    private final StopWatch timer = new StopWatch().start();
    private int counter = 0;

    protected FpsCounterGameLoop(GameLoop decorated) {
        super(decorated);
    }

    @Override
    public void render() {
        super.render();
        if(this.timer.getElapsedNanos() >= NS_UPDATE_FPS_COUNT){
            /*
             * [TODO] This string concatenation is not good for performance,
             * As soon as I have a method for showing the fps counter on the scene
             * This printing on console will be replaced with the rendering on the scene
             */
            System.out.println("Fps:" + this.counter);
            reset();
        }
        this.counter++;
    }

    private void reset() {
        this.counter = 0;
        this.timer.restart();
    }
    
}
