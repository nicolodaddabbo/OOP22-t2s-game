package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;

public class HealthComponent extends AbstractComponent{

    private int health;
    private EventFactory eventFactory = new EventFactoryImpl();
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
            this.notifyIfDead();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } 
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(final int health){
        this.health = health;
    }
    
    private void notifyIfDead(){
        if(this.health == 0){
            this.entity.getWorld().ifPresent(e -> e.notifyEvent(this.eventFactory.onDeathEvent(this.entity)));
        }
    }
}
