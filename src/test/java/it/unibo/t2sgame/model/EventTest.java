package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;
import it.unibo.t2sgame.game.model.impl.PowerUpFactoryImpl;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.api.WorldFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WorldFactoryImpl;
import it.unibo.t2sgame.input.api.Directions;

public class EventTest {
    WorldFactory worldFactory = new WorldFactoryImpl();
    World world = worldFactory.createWorldWithOnePlayer();
    EventFactory eventFactory = new EventFactoryImpl();
    EntityFactory entityFactory = new EntityFactoryImpl();
    Entity player;
    Entity enemy = new EntityImpl(new Vector2D(0, 0), Type.ENEMY);
    PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();

    public EventTest() {
        this.player = this.entityFactory.createPlayer(new Vector2D(0, 0));
        this.player.setWorld(this.world);
        this.world.addEntity(player);
        this.world.addEntity(enemy);
    }

    @Test
    void onShootEventTest() {
        var projectile = this.entityFactory.createProjectile(this.player.getPosition(), 0.5, 1, 0.5, Directions.DOWN);
        this.world.notifyEvent(this.eventFactory.onShootEvent(projectile));
        // The projectile should NOT exist after the notification of its shooting
        this.world.handleEvents();
        // The world run the onShoot event that creates the projectile when the handleEvents method get called
        assertTrue(this.world.getEntities().stream().filter(e -> e.getType() == Type.PROJECTILE).findFirst().isPresent());
    }

    @Test
    void onDeathEventTest() {
        this.world.notifyEvent(this.eventFactory.onDeathEvent(enemy));
        // The enemy should still exist after the notification of his death
        assertTrue(this.world.getEntities().stream().filter(e -> e.getType() == Type.ENEMY).findFirst().isPresent());
        // The world runs the onDeath event that removes the death entities when the handleEvents method get called
        this.world.handleEvents();
        assertFalse(this.world.getEntities().stream().filter(e -> e.getType() == Type.ENEMY).findFirst().isPresent());
    }

    @Test
    void onPowerUpEventTest() {
        var playerShootComponent = this.player.getComponent(ShootComponent.class).get();
        var initialDamage = playerShootComponent.getProjectileDamage();
        this.world.notifyEvent(this.eventFactory.onPowerUpEvent(this.powerUpFactory.generateDamageBoostPowerUp()));
        // The player projectile damage should be the same after the notification of the power up
        assertEquals(initialDamage, playerShootComponent.getProjectileDamage());
        // The world runs the onPowerUp event that gives the power up to the player when the handleEvents method get called
        this.world.handleEvents();
        assertTrue(playerShootComponent.getProjectileDamage() > initialDamage);
    }
}
