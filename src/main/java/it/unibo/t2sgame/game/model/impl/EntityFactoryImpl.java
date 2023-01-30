package it.unibo.t2sgame.game.model.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.common.shapes.Circle;
import it.unibo.t2sgame.common.shapes.Rectangle;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.components.impl.ComponentFactoryImpl;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.impl.BasicEnemyAIInputController;
import it.unibo.t2sgame.input.impl.KeyboardInputController;

public class EntityFactoryImpl implements EntityFactory {
    private final ComponentFactory componentFactory = new ComponentFactoryImpl();

    @Override
    public Entity createPlayer(final Vector2D position) {
        return new EntityImpl(position)
            .addComponent(this.componentFactory.createInputComponentFrom(new KeyboardInputController()))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(1))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 60, 80), false))
            .addComponent(this.componentFactory.createHealthComponentFrom(3))
            .addComponent(this.componentFactory.createShootComponentFrom(1))
            .addComponent(this.componentFactory.createPlayerGraphicComponent());
    }

    @Override
    public Entity createProjectile(final Vector2D position, final Directions direction) {
        return new EntityImpl(position)
            .addComponent(this.componentFactory.createPhysicsComponentFrom(1.5, direction))
            .addComponent(this.componentFactory.createProjectileCollisionComponentFrom(new Circle(position, 30), false))
            .addComponent(this.componentFactory.createDamageComponentFrom(1, 1))
            .addComponent(this.componentFactory.createProjectileGraphicComponent());
    }

    @Override
    public Entity createBaseEnemy(final Vector2D position) {
        return new EntityImpl(position)
            .addComponent(this.componentFactory.createInputComponentFrom(new BasicEnemyAIInputController()))
            .addComponent(this.componentFactory.createPhysicsComponentFrom(0.25))
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, 60, 80), false))
            .addComponent(this.componentFactory.createDamageComponentFrom(1, 1))
            .addComponent(this.componentFactory.createBaseEnemyGraphicComponent());
    }

    @Override
    public Entity createWall(Vector2D position, double width, double height) {
        return new EntityImpl(position)
            .addComponent(this.componentFactory.createCollisionComponentFrom(new Rectangle(position, width, height), true));
    }
    
}
