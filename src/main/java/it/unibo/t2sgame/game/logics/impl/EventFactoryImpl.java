package it.unibo.t2sgame.game.logics.impl;

import it.unibo.t2sgame.game.ecs.api.Entity;
import it.unibo.t2sgame.game.logics.api.Event;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.model.api.PowerUp;

/**
 * EventFactory implementation.
 */
public class EventFactoryImpl implements EventFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Event onShootEvent(final Entity projectile) {
        return world -> world.addEntity(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event onDeathEvent(final Entity entity) {
        return world -> {
            world.removeEntity(entity);
            world.getCurrentWave().ifPresent(w -> w.getEnemies().remove(entity));
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event onPowerUpEvent(final PowerUp powerUp) {
        return world -> world.getPlayers().forEach(powerUp::obtain);
    }

}
