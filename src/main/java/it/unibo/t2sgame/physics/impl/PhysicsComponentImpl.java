package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Message;
import it.unibo.t2sgame.physics.api.PhysicsComponent;

public class PhysicsComponentImpl implements PhysicsComponent {
    private static final double CONVERSION = 2;
    private double speed;
    private Vector2D velocity = new Vector2D(0, 0);

    public PhysicsComponentImpl(final double speed){
        this.speed = speed;
    }

    @Override
    public void update(final Entity entity) {
        entity.setPosition(entity.getPosition().sum(this.velocity.mul(CONVERSION*speed)));
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
    
}
