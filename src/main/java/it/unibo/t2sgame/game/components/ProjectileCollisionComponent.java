package it.unibo.t2sgame.game.components;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;

public class ProjectileCollisionComponent extends CollisionComponent {

    private final EventFactory eventFactory = new EventFactoryImpl();

    /**
     * 
     * @see {@link it.unibo.t2sgame.core.components.impl.CollisionComponent#CollisionComponent(Shape, isRigid, types)
     *      CollisionComponent constructor}
     */
    public ProjectileCollisionComponent(Shape shape, boolean isRigid, List<Type> types) {
        super(shape, isRigid, types);
    }

    @Override
    protected void collisionAction(Entity entity) {
        this.entity.getWorld().ifPresent(e -> e.notifyEvent(this.eventFactory.onDeathEvent(this.entity)));
    }

}
