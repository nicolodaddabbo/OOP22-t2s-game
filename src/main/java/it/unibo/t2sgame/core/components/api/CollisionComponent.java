package it.unibo.t2sgame.core.components.api;

import java.util.Set;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.entity.api.Entity;

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
