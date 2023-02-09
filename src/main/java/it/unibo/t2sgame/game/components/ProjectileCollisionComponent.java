package it.unibo.t2sgame.game.components;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;

/**
 * This class represents the collision of a projectile.
 */
public class ProjectileCollisionComponent extends CollisionComponent {

    private final EventFactory eventFactory = new EventFactoryImpl();

    /**
     * 
     * @param shape   the shape of the collision
     * @param isRigid if true, the collision is rigid and cant be passed through.
     *                Otherwise not
     * @param types   the types of entity that the collision collides with
     */
    public ProjectileCollisionComponent(final Shape shape, final boolean isRigid, final List<Type> types) {
        super(shape, isRigid, types);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisionAction(final Entity entity) {
        this.getEntity().getWorld().ifPresent(e -> e.notifyEvent(this.eventFactory.onDeathEvent(this.getEntity())));
    }

}
