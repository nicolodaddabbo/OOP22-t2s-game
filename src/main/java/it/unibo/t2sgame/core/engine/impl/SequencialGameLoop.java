package it.unibo.t2sgame.core.engine.impl;

import java.util.function.Consumer;

import it.unibo.t2sgame.core.components.api.Component;

/**
 * This class extends the base game loop implementation.
 * This class implements getUpdater abstract method with a non
 * concurrent-updater, in order
 * to be used by the template methods in BaseGameLoop.
 * It's not preferable to use this class in order to achieve better
 * performances.
 */
class SequencialGameLoop extends BaseGameLoop {

    public SequencialGameLoop(GameEngineImpl engine) {
        super(engine);
    }

    @Override
    Consumer<Class<? extends Component>> getUpdater() {
        return this.engine::updateComponentBy;
    }

}
