package it.unibo.t2sgame.game.logics.impl;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.Event;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.input.api.Directions;

public class EventFactoryImpl implements EventFactory {
    EntityFactory entityFactory = new EntityFactoryImpl();
    
    public Event onShootEvent(final Entity shooter, final Directions direction) {
        return world -> world.addEntity(entityFactory.createProjectile(shooter.getPosition(), direction));
    }

}
