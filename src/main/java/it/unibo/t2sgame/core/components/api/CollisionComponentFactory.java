package it.unibo.t2sgame.core.components.api;

import java.util.Set;

import it.unibo.t2sgame.common.Shape;

public interface CollisionComponentFactory {
    /**
     * 
     * @param shape the given shape for the collision
     * @return A CollisionComponent with a rigid body
     */
    CollisionComponent createRigidCollision(Shape shape);
    /**
     * 
     * @param shape the given shape for the collision
     * @return A CollisionComponent without a rigid body
     */
    CollisionComponent createCollision(Shape shape);

}
