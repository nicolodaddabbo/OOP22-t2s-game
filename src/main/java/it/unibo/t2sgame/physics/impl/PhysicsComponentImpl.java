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

    @Override
    public void update(Entity entity) {
        entity.getPosition().ifPresent(p -> {
            entity.setPosition(p.sum(this.velocity));
        });
    }

    @Override
    public void receive() {
        
    }
    
}
