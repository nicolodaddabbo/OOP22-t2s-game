package it.unibo.t2sgame.physics.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.HealthComponent;
import it.unibo.t2sgame.model.api.Message;
import it.unibo.t2sgame.physics.api.CollisionComponent;
import it.unibo.t2sgame.physics.api.CollisionComponentFactory;
import it.unibo.t2sgame.physics.api.Shape;

public class CollisionComponentFactoryImpl implements CollisionComponentFactory{

    private CollisionComponent collisionWith(final Shape shape, final int damage){
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
                        collision.getEntity().notifyComponent(HealthComponent.class, () -> this.getDamage());
                        this.entity.notifyComponent(HealthComponent.class, () -> collision.getDamage());
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
            public int getDamage() {
                return damage;
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
    public CollisionComponent collisionWithCirlceShape(final Circle circle, final int damage) {
        return collisionWith(circle, damage);
    }

    @Override
    public CollisionComponent collisionWithRectangleShape(final Rectangle rectangle,final int damage) {
        return  collisionWith(rectangle, damage);
    }
    
}
