package it.unibo.t2sgame.core.engine.impl;

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
class ConcurrentGameLoop extends BaseGameLoop {

    /**
     * 
     * @param engine the engine where the game loop operates
     */
    ConcurrentGameLoop(final GameEngine engine) {
        super(engine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Consumer<Class<? extends Component>> getUpdater() {
        return this::updateComponentByConcurrent;
    }

    private <T extends Component> void updateComponentByConcurrent(final Class<T> clazz) {
        this.getEngine().getComponents(clazz).stream().parallel().forEach(Component::update);
    }

}
