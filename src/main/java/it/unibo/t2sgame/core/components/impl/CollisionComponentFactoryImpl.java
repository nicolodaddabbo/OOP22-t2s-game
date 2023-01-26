package it.unibo.t2sgame.core.components.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.common.shapes.Circle;
import it.unibo.t2sgame.common.shapes.Rectangle;
import it.unibo.t2sgame.core.components.api.CollisionComponent;
import it.unibo.t2sgame.core.components.api.CollisionComponentFactory;
import it.unibo.t2sgame.core.components.api.DamageComponent;
import it.unibo.t2sgame.core.components.api.HealthComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.components.api.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;

public class CollisionComponentFactoryImpl implements CollisionComponentFactory{

    private CollisionComponent collisionWith(final Shape shape){
        return new CollisionComponent() {

            private Entity entity;
            private Set<CollisionComponent> collisions = new HashSet<>();
            private boolean canCheck = true;
            
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

            private void receiveFromHealthComponent(final boolean checkStatus){

            }

            @Override
            public void update() {
                if(this.canCheck){
                    this.collisions.stream()
                    // Filtering each collision which has been checked as true
                    .filter(collision -> shape.isColliding(collision.getShape()))
                    // Notify to the health component every collision which has been checked as true
                    .forEach(collision -> {
                        collision.getEntity().getComponent(PhysicsComponent.class).ifPresent(pc1 -> 
                            this.entity.getComponent(PhysicsComponent.class).ifPresent(pc2 -> {
                                collision.getEntity()
                                    .setPosition(collision.getEntity().getPosition().sum(pc2.getVelocity().mul(pc1.getConvertedSpeed())));
                                this.entity
                                    .setPosition(this.entity.getPosition().sum(pc1.getVelocity().mul(pc2.getConvertedSpeed())));
                            })
                        );
                        //Remove health to the touched entity
                        this.entity.getComponent(DamageComponent.class)
                            .ifPresent(c -> {
                                if(c.canDamage()){
                                    collision.getEntity().notifyComponent(HealthComponent.class, c::getDamage);
                                }
                            });
                        //Remove health to this entity
                        collision.getEntity().getComponent(DamageComponent.class)
                            .ifPresent(c -> {
                                if(c.canDamage()){
                                    this.entity.notifyComponent(HealthComponent.class, c::getDamage);
                                }
                            });
                        });
                }
              
            }

            @Override
            public Shape getShape() {
                return shape;
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
    public CollisionComponent collisionWithCirlceShape(final Circle circle) {
        return collisionWith(circle);
    }

    @Override
    public CollisionComponent collisionWithRectangleShape(final Rectangle rectangle) {
        return  collisionWith(rectangle);
    }
    
}
