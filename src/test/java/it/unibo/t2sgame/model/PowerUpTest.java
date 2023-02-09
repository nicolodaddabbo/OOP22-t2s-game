package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.game.model.impl.PowerUpFactoryImpl;

public class PowerUpTest {
    private PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();

    private Entity createBasePlayerEntity() {
        return new EntityFactoryImpl().createPlayer(new Vector2D(0, 0));
    }

    @Test
    void testHealthUpPowerUp() {
        var entity = this.createBasePlayerEntity();
        var healthComponent = entity.getComponent(HealthComponent.class);
        assertTrue(healthComponent.isPresent());
        assertEquals(3, healthComponent.get().getHealth());
        powerUpFactory.generateHealthUpPowerUp().obtain(entity);
        assertEquals(4, healthComponent.get().getHealth());
    }

    @Test
    void testDamageBoostPowerUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        assertTrue(shootComponent.isPresent());
        assertEquals(1, shootComponent.get().getProjectileDamage());
        powerUpFactory.generateDamageBoostPowerUp().obtain(entity);
        assertEquals(2, shootComponent.get().getProjectileDamage());
    }

    @Test
    void testFireRatioPowerUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        assertTrue(shootComponent.isPresent());
        assertEquals(0.5, shootComponent.get().getfireRateSeconds());
        powerUpFactory.generateFireRatioPowerUp().obtain(entity);
        assertEquals(0.45, shootComponent.get().getfireRateSeconds());
    }

    @Test
    void testSpeedUpPowerUp() {
        var entity = this.createBasePlayerEntity();
        var physicsComponent = entity.getComponent(PhysicsComponent.class);
        assertTrue(physicsComponent.isPresent());
        assertEquals(1, physicsComponent.get().getSpeed());
        powerUpFactory.generateSpeedUpPowerUp().obtain(entity);
        assertEquals(1.25, physicsComponent.get().getSpeed());
    }

    @Test
    void testProjectileSpeedUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        assertTrue(shootComponent.isPresent());
        assertEquals(1.5, shootComponent.get().getProjectileSpeed());
        powerUpFactory.generateProjectileSpeedUpPowerUp().obtain(entity);
        assertEquals(2.5, shootComponent.get().getProjectileSpeed());
    }

    @Test
    void testProjectileSizeUp() {
        var entity = this.createBasePlayerEntity();
        var shootComponent = entity.getComponent(ShootComponent.class);
        assertTrue(shootComponent.isPresent());
        assertEquals(20.0, shootComponent.get().getProjectileSize());
        powerUpFactory.generateProjectileSizeUpPowerUp().obtain(entity);
        assertEquals(25.0, shootComponent.get().getProjectileSize());
    }
}
