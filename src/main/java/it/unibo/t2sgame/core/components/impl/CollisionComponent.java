package it.unibo.t2sgame.core.components.impl;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<Type> types;

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

    private void receiveFromPhysicComponent(final Vector2D pos) {
        this.shape.setCenter(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.entity.getWorld().stream()
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
        this.entity.getComponent(PhysicsComponent.class).ifPresent(phycmp -> this.entity
                .setPosition(this.entity.getPosition().sub(phycmp.getVelocity().mul(phycmp.getConvertedSpeed()))));
    }

    /**
     * 
     * @return the shape of the collision
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     * 
     * @return true if the collision is rigid, false otherwise
     */
    public boolean isRigid() {
        return this.isRigid;
    }

}
