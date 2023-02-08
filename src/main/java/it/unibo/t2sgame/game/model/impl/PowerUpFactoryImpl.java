package it.unibo.t2sgame.game.model.impl;

import java.util.List;
import java.util.function.Consumer;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.game.model.api.PowerUp;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;
/**
 * class that implements interface PowerUpFactory.
 */
public class PowerUpFactoryImpl implements PowerUpFactory {
    private static final int DMGUP = 1;
    private static final int PROJSPEEDUP = 1;
    private static final int PROJSIZEUP = 5;
    private static final double FIRERATEUP = 0.05;
    private static final int HEALTHUP = 1;
    private static final double SPEEDUP = 0.25;
    /**
     * method that receives a consumer containing the power up to apply.
     * @param function the consumer containing the power up
     * @return a PowerUp that depends on the consumer
     */
    private PowerUp fromFunction(final Consumer<Entity> function) {
        return function::accept;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp generateDamageBoostPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setProjectileDamage(c.getProjectileDamage() + DMGUP)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp generateProjectileSpeedUpPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setProjectileSpeed(c.getProjectileSpeed() + PROJSPEEDUP)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp generateProjectileSizeUpPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setProjectileSize(c.getProjectileSize() + PROJSIZEUP)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp generateFireRatioPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setfireRateSeconds(c.getfireRateSeconds() - FIRERATEUP)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp generateHealthUpPowerUp() {
        return fromFunction(
                entity -> entity.getComponent(HealthComponent.class).ifPresent(c -> c.setHealth(c.getHealth() + HEALTHUP)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp generateSpeedUpPowerUp() {
        return fromFunction(
                entity -> entity.getComponent(PhysicsComponent.class).ifPresent(c -> c.setSpeed(c.getSpeed() + SPEEDUP)));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<PowerUp> getObtainablePowerUpList() {
        return List.of(this.generateDamageBoostPowerUp(), this.generateFireRatioPowerUp(),
                this.generateHealthUpPowerUp(), this.generateProjectileSizeUpPowerUp(),
                this.generateProjectileSpeedUpPowerUp(), this.generateSpeedUpPowerUp());
    }
}
