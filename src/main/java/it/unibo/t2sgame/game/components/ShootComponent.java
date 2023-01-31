package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;
import it.unibo.t2sgame.input.api.Directions;

public class ShootComponent extends AbstractComponent {

    private double fireRate;
    private final EventFactoryImpl eventFactory = new EventFactoryImpl();
    private final StopWatch timer = new StopWatch().start();

    public ShootComponent(final double fireRate) {
        this.fireRate = fireRate;
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
        if(this.timer.getElapsedSeconds() >= this.fireRate){
            this.entity.getWorld().ifPresent(e -> e.notifyEvent(this.eventFactory.onShootEvent(this.entity, shotDirection)));
            this.timer.restart();
        }
    }

    public double getFireRate() {
        return this.fireRate;
    }

    public void setFireRate(final double fireRate) {
        this.fireRate = fireRate;
    }
    
}
