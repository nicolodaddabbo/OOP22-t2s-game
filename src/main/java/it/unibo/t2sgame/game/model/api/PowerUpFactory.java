package it.unibo.t2sgame.game.model.api;

public interface PowerUpFactory {
    PowerUp generateDamageBoostPowerUp();

    PowerUp generateFireRatioPowerUp();

    PowerUp generateHealthUpPowerUp();
}
