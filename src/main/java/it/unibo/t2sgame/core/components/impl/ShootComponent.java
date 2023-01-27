package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;
import it.unibo.t2sgame.input.api.Directions;

public class ShootComponent extends AbstractComponent {

    private double fireRate;
    private Directions shotDirection;
    private final EventFactoryImpl eventFactory = new EventFactoryImpl();
    private final StopWatch timer = new StopWatch().start();

    public ShootComponent(final double fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public void update() {
        if(this.timer.getElapsedSeconds() >= this.fireRate){
            this.eventFactory.onShootEvent(this.entity, this.shotDirection);
            this.timer.restart();
        }
    }

    @Override
    public <T> void receive(final Message<T> message) {
        try {
            var shtDir = (Directions)message.getMessage();
            this.shotDirection = shtDir;
            this.update();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public double getFireRate() {
        return this.fireRate;
    }

    public void setFireRate(final double fireRate) {
        this.fireRate = fireRate;
    }
    
}
