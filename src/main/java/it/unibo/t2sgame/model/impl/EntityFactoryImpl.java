package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.impl.InputComponentFactoryImpl;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.EntityFactory;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createPlayer() {
        var entity = new EntityImpl();
        entity.setPosition(new Vector2D(0, 0));
        return entity.addComponent(new InputComponentFactoryImpl().createKeyboardInputComponent());
    }

    @Override
    public Entity createProjectile() {
        return null;
    }

    @Override
    public Entity createBaseEnemy() {
        var entity = new EntityImpl();
        entity.setPosition(new Vector2D(0, 0));
        return entity.addComponent(new InputComponentFactoryImpl().createKeyboardInputComponent());
    }
    
}
