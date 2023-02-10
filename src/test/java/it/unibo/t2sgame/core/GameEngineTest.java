package it.unibo.t2sgame.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.engine.impl.GameEngineImpl;
import it.unibo.t2sgame.game.GameFactory;
import it.unibo.t2sgame.game.GameFactoryImpl;
import it.unibo.t2sgame.game.ecs.impl.CollisionComponent;
import it.unibo.t2sgame.game.ecs.impl.GraphicComponent;
import it.unibo.t2sgame.game.ecs.impl.InputComponent;
import it.unibo.t2sgame.game.ecs.impl.PhysicsComponent;

class GameEngineTest {

    private final GameFactory gameFactory = new GameFactoryImpl();

    private final GameEngine engine = new GameEngineImpl(this.gameFactory.createSinglePlayerGame());

    @Test
    void testRunAndStop() {
        assertDoesNotThrow(this.engine::run, "Engine was already running");
        assertThrows(IllegalStateException.class, this.engine::run);
        assertDoesNotThrow(this.engine::stop, "Engine was not running");
        assertDoesNotThrow(this.engine::run, "Engine was already running");
        assertDoesNotThrow(this.engine::stop, "Engine was not running");
        assertThrows(IllegalStateException.class, this.engine::stop);
    }

    @Test
    void testGetters() {
        assertNotNull(this.engine);
        assertNotNull(this.engine.getGame());
        assertNotNull(this.engine.getComponents(InputComponent.class));
        assertNotNull(this.engine.getComponents(PhysicsComponent.class));
        assertNotNull(this.engine.getComponents(CollisionComponent.class));
        assertNotNull(this.engine.getComponents(GraphicComponent.class));
        assertNotNull(this.engine.getScene());
    }

}
