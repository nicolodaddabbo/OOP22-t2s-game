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

public class EntityFactoryImpl implements EntityFactory {
    private final ComponentFactory componentFactory = new ComponentFactoryImpl();
    private final Random random = new Random();
    @Override
    public Entity createPlayer(final Vector2D position) {
        return new EntityImpl(position, Type.PLAYER)
            .addComponent(this.componentFactory.createInputComponentFrom(new KeyboardInputController()))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(1))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 60, 80), false , List.of(Type.ENEMY , Type.WALL)))
            .addComponent(this.componentFactory.createHealthComponentFrom(3))
            .addComponent(this.componentFactory.createShootComponentFrom(0.2, 1.5, 1, 20))
            .addComponent(this.componentFactory.createGraphicComponentWithSprite("player", 60, 80));
    }

    @Override
    public Entity createCompanion(final Vector2D position) {
        return new EntityImpl(position, Type.COMPANION)
            .addComponent(this.componentFactory.createInputComponentFrom(new ChasingAIInputController(Type.ENEMY)))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(1))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 60, 80), false , List.of(Type.WALL)))
            .addComponent(this.componentFactory.createDamageComponentFrom(1, 1))
            .addComponent(this.componentFactory.createGraphicComponentWithSprite("companion", 60, 80));
    }

    @Override
    public Entity createProjectile(final Vector2D position, final double speed, final int damage, final double size, final Directions direction) {
        return new EntityImpl(position, Type.PROJECTILE)
            .addComponent(this.componentFactory.createPhysicsComponentFrom(speed, direction))
            .addComponent(this.componentFactory.createProjectileCollisionComponentFrom(new Circle(position, size), false , List.of(Type.ENEMY , Type.WALL)))
            .addComponent(this.componentFactory.createDamageComponentFrom(damage, 0))
            .addComponent(this.componentFactory.createCircleGraphicComponent(size*2, size*2));
    }

    @Override
    public Entity createBaseEnemy(final Vector2D position) {
        return new EntityImpl(position, Type.ENEMY)
            .addComponent(this.componentFactory.createInputComponentFrom(new ChasingAIInputController(Type.PLAYER)))
            .addComponent(this.componentFactory.createHealthComponentFrom(1))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(0.5))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 60, 50), false , List.of(Type.PROJECTILE, Type.COMPANION)))
            .addComponent(this.componentFactory.createDamageComponentFrom(1, 1))
            .addComponent(this.componentFactory.createGraphicComponentWithSprite("ice_enemy", 60, 60));
    }

    @Override
    public Entity createGaussianEnemy(final Vector2D position) {
        return new EntityImpl(position, Type.ENEMY)
            .addComponent(this.componentFactory.createInputComponentFrom(new GaussianAIInputController(Type.PLAYER)))
            .addComponent(this.componentFactory.createHealthComponentFrom(1))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(1))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 60, 50), false , List.of(Type.PROJECTILE, Type.COMPANION)))
            .addComponent(this.componentFactory.createDamageComponentFrom(1, 1))
            .addComponent(this.componentFactory.createGraphicComponentWithSprite("fire_enemy", 60, 60));
    }

    @Override
    public Entity createWildEnemy(final Vector2D position){
        return new EntityImpl(position, Type.ENEMY)
            .addComponent(this.componentFactory.createInputComponentFrom(random.nextInt(2) == 0 ? new GaussianAIInputController(Type.PLAYER) : new ChasingAIInputController(Type.PLAYER)))
            .addComponent(this.componentFactory.createHealthComponentFrom(1))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(random.nextDouble(0.5, 2)))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 60, 50), false , List.of(Type.PROJECTILE, Type.COMPANION)))
            .addComponent(this.componentFactory.createDamageComponentFrom(1, 1))
            .addComponent(this.componentFactory.createGraphicComponentWithSprite("rainbow_enemy", 60, 60));
    }
    
    @Override
    public Entity createBossEnemy(final Vector2D position){
        return new EntityImpl(position, Type.ENEMY)
            .addComponent(this.componentFactory.createInputComponentFrom(new ChasingAIInputController(Type.PLAYER)))
            .addComponent(this.componentFactory.createHealthComponentFrom(10))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(0.25))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 200, 140), false , List.of(Type.PROJECTILE, Type.COMPANION)))
            .addComponent(this.componentFactory.createDamageComponentFrom(2, 1))
            .addComponent(this.componentFactory.createGraphicComponentWithSprite("crown_enemy", 200, 140));
    }

    @Override
    public Entity createWall(Vector2D position, double width, double height) {
        return new EntityImpl(position, Type.WALL)
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, width, height), true, List.of()));
    }
}
