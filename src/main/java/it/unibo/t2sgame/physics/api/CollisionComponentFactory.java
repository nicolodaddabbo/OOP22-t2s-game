package it.unibo.t2sgame.physics.api;

import java.util.Set;

import it.unibo.t2sgame.physics.impl.Circle;
import it.unibo.t2sgame.physics.impl.Rectangle;

public interface CollisionComponentFactory {
    /**
     * 
     * @param circle the given shape for the collision
     * @param damage the damage inflicted to other entity when the collision has been checked
     * 
     * @return A CollisionComponent with a cirlce shape and the given damage
     */
    CollisionComponent collisionWithCirlceShape(Circle circle, int damage);
    /**
     * 
     * @param rectangle the given shape for the collision
     * @param damage the damage inflicted to other entity when the collision has been checked
     * 
     * @return A CollisionComponent with a rectangle shape and the given damage
     */
    CollisionComponent collisionWithRectangleShape(Rectangle rectangle, int damage);

}
