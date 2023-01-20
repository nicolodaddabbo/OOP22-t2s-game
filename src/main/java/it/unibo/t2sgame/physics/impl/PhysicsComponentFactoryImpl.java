package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Message;
import it.unibo.t2sgame.physics.api.BoundingBox;
import it.unibo.t2sgame.physics.api.PhysicsComponent;
import it.unibo.t2sgame.physics.api.PhysicsComponentFactory;

public class PhysicsComponentFactoryImpl implements PhysicsComponentFactory {

    private PhysicsComponent createPhysicsComponentWith(final BoundingBox bound){
        return new PhysicsComponent() {

            private Vector2D velocity;
        
            @Override
            public void update(final Entity entity) {
                entity.setPosition(entity.getPosition().sum(this.velocity));
                bound.setCenter(entity.getPosition());
            }

            @Override
            public <T> void receive(Message<T> message) {
                
            }

            @Override
            public BoundingBox getBoundingBox() {
                return bound;
            }
            
        };
    }

    @Override
    public PhysicsComponent createRectanglePhyisicsComponent(final Vector2D min, final Vector2D max) {
        return createPhysicsComponentWith(new RectangleBoundingBox(min, max));
    }

    @Override
    public PhysicsComponent createCirclePhyisicsComponent(final double radius) {
        return createPhysicsComponentWith(new CircleBoundingBox(radius));
    }

}