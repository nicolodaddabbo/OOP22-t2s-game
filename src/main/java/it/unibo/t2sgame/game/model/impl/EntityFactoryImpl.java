package it.unibo.t2sgame.game.model.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.common.shapes.Rectangle;
import it.unibo.t2sgame.core.components.api.GraphicComponentFactory;
import it.unibo.t2sgame.core.components.api.InputComponentFactory;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.DamageComponent;
import it.unibo.t2sgame.core.components.impl.GraphicComponentFactoryImpl;
import it.unibo.t2sgame.core.components.impl.HealthComponentImpl;
import it.unibo.t2sgame.core.components.impl.InputComponentFactoryImpl;
import it.unibo.t2sgame.core.components.impl.PhysicsComponentImpl;
import it.unibo.t2sgame.core.components.impl.ShootComponentImpl;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.input.api.Directions;

public class EntityFactoryImpl implements EntityFactory {

    private final InputComponentFactory inputFactory = new InputComponentFactoryImpl();
    private final GraphicComponentFactory graphicFactory = new GraphicComponentFactoryImpl();

    @Override
    public Entity createPlayer(final Vector2D position) {
        return new EntityImpl(position)
            .addComponent(this.inputFactory.createKeyboardInputComponent())
            .addComponent(new PhysicsComponentImpl(1))
            .addComponent(new CollisionComponent(new Rectangle(position, 60, 80), false))
            .addComponent(this.graphicFactory.getPlayerGraphicComponent())
            .addComponent(new HealthComponentImpl(3))
            .addComponent(new ShootComponentImpl(1));
    }

    @Override
    public Entity createProjectile(final Vector2D position, final Directions direction) {
        return new EntityImpl(position)
            .addComponent(new PhysicsComponentImpl(1.5, direction))
            .addComponent(new CollisionComponent(new Rectangle(position, 60, 80), false))
            .addComponent(this.graphicFactory.getProjectileGraphicComponent())
            .addComponent(new DamageComponent(1, 1));
    }

    @Override
    public Entity createBaseEnemy(final Vector2D position) {
        return new EntityImpl(position)
            .addComponent(this.inputFactory.createBasicEnemyAIInputComponent())
            .addComponent(new PhysicsComponentImpl(0.25))
            .addComponent(new CollisionComponent(new Rectangle(position, 60, 80), false))
            .addComponent(new DamageComponent(1, 1))
            .addComponent(this.graphicFactory.getBaseEnemyGraphicComponent());
    }
    
}
