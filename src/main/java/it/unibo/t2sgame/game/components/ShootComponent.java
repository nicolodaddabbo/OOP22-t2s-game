package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;
import it.unibo.t2sgame.input.api.Directions;

/**
 * This class represents the ability to shoot of the entity.
 */
public class ShootComponent extends AbstractComponent {

    private double fireRateSeconds;
    private final EventFactoryImpl eventFactory = new EventFactoryImpl();
    private final StopWatch timer = new StopWatch().start();

    /**
     * 
     * @param fireRateSeconds the rate of fire of the entity in seconds
     */
    public ShootComponent(final double fireRateSeconds) {
        this.fireRateSeconds = fireRateSeconds;
    }

    @Override
    public void update() {
        
    }

    @Override
    public <T> void receive(final Message<T> message) {
        try {
            var shotDirection = (Directions)message.getMessage();
            this.shoot(shotDirection);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void shoot(final Directions shotDirection){
        if(this.timer.getElapsedSeconds() >= this.fireRateSeconds){
            this.entity.getWorld().ifPresent(e -> e.notifyEvent(this.eventFactory.onShootEvent(this.entity, shotDirection)));
            this.timer.restart();
        }
    }

    /**
     * 
     * @return the rate of fire of the entity in seconds
     */
    public double getfireRateSeconds() {
        return this.fireRateSeconds;
    }

    /**
     * 
     * @param fireRateSeconds the new rate of fire in seconds
     */
    public void setfireRateSeconds(final double fireRateSeconds) {
        this.fireRateSeconds = fireRateSeconds;
    }
    
}
