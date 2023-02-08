package it.unibo.t2sgame.game.model.api;

import it.unibo.t2sgame.core.entity.api.Entity;
/**
 * functional interface that represents a power up.
 */
@FunctionalInterface
public interface PowerUp {
    /**
     * method called to obtain/gain a power up.
     * @param entity that receives the power up
     */
    void obtain(Entity entity);
}
