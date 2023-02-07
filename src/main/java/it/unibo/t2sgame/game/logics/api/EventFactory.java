package it.unibo.t2sgame.game.logics.api;

import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.model.api.PowerUp;

/**
 * This interface model an EventFactory.
 */
public interface EventFactory {
    /**
     * @param projectile to shoot
     * @return an onShoot event
     */
    Event onShootEvent(Entity projectile);
    /**
     * @param entity to remove
     * @return an onDeath event
     */
    Event onDeathEvent(Entity entity);
    /**
     * @param powerUp to apply
     * @return an onPowerUp event
     */
    Event onPowerUpEvent(PowerUp powerUp);
}
