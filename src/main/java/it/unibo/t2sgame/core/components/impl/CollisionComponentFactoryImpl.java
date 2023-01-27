package it.unibo.t2sgame.core.components.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.CollisionComponent;
import it.unibo.t2sgame.core.components.api.CollisionComponentFactory;
import it.unibo.t2sgame.core.components.api.HealthComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.components.api.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;

public class CollisionComponentFactoryImpl implements CollisionComponentFactory{

    private CollisionComponent collisionWith(final Shape shape, final boolean isRigid){
        return new CollisionComponent() {

            private Entity entity;
            private Set<CollisionComponent> collisions = new HashSet<>();
            
            @Override
            public <T> void receive(Message<T> message) {
                try {
                    Vector2D pos = (Vector2D)message.getMessage();
                    receiveFromPhysicComponent(pos);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }

            private void receiveFromPhysicComponent(final Vector2D pos){
                shape.setCenter(pos);
            }

            @Override
            public void update() {
                this.collisions.stream()
                // Filtering each collision which has been checked as true
                .filter(collision -> shape.isColliding(collision.getShape()))
                // Notify to the health component every collision which has been checked as true
                .forEach(collision -> {
                    if(isRigid || collision.isRigid()){
                        this.knockBack(collision.getEntity());
                    }
                    //Remove health to the touched entity
                    this.entity.getComponent(DamageComponent.class)
                        .ifPresent(c -> {
                            if(c.canDamage()){
                                collision.getEntity().notifyComponent(HealthComponent.class, c::getDamage);
                            }
                        }
                    );
                    //Remove health to this entity
                    collision.getEntity().getComponent(DamageComponent.class)
                        .ifPresent(c -> {
                            if(c.canDamage()){
                                this.entity.notifyComponent(HealthComponent.class, c::getDamage);
                            }
                        }
                    );
                });
            }

            private void knockBack(Entity collisionEntity){
                collisionEntity.getComponent(PhysicsComponent.class).ifPresent(phycmp -> 
                    collisionEntity.setPosition(collisionEntity.getPosition().sub(phycmp.getVelocity().mul(phycmp.getConvertedSpeed())))
                );
                this.entity.getComponent(PhysicsComponent.class).ifPresent(phycmp -> 
                    this.entity.setPosition(this.entity.getPosition().sub(phycmp.getVelocity().mul(phycmp.getConvertedSpeed())))
                );
            }

            @Override
            public Shape getShape() {
                return shape;
            }

            @Override
            public boolean isRigid() {
                return isRigid;
            }

            @Override
            public Set<CollisionComponent> getCollisions() {
                return new HashSet<>(this.collisions);
            }

            @Override
            public void addCollision(CollisionComponent c) {
                this.collisions.add(c);
                
            }

            @Override
            public void setCollisions(Set<CollisionComponent> collisions) {
                this.collisions = new HashSet<>(collisions);                
            }

            @Override
            public void setEntity(Entity entity) {
                this.entity = entity;                
            }

            @Override
            public Entity getEntity() {
                return this.entity;
            }
            
        };
    }

    @Override
    public CollisionComponent createRigidCollision(final Shape shape) {
        return collisionWith(shape, true);
    }

    @Override
    public CollisionComponent createCollision(final Shape shape) {
        return  collisionWith(shape, false);
    }
    
}
