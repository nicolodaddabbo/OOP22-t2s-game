package it.unibo.t2sgame.core.components.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.TypeComponent;
import it.unibo.t2sgame.game.components.TypeComponent.Type;

public abstract class CollisionComponent extends AbstractComponent {

    protected Set<CollisionComponent> collisions = new HashSet<>();
    protected final Shape shape;
    protected final boolean isRigid;
    private List<Type> types;

    protected CollisionComponent(final Shape shape, final boolean isRigid, List<Type> types) {
        this.shape = shape;
        this.isRigid = isRigid;
        this.types = types;
    }

    @Override
    public <T> void receive(final Message<T> message) {
        if(Vector2D.class.isInstance(message.getMessage())){
            this.receiveFromPhysicComponent(Vector2D.class.cast(message.getMessage()));
        }
    }

    private void receiveFromPhysicComponent(final Vector2D pos) {
        this.shape.setCenter(pos);
    }

    @Override
    public void update() {
        /**
         * THIS CODE IS ONLY HERE BECAUSE MY FRIENDS WANTS TO PLAY.
         * I know that it's very bad, tell them it
         */
        this.collisions = this.entity.getWorld().get().getEntities().stream()
            .filter(e -> this.types.contains(e.getComponent(TypeComponent.class).get().getType()))
            .map(e -> e.getComponent(CollisionComponent.class).get()).collect(Collectors.toSet());

        this.collisions.stream()
        // Filtering each collision which has been checked as true
        .filter(collision -> shape.isColliding(collision.getShape()))
        // Notify to the health component every collision which has been checked as true
        .forEach(collision -> {
            if (this.isRigid || collision.isRigid()) {
                this.knockBack();
            }
            this.collisionAction(collision.getEntity());
        });
    }

    protected abstract void collisionAction(Entity collisionEntity);

    private void knockBack() {
        this.entity.getComponent(PhysicsComponent.class).ifPresent(phycmp -> this.entity
                .setPosition(this.entity.getPosition().sub(phycmp.getVelocity().mul(phycmp.getConvertedSpeed()))));
    }

    public Shape getShape() {
        return this.shape;
    }

    public boolean isRigid() {
        return this.isRigid;
    }

    public Set<CollisionComponent> getCollisions() {
        return new HashSet<>(this.collisions);
    }

    public void addCollision(final CollisionComponent component) {
        this.collisions.add(component);
    }

    public void setCollisions(final Set<CollisionComponent> collisions) {
        this.collisions = new HashSet<>(collisions);
    }

}
