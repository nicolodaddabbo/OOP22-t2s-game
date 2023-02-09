package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.input.api.Directions;

/**
 * This class represents the physics of the entity.
 */
public class PhysicsComponent extends AbstractComponent {
    private static final double CONVERSION = 2;
    private double speed;
    private Vector2D velocity = new Vector2D(0, 0);

    /**
     * 
     * @param speed the speed of the entity
     */
    public PhysicsComponent(final double speed) {
        this.speed = speed;
    }

    /**
     * 
     * @param speed     the speed of the entity
     * @param direction the starting direction of the entity
     */
    public PhysicsComponent(final double speed, final Directions direction) {
        this.speed = speed;
        this.receiveDirection(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        final var entity = this.getEntity();
        entity.setPosition(entity.getPosition().sum(this.velocity.mul(this.getConvertedSpeed())));
        entity.notifyComponent(CollisionComponent.class, entity::getPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void receive(final Message<T> message) {
        if (Directions.class.isInstance(message.getMessage())) {
            this.receiveDirection(Directions.class.cast(message.getMessage()));
        }
    }

    private void receiveDirection(final Directions direction) {
        switch (direction) {
            case UP:
                this.velocity = new Vector2D(0, -1);
                break;
            case DOWN:
                this.velocity = new Vector2D(0, 1);
                break;
            case LEFT:
                this.velocity = new Vector2D(-1, 0);
                break;
            case RIGHT:
                this.velocity = new Vector2D(1, 0);
                break;
            default:
                this.velocity = new Vector2D(0, 0);
                break;
        }
    }

    /**
     * 
     * @return the converted speed of the entity
     */
    public double getConvertedSpeed() {
        return CONVERSION * speed;
    }

    /**
     * 
     * @return the current velocity of the entity
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }

    /**
     * 
     * @param velocity the new velocity
     */
    public void setVelocity(final Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * 
     * @return the speed of the entity
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * 
     * @param speed the new speed
     */
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

}
