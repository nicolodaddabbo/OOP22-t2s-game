package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.impl.InputComponentFactoryImpl;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.EntityFactory;
import it.unibo.t2sgame.view.impl.GraphicComponentFactoryImpl;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createPlayer(final Vector2D position) {
        return new EntityImpl(position)
            .addComponent(new InputComponentFactoryImpl().createKeyboardInputComponent())
            .addComponent(null)
            .addComponent(new GraphicComponentFactoryImpl().getPlayerGraphicComponent());
    }

    @Override
    public Entity createProjectile(final Vector2D position) {
        return new EntityImpl(position)
            .addComponent(null)
            .addComponent(new GraphicComponentFactoryImpl().getProjectileGraphicComponent());
    }

    @Override
    public Entity createBaseEnemy(final Vector2D position) {
        return new EntityImpl(position)
            .addComponent(new InputComponentFactoryImpl().createBasicEnemyAIInputComponent())
            .addComponent(null)
            .addComponent(new GraphicComponentFactoryImpl().getBaseEnemyGraphicComponent());
    }
    
}
