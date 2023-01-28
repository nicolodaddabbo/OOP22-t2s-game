package it.unibo.t2sgame.core.components.impl;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.input.api.Directions;


public class PhysicsComponent extends AbstractComponent {
    private static final double CONVERSION = 2;
    private double speed;
    private Vector2D velocity = new Vector2D(0, 0);

    /**
     * 
     * @param speed the speed of the entity
     */
    public PhysicsComponent(final double speed){
        this.setSpeed(speed);
    }

    /**
     * 
     * @param speed the speed of the entity
     * @param direction the starting direction of the entity
     */
    public PhysicsComponent(final double speed, final Directions direction) {
        this.setSpeed(speed);
        this.receiveDirection(direction);
    }

    @Override
    public void update() {
        entity.setPosition(entity.getPosition().sum(this.velocity.mul(this.getConvertedSpeed())));
        entity.notifyComponent(CollisionComponent.class, entity::getPosition);
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
                this.setVelocity(new Vector2D(0, -1));
                break;
            case DOWN:
                this.setVelocity(new Vector2D(0, 1));
                break;
            case LEFT:
                this.setVelocity(new Vector2D(-1, 0));
                break;
            case RIGHT:
                this.setVelocity(new Vector2D(1, 0));
                break;
            default:
                this.setVelocity(new Vector2D(0, 0));
                break;
        }
    }

    public double getConvertedSpeed() {
        return CONVERSION*speed;
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;        
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
}
