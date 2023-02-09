package it.unibo.t2sgame.game.components;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;

/**
 * This class represents the basic collision of an entity.
 */
public class BaseCollisionComponent extends CollisionComponent {

    /**
     * 
     * @param shape   the shape of the collision
     * @param isRigid if true, the collision is rigid and cant be passed through.
     *                Otherwise not
     * @param types   the types of entity that the collision collides with
     */
    public BaseCollisionComponent(final Shape shape, final boolean isRigid, final List<Type> types) {
        super(shape, isRigid, types);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisionAction(final Entity collisionEntity) {
        // Remove health to this entity
        collisionEntity.getComponent(DamageComponent.class)
                .ifPresent(c -> {
                    if (c.canDamage()) {
                        this.getEntity().notifyComponent(HealthComponent.class, c::getDamage);
                    }
                });
    }

}
