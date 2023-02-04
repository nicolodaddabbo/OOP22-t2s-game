package it.unibo.t2sgame.core.engine.impl;

import java.util.function.Consumer;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.engine.api.GameLoop;

/**
 * This abstract class models the base of every game loop.
 * Based on its updater Consumer, the game loop update the components passed as
 * input of the Consumer.
 * The type of updater Consumer depends on the real implementation of the GameLoop
 */
abstract class BaseGameLoop implements GameLoop {

    protected final GameEngineImpl engine;

    public BaseGameLoop(GameEngineImpl engine) {
        this.engine = engine;
    }

    @Override
    public void processInput() {
        this.getUpdater().accept(InputComponent.class);
    }

    @Override
    public void render() {
        this.engine.getScene().render();
    }

    @Override
    public void updateGame() {
        this.getUpdater().accept(PhysicsComponent.class);
        this.getUpdater().accept(CollisionComponent.class);
        this.engine.getGame().update();
    }

    abstract Consumer<Class<? extends Component>> getUpdater();

    @Override
    public GameEngine getEngine() {
        return this.engine;
    }

}
