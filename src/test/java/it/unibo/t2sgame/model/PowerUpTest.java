package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;
import it.unibo.t2sgame.game.model.impl.PowerUpFactoryImpl;

public class PowerUpTest {
    private PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();
    private static final double FIRERATE_SECONDS = 1.5;
    private static final double PROJECTILE_SPEED = 1.0;
    private static final int PROJECTILE_DAMAGE = 1;
    private static final double PROJECTILE_SIZE = 20;
    private static final int HEALTH = 3;
    private static final double SPEED = 1;

    private Entity createBasePlayerEntity() {
        return new EntityImpl(new Vector2D(0, 0), Type.PLAYER)
        .addComponent(new PhysicsComponent(SPEED))
        .addComponent(new HealthComponent(HEALTH))
        .addComponent(new ShootComponent(FIRERATE_SECONDS, PROJECTILE_SPEED, PROJECTILE_DAMAGE, PROJECTILE_SIZE));
    }

    @Test
    void testHealthUpPowerUp() {
        var entity = this.createBasePlayerEntity();
        var healthComponent = entity.getComponent(HealthComponent.class);
        powerUpFactory.generateHealthUpPowerUp().obtain(entity);
        assertEquals(HEALTH+1, healthComponent.get().getHealth());
    }

    @Test
    void testDamageBoostPowerUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateDamageBoostPowerUp().obtain(entity);
        assertEquals(PROJECTILE_DAMAGE+1, shootComponent.get().getProjectileDamage());
    }

    @Test
    void testFireRatioPowerUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateFireRatioPowerUp().obtain(entity);
        assertEquals(FIRERATE_SECONDS-0.05, shootComponent.get().getfireRateSeconds());
    }

    @Test
    void testSpeedUpPowerUp() {
        var entity = this.createBasePlayerEntity();
        var physicsComponent = entity.getComponent(PhysicsComponent.class);
        powerUpFactory.generateSpeedUpPowerUp().obtain(entity);
        assertEquals(SPEED+0.25, physicsComponent.get().getSpeed());
    }

    @Test
    void testProjectileSpeedUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateProjectileSpeedUpPowerUp().obtain(entity);
        assertEquals(PROJECTILE_SPEED+0.25, shootComponent.get().getProjectileSpeed());
    }

    @Test
    void testProjectileSizeUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateProjectileSizeUpPowerUp().obtain(entity);
        assertEquals(PROJECTILE_SIZE+5, shootComponent.get().getProjectileSize());
    }
}
