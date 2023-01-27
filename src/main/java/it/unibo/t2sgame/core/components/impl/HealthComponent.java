package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;

public class HealthComponent extends AbstractComponent{

    private int health;

    public HealthComponent(final int health){
        this.health = health;
    }

    @Override
    public void update() {
    
    }

    @Override
    public <T> void receive(Message<T> message) {
        try {
            var dmg = (int)message.getMessage();
            this.health = this.health - dmg >= 0 ? this.health - dmg : 0;
        } catch (ClassCastException e) {
            e.printStackTrace();
        } 
    }

    public int getHealth() {
        return this.health;
    }

}
