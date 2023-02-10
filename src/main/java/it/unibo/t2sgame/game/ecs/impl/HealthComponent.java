package it.unibo.t2sgame.game.ecs.impl;

import it.unibo.t2sgame.game.ecs.api.Message;
import it.unibo.t2sgame.game.logics.api.EventFactory;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;

/**
 * This class represent the health of an entity, and extends AbstractComponent.
 */
public class HealthComponent extends AbstractComponent {
    private int health;
    private final EventFactory eventFactory = new EventFactoryImpl();
    /**
     * 
     * @param health the health of an entity
     */
    public HealthComponent(final int health) {
        this.health = health;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void receive(final Message<T> message) {
        if (Integer.class.isInstance(message.getMessage())) {
            final var dmg = Integer.class.cast(message.getMessage());
            this.health = this.health - dmg >= 0 ? this.health - dmg : 0;
            this.notifyIfDead();
        }
    }
    /**
     * Getter that returns the current health of the entity.
     * @return the current health
     */
    public int getHealth() {
        return this.health;
    }
    /**
     * Setter to modify the value of health.
     * @param health value that will substitute the previous health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    private void notifyIfDead() {
        if (this.health == 0) {
            this.getEntity().getWorld().ifPresent(e -> e.notifyEvent(this.eventFactory.onDeathEvent(this.getEntity())));
        }
    }
}
