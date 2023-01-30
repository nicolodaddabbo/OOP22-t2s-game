package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.entity.api.Entity;

public class BaseCollisionComponent extends CollisionComponent {

    public BaseCollisionComponent(Shape shape, boolean isRigid) {
        super(shape, isRigid);
    }

    @Override
    protected void collisionAction(Entity collisionEntity) {
        // Remove health to the touched entity
        this.entity.getComponent(DamageComponent.class)
            .ifPresent(c -> {
                if (c.canDamage()) {
                    collisionEntity.notifyComponent(HealthComponent.class, c::getDamage);
                }
            });
        // Remove health to this entity
        collisionEntity.getComponent(DamageComponent.class)
            .ifPresent(c -> {
                if (c.canDamage()) {
                    this.entity.notifyComponent(HealthComponent.class, c::getDamage);
                }
            });
    }
    
}
