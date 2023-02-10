package it.unibo.t2sgame.core.components.impl;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;

/**
 * This class represents the collsion of the entity.
 */
public abstract class CollisionComponent extends AbstractComponent {

    private final Shape shape;
    private final boolean isRigid;
    private final List<Type> types;

    /**
     * 
     * @param shape   the shape of the collision
     * @param isRigid if true, the collision is rigid and cant be passed through.
     *                Otherwise not
     * @param types   the types of entity that the collision collides with
     */
    protected CollisionComponent(final Shape shape, final boolean isRigid, final List<Type> types) {
        this.shape = shape;
        this.isRigid = isRigid;
        this.types = types;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void receive(final Message<T> message) {
        if (Vector2D.class.isInstance(message.getMessage())) {
            this.receiveFromPhysicComponent(Vector2D.class.cast(message.getMessage()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.getEntity().getWorld().stream()
                .flatMap(w -> w.getEntities().stream()
                        .filter(e -> this.types.contains(e.getType()))
                        .flatMap(e -> e.getComponent(CollisionComponent.class).stream()))
                // Filtering each collision which has been checked as true
                .filter(c -> shape.isColliding(c.getShape()))
                // Take collision action for every collision which has been checked as true
                .forEach(c -> {
                    if (this.isRigid || c.isRigid()) {
                        this.knockBack();
                    }
                    this.collisionAction(c.getEntity());
                });
    }

    /**
     * 
     * @return the shape of the collision
     */
    public Shape getShape() {
        return this.shape.copy();
    }

    /**
     * 
     * @return true if the collision is rigid, false otherwise
     */
    public boolean isRigid() {
        return this.isRigid;
    }

    /**
     * The action the collision should take when colliding with the specified
     * entity.
     * 
     * @param collisionEntity the entity the collision is colliding with
     */
    protected abstract void collisionAction(Entity collisionEntity);

    /**
     * The action the collision should take when colliding with the specified entity
     * that has a rigid collision.
     */
    protected void knockBack() {
        this.getEntity().getComponent(PhysicsComponent.class).ifPresent(phycmp -> this.getEntity()
                .setPosition(this.getEntity().getPosition().sub(phycmp.getVelocity().mul(phycmp.getConvertedSpeed()))));
    }

    private void receiveFromPhysicComponent(final Vector2D pos) {
        this.shape.setCenter(pos);
    }

}
