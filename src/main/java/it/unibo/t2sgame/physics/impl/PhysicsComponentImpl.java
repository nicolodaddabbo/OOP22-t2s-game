package it.unibo.t2sgame.physics.impl;
import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.physics.api.PhysicsComponent;

public class PhysicsComponentImpl implements PhysicsComponent {

    private Vector2D velocity;

    /**
     * Constructor to set starting velocity.
     * @param velocity starting velocity
     */
    public PhysicsComponentImpl(Vector2D velocity){
        this.velocity = velocity;
    }

    /**
     * Updates the position of the passed entity if it has one.
     * @param entity the entity to update
     */
    @Override
    public void update(Entity entity) {
        entity.getPosition().ifPresent(p -> {
            Vector2D position = p;
            entity.setPosition(position.sum(velocity));
        });
    }

    @Override
    public void receive() {
        
    }
    
}
