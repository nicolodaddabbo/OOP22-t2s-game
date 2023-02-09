package it.unibo.t2sgame.core.gameloop.impl;

import java.util.function.Consumer;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.engine.api.GameEngine;

/**
 * This class extends the base game loop implementation.
 * This class implements getUpdater abstract method with a
 * concurrent-updater, in order
 * to be used by the template methods in BaseGameLoop.
 * It's preferable to use this GameLoop in order to achieve better
 * performances.
 */
public class ConcurrentGameLoop extends BaseGameLoop {

    /**
     * {@inheritDoc}
     */
    public ConcurrentGameLoop(final GameEngine engine) {
        super(engine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Consumer<Class<? extends Component>> getUpdater() {
        return this.engine::updateComponentByConcurrent;
    }

}
