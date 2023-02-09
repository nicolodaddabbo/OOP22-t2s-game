package it.unibo.t2sgame.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.common.shapes.Rectangle;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.ComponentFactoryImpl;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.input.api.Directions;

public class MessageTest {

    private ComponentFactory componentFactory = new ComponentFactoryImpl();

    private Entity baseEntity() {
        var pos = new Vector2D(0, 0);
        return new EntityImpl(pos, Type.PLAYER)
                .addComponent(this.componentFactory.createPhysicsComponentFrom(1))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(pos, 0, 0), false, List.of()));
    }

    @Test
    void testPhysicsReceiveDirection() {
        var entity = this.baseEntity();
        // Notifys the direction
        entity.notifyComponent(PhysicsComponent.class, () -> Directions.UP);
        var physComponent = entity.getComponent(PhysicsComponent.class).get();
        // The velocity should be the same as the given direction
        assertEquals(new Vector2D(0, -1), physComponent.getVelocity());
    }

    @Test
    void testPhysicsReceiveUnwantedMessage() {
        var entity = this.baseEntity();
        // Notifys unnecepted message
        entity.notifyComponent(PhysicsComponent.class, () -> "Go up");
        var physComponent = entity.getComponent(PhysicsComponent.class).get();
        // The velocity should be the unchanged
        assertNotEquals(new Vector2D(0, -1), physComponent.getVelocity());
    }

    @Test
    void testCollisionReceivePosition() {
        var entity = this.baseEntity();
        var pos = new Vector2D(5, 5);
        // Notifys the new position
        entity.notifyComponent(CollisionComponent.class, () -> pos);
        var collComponent = entity.getComponent(CollisionComponent.class).get();
        // The position of the collisions center should be the same as the nitified
        // position
        assertEquals(pos, collComponent.getShape().getCenter());
    }

    @Test
    void testCollisionReceiveUnwantedMessage() {
        var entity = this.baseEntity();
        var pos = new Vector2D(5, 5);
        // Notifys unnecepted message
        entity.notifyComponent(CollisionComponent.class, () -> "Go to position 5:5");
        var collComponent = entity.getComponent(CollisionComponent.class).get();
        // The position of the collisions center should be unchanged
        assertNotEquals(pos, collComponent.getShape().getCenter());
    }

    @Test
    void testComunicationPhysicsWithCollision() {
        var entity = this.baseEntity();
        // Notifys the direction
        entity.notifyComponent(PhysicsComponent.class, () -> Directions.UP);
        var physComponent = entity.getComponent(PhysicsComponent.class).get();
        // Notifys the collision component of the new position
        physComponent.update();
        var collComponent = entity.getComponent(CollisionComponent.class).get();
        // The position of the entity should be the same as the collisions center
        assertEquals(entity.getPosition(), collComponent.getShape().getCenter());
    }
}
