package it.unibo.t2sgame.core.components.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.HealthComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.components.api.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;

public class CollisionComponent extends AbstractComponent {

    private Set<CollisionComponent> collisions = new HashSet<>();
    private final Shape shape;
    private boolean isRigid;

    public CollisionComponent(Shape shape, boolean isRigid) {
        this.shape = shape;
        this.isRigid = isRigid;
    }

    @Override
    public <T> void receive(Message<T> message) {
        try {
            Vector2D pos = (Vector2D) message.getMessage();
            receiveFromPhysicComponent(pos);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void receiveFromPhysicComponent(final Vector2D pos) {
        shape.setCenter(pos);
    }

    @Override
    public void update() {
        this.collisions.stream()
        // Filtering each collision which has been checked as true
        .filter(collision -> shape.isColliding(collision.getShape()))
        // Notify to the health component every collision which has been checked as true
        .forEach(collision -> {
            if (isRigid || collision.isRigid()) {
                this.knockBack(collision.getEntity());
            }
            // Remove health to the touched entity
            this.entity.getComponent(DamageComponent.class)
                    .ifPresent(c -> {
                        if (c.canDamage()) {
                            collision.getEntity().notifyComponent(HealthComponent.class, c::getDamage);
                        }
                    });
            // Remove health to this entity
            collision.getEntity().getComponent(DamageComponent.class)
                    .ifPresent(c -> {
                        if (c.canDamage()) {
                            this.entity.notifyComponent(HealthComponent.class, c::getDamage);
                        }
                    });
        });
    }

    private void knockBack(Entity collisionEntity) {
        collisionEntity.getComponent(PhysicsComponent.class).ifPresent(phycmp -> collisionEntity
                .setPosition(collisionEntity.getPosition().sub(phycmp.getVelocity().mul(phycmp.getConvertedSpeed()))));
        this.entity.getComponent(PhysicsComponent.class).ifPresent(phycmp -> this.entity
                .setPosition(this.entity.getPosition().sub(phycmp.getVelocity().mul(phycmp.getConvertedSpeed()))));
    }

    public Shape getShape() {
        return shape;
    }

    public boolean isRigid() {
        return isRigid;
    }

    public Set<CollisionComponent> getCollisions() {
        return new HashSet<>(this.collisions);
    }

    public void addCollision(CollisionComponent c) {
        this.collisions.add(c);

    }

    public void setCollisions(Set<CollisionComponent> collisions) {
        this.collisions = new HashSet<>(collisions);
    }

}
