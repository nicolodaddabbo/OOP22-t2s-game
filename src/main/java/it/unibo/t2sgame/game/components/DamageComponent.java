package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;

/**
 * This class represents the ability to damage entities on collision.
 */
public class DamageComponent extends AbstractComponent {

    private int damage;
    private double cooldown;
    private final StopWatch timer = new StopWatch().start();

    /**
     * 
     * @param damage the damage dealt on collision
     * @param cooldown the cooldown period in seconds, that indicates after how much the entity can damage after damaging an entity
     */
    public DamageComponent(int damage, double cooldown) {
        this.setDamage(damage);
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
     * @return the damage of the entity
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * 
     * @param damage the new damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}
