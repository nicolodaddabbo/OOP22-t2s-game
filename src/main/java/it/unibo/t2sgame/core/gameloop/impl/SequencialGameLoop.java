package it.unibo.t2sgame.core.gameloop.impl;

import java.util.function.Consumer;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.engine.api.GameEngine;

/**
 * This class extends the base game loop implementation.
 * This class implements getUpdater abstract method with a non
 * concurrent-updater, in order
 * to be used by the template methods in BaseGameLoop.
 * It's not preferable to use this class in order to achieve better
 * performances.
 */
public class SequencialGameLoop extends BaseGameLoop {

    /**
     * {@inheritDoc}
     */
    public SequencialGameLoop(final GameEngine engine) {
        super(engine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Consumer<Class<? extends Component>> getUpdater() {
        return this.engine::updateComponentBy;
    }

}