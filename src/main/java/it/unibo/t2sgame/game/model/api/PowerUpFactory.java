package it.unibo.t2sgame.game.model.api;

import java.util.List;

public interface PowerUpFactory {
    PowerUp generateDamageBoostPowerUp();

    PowerUp generateFireRatioPowerUp();

    PowerUp generateHealthUpPowerUp();

    PowerUp generateSpeedUpPowerUp();

    PowerUp generateProjectileSpeedUpPowerUp();

    PowerUp generateProjectileSizeUpPowerUp();

    List<PowerUp> getObtainablePowerUpList();
}
