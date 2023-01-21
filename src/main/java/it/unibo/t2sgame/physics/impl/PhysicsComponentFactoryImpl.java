package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Message;
import it.unibo.t2sgame.physics.api.BoundingBox;
import it.unibo.t2sgame.physics.api.PhysicsComponent;
import it.unibo.t2sgame.physics.api.PhysicsComponentFactory;

public class PhysicsComponentFactoryImpl implements PhysicsComponentFactory {

    private PhysicsComponent createPhysicsComponentWith(final BoundingBox bound){
        return new PhysicsComponent() {

            private static final double CONVERSION = 3;
            private Vector2D velocity = new Vector2D(0, 0);
        
            @Override
            public void update(final Entity entity) {
                entity.setPosition(entity.getPosition().sum(this.velocity.mul(CONVERSION)));
                bound.setCenter(entity.getPosition());
            }

            @Override
            public <T> void receive(Message<T> message) {
                try{
                    Directions d = (Directions)message.getMessage();
                    this.receiveDirection(d);
                }catch(ClassCastException e){
                    e.printStackTrace();
                }
            }

            private void receiveDirection(Directions direction) {
                switch(direction){
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