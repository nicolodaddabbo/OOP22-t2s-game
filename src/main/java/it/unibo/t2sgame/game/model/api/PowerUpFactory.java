package it.unibo.t2sgame.game.model.api;

import java.util.List;
/**
 * interface describing a PowerUp factory.
 */
public interface PowerUpFactory {
    /**
     * @return a damage up PowerUp
     */
    PowerUp generateDamageBoostPowerUp();
    /**
     * @return a damage up PowerUp
     */
    PowerUp generateFireRatioPowerUp();
    /**
     * @return a damage up PowerUp
     */
    PowerUp generateHealthUpPowerUp();
    /**
     * @return a damage up PowerUp
     */
    PowerUp generateSpeedUpPowerUp();
    /**
     * @return a damage up PowerUp
     */
    PowerUp generateProjectileSpeedUpPowerUp();
    /**
     * @return a damage up PowerUp
     */
    PowerUp generateProjectileSizeUpPowerUp();
    /**
     * @return a list of all obtainable power ups
     */
    List<PowerUp> getObtainablePowerUpList();
}
