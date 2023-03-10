package it.unibo.t2sgame.core.engine.impl;

import java.util.function.Consumer;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.ecs.api.Component;
import it.unibo.t2sgame.game.ecs.impl.CollisionComponent;
import it.unibo.t2sgame.game.ecs.impl.InputComponent;
import it.unibo.t2sgame.game.ecs.impl.PhysicsComponent;
import it.unibo.t2sgame.view.api.GameScene;

/**
 * This abstract class models the base of every game loop.
 * Based on its "updater" Consumer, the game loop updates a group of components
 * of
 * the same type.
 * The type of updater Consumer depends on the real implementation of the
 * BaseGameLoop.
 */
abstract class BaseGameLoop implements GameLoop {

    private final GameEngine engine;

    /**
     * 
     * @param engine the engine where the game loop operates.
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
        this.engine.getScene().ifPresent(GameScene::render);
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
     *         component of the same type.
     */
    protected abstract Consumer<Class<? extends Component>> getUpdater();

}
