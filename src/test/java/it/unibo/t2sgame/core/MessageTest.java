package it.unibo.t2sgame.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.common.shapes.Rectangle;
import it.unibo.t2sgame.game.ecs.api.ComponentFactory;
import it.unibo.t2sgame.game.ecs.api.Entity;
import it.unibo.t2sgame.game.ecs.api.Type;
import it.unibo.t2sgame.game.ecs.impl.CollisionComponent;
import it.unibo.t2sgame.game.ecs.impl.ComponentFactoryImpl;
import it.unibo.t2sgame.game.ecs.impl.EntityImpl;
import it.unibo.t2sgame.game.ecs.impl.PhysicsComponent;
import it.unibo.t2sgame.input.api.Directions;

class MessageTest {

    private final ComponentFactory componentFactory = new ComponentFactoryImpl();

    private Entity baseEntity() {
        final var pos = new Vector2D(0, 0);
        return new EntityImpl(pos, Type.PLAYER)
                .addComponent(this.componentFactory.createPhysicsComponentFrom(1))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(pos, 0, 0), false, List.of()));
    }

    @Test
    void testPhysicsReceiveDirection() {
        final var entity = this.baseEntity();
        // Notifys the direction
        entity.notifyComponent(PhysicsComponent.class, () -> Directions.UP);
        final var physComponent = entity.getComponent(PhysicsComponent.class).get();
        // The velocity should be the same as the given direction
        assertEquals(new Vector2D(0, -1), physComponent.getVelocity());
    }

    @Test
    void testPhysicsReceiveUnwantedMessage() {
        final var entity = this.baseEntity();
        // Notifys unnecepted message
        entity.notifyComponent(PhysicsComponent.class, () -> "Go up");
        final var physComponent = entity.getComponent(PhysicsComponent.class).get();
        // The velocity should be the unchanged
        assertNotEquals(new Vector2D(0, -1), physComponent.getVelocity());
    }

    @Test
    void testCollisionReceivePosition() {
        final var entity = this.baseEntity();
        final var pos = new Vector2D(5, 5);
        // Notifys the new position
        entity.notifyComponent(CollisionComponent.class, () -> pos);
        final var collComponent = entity.getComponent(CollisionComponent.class).get();
        // The position of the collisions center should be the same as the nitified
        // position
        assertEquals(pos, collComponent.getShape().getCenter());
    }

    @Test
    void testCollisionReceiveUnwantedMessage() {
        final var entity = this.baseEntity();
        final var pos = new Vector2D(5, 5);
        // Notifys unnecepted message
        entity.notifyComponent(CollisionComponent.class, () -> "Go to position 5:5");
        final var collComponent = entity.getComponent(CollisionComponent.class).get();
        // The position of the collisions center should be unchanged
        assertNotEquals(pos, collComponent.getShape().getCenter());
    }

    @Test
    void testComunicationPhysicsWithCollision() {
        final var entity = this.baseEntity();
        // Notifys the direction
        entity.notifyComponent(PhysicsComponent.class, () -> Directions.UP);
        final var physComponent = entity.getComponent(PhysicsComponent.class).get();
        // Notifys the collision component of the new position
        physComponent.update();
        final var collComponent = entity.getComponent(CollisionComponent.class).get();
        // The position of the entity should be the same as the collisions center
        assertEquals(entity.getPosition(), collComponent.getShape().getCenter());
    }
}
