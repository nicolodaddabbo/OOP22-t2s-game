package it.unibo.t2sgame.game.model.impl;

import java.util.List;
import java.util.Random;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.common.shapes.Circle;
import it.unibo.t2sgame.common.shapes.Rectangle;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.components.impl.ComponentFactoryImpl;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.impl.ChasingAIInputController;
import it.unibo.t2sgame.input.impl.GaussianAIInputController;
import it.unibo.t2sgame.input.impl.KeyboardInputController;

/**
 * This class implements the EntityFactory interface, it is used to create
 * entities.
 */
public class EntityFactoryImpl implements EntityFactory {
    private final ComponentFactory componentFactory = new ComponentFactoryImpl();
    private final Random random = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPlayer(final Vector2D position) {
        return new EntityImpl(position, Type.PLAYER)
                .addComponent(this.componentFactory.createInputComponentFrom(new KeyboardInputController()))
                .addComponent(this.componentFactory.createPhysicsComponentFrom(Stats.Player.MOVEMENT_SPEED))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(position, Stats.Player.COLLISION_WIDTH, Stats.Player.COLLISION_HEIGHT), false,
                        List.of(Type.ENEMY, Type.WALL)))
                .addComponent(this.componentFactory.createHealthComponentFrom(Stats.Player.HEALTH))
                .addComponent(this.componentFactory.createShootComponentFrom(Stats.Player.FIRERATE_SECONDS,
                        Stats.Player.PROJECTILE_SPEED, Stats.Player.PROJECTILE_DAMAGE, Stats.Player.PROJECTILE_SIZE))
                .addComponent(
                        this.componentFactory.createGraphicComponentWithSprite("player", Stats.Player.RENDER_WIDTH,
                                Stats.Player.RENDER_HEIGHT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createCompanion(final Vector2D position) {
        return new EntityImpl(position, Type.COMPANION)
                .addComponent(this.componentFactory.createInputComponentFrom(new ChasingAIInputController(Type.ENEMY)))
                .addComponent(this.componentFactory.createPhysicsComponentFrom(Stats.Companion.MOVEMENT_SPEED))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(position, Stats.Companion.COLLISION_WIDTH, Stats.Companion.COLLISION_HEIGHT),
                        false, List.of(Type.WALL)))
                .addComponent(this.componentFactory.createDamageComponentFrom(Stats.Companion.DAMAGE,
                        Stats.Companion.COOLDOWN_SECONDS))
                .addComponent(
                        this.componentFactory.createGraphicComponentWithSprite("companion",
                                Stats.Companion.RENDER_WIDTH, Stats.Companion.RENDER_HEIGHT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createProjectile(final Vector2D position, final double speed, final int damage, final double size,
            final Directions direction) {
        return new EntityImpl(position, Type.PROJECTILE)
                .addComponent(this.componentFactory.createPhysicsComponentFrom(speed, direction))
                .addComponent(this.componentFactory.createProjectileCollisionComponentFrom(new Circle(position, size),
                        false, List.of(Type.ENEMY, Type.WALL)))
                .addComponent(this.componentFactory.createDamageComponentFrom(damage, 0))
                .addComponent(this.componentFactory.createCircleGraphicComponent(size * 2, size * 2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBaseEnemy(final Vector2D position) {
        return new EntityImpl(position, Type.ENEMY)
                .addComponent(this.componentFactory.createInputComponentFrom(new ChasingAIInputController(Type.PLAYER)))
                .addComponent(this.componentFactory.createHealthComponentFrom(Stats.Enemy.HEALTH))
                .addComponent(this.componentFactory.createPhysicsComponentFrom(Stats.Enemy.MOVEMENT_SPEED))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(position, Stats.Enemy.COLLISION_WIDTH, Stats.Enemy.COLLISION_HEIGHT),
                        false, List.of(Type.PROJECTILE, Type.COMPANION)))
                .addComponent(this.componentFactory.createDamageComponentFrom(Stats.Enemy.DAMAGE,
                        Stats.Enemy.DAMAGE_COOLDOWN))
                .addComponent(
                        this.componentFactory.createGraphicComponentWithSprite("ice_enemy", Stats.Enemy.RENDER_WIDTH,
                                Stats.Enemy.RENDER_HEIGHT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createGaussianEnemy(final Vector2D position) {
        return new EntityImpl(position, Type.ENEMY)
                .addComponent(
                        this.componentFactory.createInputComponentFrom(new GaussianAIInputController(Type.PLAYER)))
                .addComponent(this.componentFactory.createHealthComponentFrom(Stats.GaussianEnemy.HEALTH))
                .addComponent(this.componentFactory.createPhysicsComponentFrom(Stats.GaussianEnemy.MOVEMENT_SPEED))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(position, Stats.GaussianEnemy.COLLISION_WIDTH,
                                Stats.GaussianEnemy.COLLISION_HEIGHT),
                        false, List.of(Type.PROJECTILE, Type.COMPANION)))
                .addComponent(this.componentFactory.createDamageComponentFrom(Stats.GaussianEnemy.DAMAGE,
                        Stats.GaussianEnemy.DAMAGE_COOLDOWN))
                .addComponent(
                        this.componentFactory.createGraphicComponentWithSprite("fire_enemy",
                                Stats.GaussianEnemy.RENDER_WIDTH, Stats.GaussianEnemy.RENDER_HEIGHT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWildEnemy(final Vector2D position) {
        return new EntityImpl(position, Type.ENEMY)
                .addComponent(this.componentFactory.createInputComponentFrom(
                        random.nextInt(2) == 0
                                ? new GaussianAIInputController(Type.PLAYER)
                                : new ChasingAIInputController(Type.PLAYER)))
                .addComponent(this.componentFactory.createHealthComponentFrom(Stats.WildEnemy.HEALTH))
                .addComponent(this.componentFactory.createPhysicsComponentFrom(Stats.WildEnemy.MOVEMENT_SPEED))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(position, Stats.WildEnemy.COLLISION_WIDTH, Stats.WildEnemy.COLLISION_HEIGHT),
                        false, List.of(Type.PROJECTILE, Type.COMPANION)))
                .addComponent(this.componentFactory.createDamageComponentFrom(Stats.WildEnemy.DAMAGE,
                        Stats.WildEnemy.DAMAGE_COOLDOWN))
                .addComponent(
                        this.componentFactory.createGraphicComponentWithSprite("rainbow_enemy",
                                Stats.WildEnemy.RENDER_WIDTH, Stats.WildEnemy.RENDER_HEIGHT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBossEnemy(final Vector2D position) {
        return new EntityImpl(position, Type.ENEMY)
                .addComponent(this.componentFactory.createInputComponentFrom(new ChasingAIInputController(Type.PLAYER)))
                .addComponent(this.componentFactory.createHealthComponentFrom(Stats.BossEnemy.HEALTH))
                .addComponent(this.componentFactory.createPhysicsComponentFrom(Stats.BossEnemy.MOVEMENT_SPEED))
                .addComponent(this.componentFactory.createCollisionComponentFrom(
                        new Rectangle(position, Stats.BossEnemy.COLLISION_WIDTH, Stats.BossEnemy.COLLISION_HEIGHT),
                        false, List.of(Type.PROJECTILE, Type.COMPANION)))
                .addComponent(this.componentFactory.createDamageComponentFrom(Stats.BossEnemy.DAMAGE,
                        Stats.BossEnemy.DAMAGE_COOLDOWN))
                .addComponent(
                        this.componentFactory.createGraphicComponentWithSprite("crown_enemy",
                                Stats.BossEnemy.RENDER_WIDTH, Stats.BossEnemy.RENDER_HEIGHT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWall(final Vector2D position, final double width, final double height) {
        return new EntityImpl(position, Type.WALL)
                .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, width, height),
                        true, List.of()));
    }
}
