package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Message;
import it.unibo.t2sgame.model.api.ShootComponent;

public class ShootComponentImpl implements ShootComponent {

    private double fireRate;
    private Entity entity;
    private Directions shotDirection;
    private final EntityFactoryImpl projectileFactory = new EntityFactoryImpl();
    private final StopWatch timer = new StopWatch().start();

    public ShootComponentImpl(final double fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public void update() {
        if(this.timer.getElapsedSeconds() >= this.fireRate){
            this.projectileFactory.createProjectile(this.entity.getPosition());
            //SPAWN THE PROJECTILE IN THE WORLD
            this.timer.restart();
        }
    }

    @Override
    public <T> void receive(final Message<T> message) {
        try {
            Directions shotDirection = (Directions)message.getMessage();
            this.shotDirection = shotDirection;
            this.update();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public void setEntity(final Entity entity) {
        this.entity = entity;;
    }

    @Override
    public double getFireRate() {
        return this.fireRate;
    }

    @Override
    public void setFireRate(final double fireRate) {
        this.fireRate = fireRate;
    }
    
}
