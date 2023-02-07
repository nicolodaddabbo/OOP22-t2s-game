package it.unibo.t2sgame.game.model.impl;

import java.util.List;
import java.util.function.Consumer;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.game.model.api.PowerUp;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;

public class PowerUpFactoryImpl implements PowerUpFactory {
    public PowerUp fromFunction(final Consumer<Entity> function) {
        return function::accept;
    }

    @Override
    public PowerUp generateDamageBoostPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setProjectileDamage(c.getProjectileDamage() + 1)));
    }

    @Override
    public PowerUp generateProjectileSpeedUpPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setProjectileSpeed(c.getProjectileSpeed() + 1)));
    }

    @Override
    public PowerUp generateProjectileSizeUpPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setProjectileSize(c.getProjectileSize() + 5)));
    }

    @Override
    public PowerUp generateFireRatioPowerUp() {
        return fromFunction(entity -> entity.getComponent(ShootComponent.class)
                .ifPresent(c -> c.setfireRateSeconds(c.getfireRateSeconds() - 0.05)));
    }

    @Override
    public PowerUp generateHealthUpPowerUp() {
        return fromFunction(
                entity -> entity.getComponent(HealthComponent.class).ifPresent(c -> c.setHealth(c.getHealth() + 1)));
    }

    @Override
    public PowerUp generateSpeedUpPowerUp() {
        return fromFunction(
                entity -> entity.getComponent(PhysicsComponent.class).ifPresent(c -> c.setSpeed(c.getSpeed() + 0.25)));
    }

    @Override
    public List<PowerUp> getObtainablePowerUpList() {
        return List.of(this.generateDamageBoostPowerUp(), this.generateFireRatioPowerUp(),
                this.generateHealthUpPowerUp(), this.generateProjectileSizeUpPowerUp(),
                this.generateProjectileSpeedUpPowerUp(), this.generateSpeedUpPowerUp());
    }
}
