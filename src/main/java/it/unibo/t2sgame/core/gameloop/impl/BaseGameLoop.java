package it.unibo.t2sgame.core.gameloop.impl;

import java.util.function.Consumer;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.gameloop.api.GameLoop;

/**
 * This abstract class models the base of every game loop.
 * Based on its "updater" Consumer, the game loop updates a group of components of
 * the same type.
 * The type of updater Consumer depends on the real implementation of the
 * BaseGameLoop.
 */
public abstract class BaseGameLoop implements GameLoop {

    protected final GameEngine engine;

    /**
     * 
     * @param engine the engine where the game loop operates
     */
    protected BaseGameLoop(final GameEngine engine) {
        this.engine = engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        this.getUpdater().accept(InputComponent.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.engine.getScene().render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame() {
        this.getUpdater().accept(PhysicsComponent.class);
        this.getUpdater().accept(CollisionComponent.class);
        this.engine.getGame().update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEngine getEngine() {
        return this.engine;
    }

    /**
     * 
     * @return the component updater whichs is delegated to update a group of
     *         component of the same type
     */
    abstract Consumer<Class<? extends Component>> getUpdater();

}
