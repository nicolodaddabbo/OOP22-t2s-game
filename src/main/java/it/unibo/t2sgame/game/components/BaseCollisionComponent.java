package it.unibo.t2sgame.game.components;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;

public class BaseCollisionComponent extends CollisionComponent {

    /**
     * 
     * @see {@link it.unibo.t2sgame.core.components.impl.CollisionComponent#CollisionComponent(Shape, isRigid, types) CollisionComponent constructor}
     */
    public BaseCollisionComponent(Shape shape, boolean isRigid , List<Type> types) {
        super(shape, isRigid, types);
    }

    @Override
    protected void collisionAction(Entity collisionEntity) {
        // Remove health to this entity
        collisionEntity.getComponent(DamageComponent.class)
            .ifPresent(c -> {
                if (c.canDamage()) {
                    this.entity.notifyComponent(HealthComponent.class, c::getDamage);
                }
            });
    }
    
}
