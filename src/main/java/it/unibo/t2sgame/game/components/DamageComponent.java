package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;

/**
 * This class represents the ability to damage entities on collision.
 */
public class DamageComponent extends AbstractComponent {

    private int damage;
    private final double cooldownSeconds;
    private final StopWatch timer = new StopWatch().start();

    /**
     * 
     * @param damage          the damage dealt on collision
     * @param cooldownSeconds the cooldown period in seconds, that indicates after
     *                        how much the entity can damage after damaging an
     *                        entity
     */
    public DamageComponent(final int damage, final double cooldownSeconds) {
        this.damage = damage;
        this.cooldownSeconds = cooldownSeconds;
    }

    @Override
    public void update() {

    }

    @Override
    public <T> void receive(final Message<T> message) {

    }

    /**
     * 
     * @return true if the cooldown period is over, otherwise returns false
     */
    public boolean canDamage() {
        if (this.timer.getElapsedSeconds() >= this.cooldownSeconds) {
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
    public void setDamage(final int damage) {
        this.damage = damage;
    }

}
