package it.unibo.t2sgame.core;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.common.shapes.Rectangle;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.ComponentFactoryImpl;
import it.unibo.t2sgame.core.components.impl.HealthComponent;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.input.api.Directions;

public class MessageTest {

    private ComponentFactory componentFactory = new ComponentFactoryImpl();

    private Entity createEntityWithPhysics(){
        Entity entity = new EntityImpl(new Vector2D(0,0));
        entity.addComponent(this.componentFactory.createPhysicsComponentFrom(1));
        return entity;
    }

    private Entity createEntityWithCollision(){
        var pos = new Vector2D(0,0);
        Entity entity = new EntityImpl(pos);
        entity.addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(pos, 0, 0), false));
        return entity;
    }

    private Entity createEntityWithPhysicsAndCollision(){
        var pos = new Vector2D(0,0);
        Entity entity = new EntityImpl(pos);
        entity.addComponent(this.componentFactory.createPhysicsComponentFrom(1))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(pos, 0, 0), false));
        return entity;
    }
    
    private Entity createEntityWithHealth(){
        Entity entity = new EntityImpl(new Vector2D(0,0));
        entity.addComponent(this.componentFactory.createHealthComponentFrom(3));
        return entity;
    }

    @Test
    void testPhysicsReceiveDirection(){
        var entity = createEntityWithPhysics();
        entity.notifyComponent(PhysicsComponent.class, () -> Directions.UP);
        var physComponent = entity.getComponent(PhysicsComponent.class).get();
        physComponent.update();
        assertEquals(new Vector2D(0, -1), physComponent.getVelocity());
    }

    @Test
    void testCollisionReceivePosition(){
        var entity = createEntityWithCollision();
        var pos = new Vector2D(5, 5);
        entity.notifyComponent(CollisionComponent.class, () -> pos);
        var collComponent = entity.getComponent(CollisionComponent.class).get();
        assertEquals(pos, collComponent.getShape().getCenter());
    }

    @Test
    void testHealthReceiveDamage(){
        var entity = createEntityWithHealth();
        entity.notifyComponent(HealthComponent.class, () -> 2);
        var hpComponent = entity.getComponent(HealthComponent.class).get();
        assertEquals(1, hpComponent.getHealth());
    }

    @Test
    void testComunicationPhysicsWithCollision(){
        var entity = createEntityWithPhysicsAndCollision();
        entity.notifyComponent(PhysicsComponent.class, () -> Directions.UP);
        var physComponent = entity.getComponent(PhysicsComponent.class).get();
        physComponent.update();
        var collComponent = entity.getComponent(CollisionComponent.class).get();
        assertEquals(entity.getPosition(), collComponent.getShape().getCenter());
    }
}
