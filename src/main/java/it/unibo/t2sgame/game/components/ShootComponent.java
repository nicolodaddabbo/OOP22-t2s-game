package it.unibo.t2sgame.game.components;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.logics.impl.EventFactoryImpl;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.input.api.Directions;

/**
 * This class represents the ability to shoot of the entity.
 */
public class ShootComponent extends AbstractComponent {

    private double fireRateSeconds;
    private double projectileSpeed;
    private int projectileDamage;
    private double projectileSize;
    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final EventFactoryImpl eventFactory = new EventFactoryImpl();
    private final StopWatch timer = new StopWatch().start();

    /**
     * 
     * @param fireRateSeconds the rate of fire of the entity in seconds
     */
    public ShootComponent(final double fireRateSeconds, final double projectileSpeed, final int projectileDamage,
            final double projectileSize) {
        this.fireRateSeconds = fireRateSeconds;
        this.projectileSpeed = projectileSpeed;
        this.projectileDamage = projectileDamage;
        this.projectileSize = projectileSize;
    }

    @Override
    public void update() {

    }

    @Override
    public <T> void receive(final Message<T> message) {
        if (Directions.class.isInstance(message.getMessage())) {
            this.shoot(Directions.class.cast(message.getMessage()));
        }
    }

    private void shoot(final Directions shotDirection) {
        if (this.timer.getElapsedSeconds() >= this.fireRateSeconds) {
            this.entity.getWorld()
                    .ifPresent(e -> e.notifyEvent(this.eventFactory.onShootEvent(this.createProjectile(shotDirection))));
            this.timer.restart();
        }
    }

    protected Entity createProjectile(final Directions shotDirection){
        return this.entityFactory.createProjectile(this.entity.getPosition(), projectileSpeed, projectileDamage, projectileSize, shotDirection);
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

    /**
     * 
     * @return the projectile speed of the entity in seconds
     */
    public double getProjectileSpeed() {
        return projectileSpeed;
    }
    /**
     * 
     * @param projectileSpeed the new projectile speed of the entity
     */
    public void setProjectileSpeed(final double projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    /**
     * 
     * @return the projectile damage of the entity in seconds
     */
    public int getProjectileDamage() {
        return projectileDamage;
    }
    /**
     * 
     * @param projectileDamage the new projectile damage of the entity
     */
    public void setProjectileDamage(final int projectileDamage) {
        this.projectileDamage = projectileDamage;
    }

    /**
     * 
     * @return the projectile size of the entity in seconds
     */
    public double getProjectileSize() {
        return projectileSize;
    }
    /**
     * 
     * @param projectileSize the new projectile size of the entity
     */
    public void setProjectileSize(final double projectileSize) {
        this.projectileSize = projectileSize;
    }

}
