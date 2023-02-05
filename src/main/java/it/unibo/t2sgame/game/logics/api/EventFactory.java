package it.unibo.t2sgame.game.logics.api;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.model.api.PowerUp;

/**
 * This interface model an EventFactory
 */
public interface EventFactory {
    /**
     * @param shooter the entity that shot the projectile
     * @param direction the direction of the projectile
     * @return an onShoot event
     */
    public Event onShootEvent(Entity projectile);
    /**
     * @param entity to remove
     * @return an onDeath event
     */
    public Event onDeathEvent(Entity entity);
    /**
     * @param powerUp to apply
     * @return an onPowerUp event
     */
    public Event onPowerUpEvent(PowerUp powerUp);
}
