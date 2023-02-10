package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.game.ecs.api.Entity;
import it.unibo.t2sgame.game.ecs.api.Type;
import it.unibo.t2sgame.game.ecs.impl.EntityImpl;
import it.unibo.t2sgame.game.ecs.impl.HealthComponent;
import it.unibo.t2sgame.game.ecs.impl.PhysicsComponent;
import it.unibo.t2sgame.game.ecs.impl.ShootComponent;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;
import it.unibo.t2sgame.game.model.impl.PowerUpFactoryImpl;

class PowerUpTest {
    private final PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();
    private static final double FIRERATE_SECONDS = 1.5;
    private static final double PROJECTILE_SPEED = 1.0;
    private static final int PROJECTILE_DAMAGE = 1;
    private static final double PROJECTILE_SIZE = 20;
    private static final int HEALTH = 3;
    private static final double SPEED = 1;
    private static final double FIRERATE_SECONDS_POWERUP = 0.05;
    private static final double PROJECTILE_SPEED_POWERUP = 0.5;
    private static final int PROJECTILE_DAMAGE_POWERUP = 1;
    private static final double PROJECTILE_SIZE_POWERUP = 5;
    private static final int HEALTH_POWERUP = 1;
    private static final double SPEED_POWERUP = 0.25;

    private Entity createBasePlayerEntity() {
        return new EntityImpl(new Vector2D(0, 0), Type.PLAYER)
        .addComponent(new PhysicsComponent(SPEED))
        .addComponent(new HealthComponent(HEALTH))
        .addComponent(new ShootComponent(FIRERATE_SECONDS, PROJECTILE_SPEED, PROJECTILE_DAMAGE, PROJECTILE_SIZE));
    }

    @Test
    void testHealthUpPowerUp() {
        final var entity = this.createBasePlayerEntity();
        final var healthComponent = entity.getComponent(HealthComponent.class);
        powerUpFactory.generateHealthUpPowerUp().obtain(entity);
        assertEquals(HEALTH + HEALTH_POWERUP, healthComponent.get().getHealth());
    }

    @Test
    void testDamageBoostPowerUp() {
        final var entity = this.createBasePlayerEntity();
        final var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateDamageBoostPowerUp().obtain(entity);
        assertEquals(PROJECTILE_DAMAGE + PROJECTILE_DAMAGE_POWERUP, shootComponent.get().getProjectileDamage());
    }

    @Test
    void testFireRatioPowerUp() {
        final var entity = this.createBasePlayerEntity();
        final var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateFireRatioPowerUp().obtain(entity);
        assertEquals(FIRERATE_SECONDS - FIRERATE_SECONDS_POWERUP, shootComponent.get().getfireRateSeconds());
    }

    @Test
    void testSpeedUpPowerUp() {
        final var entity = this.createBasePlayerEntity();
        final var physicsComponent = entity.getComponent(PhysicsComponent.class);
        powerUpFactory.generateSpeedUpPowerUp().obtain(entity);
        assertEquals(SPEED + SPEED_POWERUP, physicsComponent.get().getSpeed());
    }

    @Test
    void testProjectileSpeedUp() {
        final var entity = this.createBasePlayerEntity();
        final var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateProjectileSpeedUpPowerUp().obtain(entity);
        assertEquals(PROJECTILE_SPEED + PROJECTILE_SPEED_POWERUP, shootComponent.get().getProjectileSpeed());
    }

    @Test
    void testProjectileSizeUp() {
        final var entity = this.createBasePlayerEntity();
        final var shootComponent = entity.getComponent(ShootComponent.class);
        powerUpFactory.generateProjectileSizeUpPowerUp().obtain(entity);
        assertEquals(PROJECTILE_SIZE + PROJECTILE_SIZE_POWERUP, shootComponent.get().getProjectileSize());
    }
}
