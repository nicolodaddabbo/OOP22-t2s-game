package it.unibo.t2sgame.core.components.api;

import java.util.Set;

import it.unibo.t2sgame.common.shapes.Circle;
import it.unibo.t2sgame.common.shapes.Rectangle;

public interface CollisionComponentFactory {
    /**
     * 
     * @param circle the given shape for the collision
     * @return A CollisionComponent with a cirlce shape
     */
    CollisionComponent collisionWithCirlceShape(Circle circle);
    /**
     * 
     * @param rectangle the given shape for the collision
     * @return A CollisionComponent with a rectangle shape
     */
    CollisionComponent collisionWithRectangleShape(Rectangle rectangle);

}