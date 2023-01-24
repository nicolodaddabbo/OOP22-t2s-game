package it.unibo.t2sgame.physics.api;

import java.util.Set;

import it.unibo.t2sgame.model.api.Component;
import it.unibo.t2sgame.model.api.Entity;

public interface CollisionComponent extends Component{
    /**
     * 
     */
    @Override
    void update();
    /**
     * 
     * @return
     */
    Shape getShape();

    Set<CollisionComponent> getCollisions();
    
    void addCollision(CollisionComponent collision);

    void setCollisions(Set<CollisionComponent> collisions);

}
