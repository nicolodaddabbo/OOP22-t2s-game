package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;

public class EntityTest {

    private Entity baseEntity() {
        return new EntityImpl(new Vector2D(0, 0), Type.PLAYER);
    }

    @Test
    void testGetComponentIsPresentAndReturnsExpectedComponent() {
        Entity entity = this.baseEntity();
        entity.addComponent(new PhysicsComponent(1));
        var componentOptional = entity.getComponent(PhysicsComponent.class);
        assertTrue(componentOptional.isPresent());
        assertTrue(PhysicsComponent.class.isInstance(componentOptional.get()));
    }

    @Test
    void testGetComponentIsNotPresent() {
        Entity entity = this.baseEntity();
        entity.addComponent(new PhysicsComponent(1));
        var componentOptional = entity.getComponent(CollisionComponent.class);
        assertFalse(componentOptional.isPresent());
    }

}
