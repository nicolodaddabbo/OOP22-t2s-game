package it.unibo.t2sgame.game.logics.impl;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.Event;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.input.api.Directions;

public class EventFactoryImpl {
    
    public Event onShootEvent(final Entity shooter, final Directions direction) {
        EntityFactory entityFactory = new EntityFactoryImpl();
        return world -> world.addEntity(entityFactory.createProjectile(shooter.getPosition(), direction));
    }

}
