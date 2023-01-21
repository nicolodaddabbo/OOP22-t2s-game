package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.HealthComponent;
import it.unibo.t2sgame.model.api.Message;

public class HealthComponentImpl implements HealthComponent{

    private int health;
    @Override
    public void update(Entity entity) {
        entity.getComponent(HealthComponent.class)
            .ifPresent(component -> ((HealthComponent) component).setHealth(this.health));
    }

    @Override
    public <T> void receive(Message<T> message) {
        this.health = this.health - 1;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
}
