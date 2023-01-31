package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;

public class TypeComponent extends AbstractComponent {

    public enum Type {
        PLAYER, ENEMY, PROJECTILE, WALL
    }

    /* The type */
    private final Type type;

    public TypeComponent(Type type) {
        this.type = type;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public <T> void receive(Message<T> message) {
        // TODO Auto-generated method stub

    }
    
    public Type getType(){
        return this.type;
    }

}
