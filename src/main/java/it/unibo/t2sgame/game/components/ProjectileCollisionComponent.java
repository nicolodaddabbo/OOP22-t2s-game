package it.unibo.t2sgame.game.components;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.TypeComponent.Type;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;

public class ProjectileCollisionComponent extends BaseCollisionComponent {

    private EventFactory eventFactory = new EventFactoryImpl();

    public ProjectileCollisionComponent(Shape shape, boolean isRigid, List<Type> types) {
        super(shape, isRigid, types);
    }

    @Override
    protected void collisionAction(Entity entity) {
        super.collisionAction(entity);
        this.entity.getWorld().ifPresent(e -> e.notifyEvent(this.eventFactory.onDeathEvent(this.entity)));
    }
    
}
