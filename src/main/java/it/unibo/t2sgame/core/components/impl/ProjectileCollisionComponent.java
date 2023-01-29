package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;

public class ProjectileCollisionComponent extends CollisionComponent {

    private EventFactory eventFactory = new EventFactoryImpl();

    protected ProjectileCollisionComponent(Shape shape, boolean isRigid) {
        super(shape, isRigid);
    }

    @Override
    protected void collisionAction(Entity entity) {
        super.collisionAction(entity);
        this.entity.getWorld().ifPresent(world -> world.notifyEvent(this.eventFactory.onDeathEvent(this.entity)));
    }
    
}
