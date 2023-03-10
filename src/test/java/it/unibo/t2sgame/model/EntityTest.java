package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.game.ecs.api.Entity;
import it.unibo.t2sgame.game.ecs.api.Type;
import it.unibo.t2sgame.game.ecs.impl.CollisionComponent;
import it.unibo.t2sgame.game.ecs.impl.EntityImpl;
import it.unibo.t2sgame.game.ecs.impl.PhysicsComponent;

class EntityTest {

    private Entity baseEntity() {
        return new EntityImpl(new Vector2D(0, 0), Type.PLAYER);
    }

    @Test
    void testGetComponentIsPresentAndReturnsExpectedComponent() {
        final Entity entity = this.baseEntity();
        entity.addComponent(new PhysicsComponent(0));
        final var componentOptional = entity.getComponent(PhysicsComponent.class);
        assertTrue(componentOptional.isPresent());
        assertTrue(PhysicsComponent.class.isInstance(componentOptional.get()));
    }

    @Test
    void testGetComponentIsNotPresent() {
        final Entity entity = this.baseEntity();
        entity.addComponent(new PhysicsComponent(0));
        final var componentOptional = entity.getComponent(CollisionComponent.class);
        assertFalse(componentOptional.isPresent());
    }

}
