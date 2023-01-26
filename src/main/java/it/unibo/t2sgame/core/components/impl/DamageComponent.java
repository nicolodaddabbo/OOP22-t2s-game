package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;

public class DamageComponent extends AbstractComponent {

    private int damage;
    private double cooldown;
    private final StopWatch timer = new StopWatch().start();

    public DamageComponent(int damage, double cooldown) {
        this.damage = damage;
        this.cooldown = cooldown;
    }

    @Override
    public void update() {
        
    }

    @Override
    public <T> void receive(Message<T> message) {
        
    }

    /**
     * 
     * @return true if the cooldown period is over, otherwise returns false
     */
    public boolean canDamage() {
        if(this.timer.getElapsedSeconds() >= this.cooldown){
            this.timer.restart();
            return true;
        }
        return false;
    }

    /**
     * 
     * @return the damage if the entity
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * 
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}
