package it.unibo.t2sgame.game.logics.impl;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.api.Event;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.model.api.PowerUp;

public class EventFactoryImpl implements EventFactory {

    @Override
    public Event onShootEvent(final Entity projectile) {
        return world -> world.addEntity(projectile);
    }

    @Override
    public Event onDeathEvent(final Entity entity) {
        return world -> {
            world.removeEntity(entity);
            world.getCurrentWave().ifPresent(w -> w.getEnemies().remove(entity));
        };
    }

    @Override
    public Event onPowerUpEvent(final PowerUp powerUp) {
        return world -> world.getPlayers().forEach(powerUp::obtain);
    }
    
}
