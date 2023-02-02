package it.unibo.t2sgame.core.engine.impl;

import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.engine.api.GameLoop;

public class BasicGameLoop implements GameLoop {

    private final GameEngineImpl engine;

    public BasicGameLoop(GameEngineImpl engine) {
        this.engine = engine;
    }

    @Override
    public void processInput() {
        // Update all Input System
        this.engine.updateComponentByConcurrent(InputComponent.class);
    }

    @Override
    public void updateGame() {
        // Update the Physics system
        this.engine.updateComponentByConcurrent(PhysicsComponent.class);
        // Update the Collision system
        this.engine.updateComponentByConcurrent(CollisionComponent.class);
        // Update the game logics and check events
        this.engine.getGame().update();
    }

    @Override
    public void render() {
        // Delegate rendering to the scene
        this.engine.getScene().render();
    }

}
