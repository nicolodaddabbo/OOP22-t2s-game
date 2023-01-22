package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.HealthComponent;
import it.unibo.t2sgame.model.api.Message;

public class HealthComponentImpl implements HealthComponent{

    private int health;

    HealthComponentImpl(final int health){
        this.health = health;
    }

    @Override
    public void update(Entity entity) {
    
    }

    @Override
    public <T> void receive(Message<T> message) {
        this.health = this.health - 1;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}