package it.unibo.t2sgame.game.model.api;

import it.unibo.t2sgame.core.entity.api.Entity;

public interface PowerUpFactory {
    PowerUp generateDamageBoostPowerUp(Entity entity);

    PowerUp generateFireRatioPowerUp(Entity entity);

    PowerUp generateHealthUpPowerUp(Entity entity);
}
