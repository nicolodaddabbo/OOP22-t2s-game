package it.unibo.t2sgame.physics.api;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.physics.impl.Circle;
import it.unibo.t2sgame.physics.impl.Rectangle;
/**
 * This interface models a shape usefull for managing collisions.
 * 
 */
public interface Shape {
    /**
     * 
     * @param pos the new center's position
     */
    void setCenter(Vector2D pos);

    /**
     * Checking if the current shape is colliding with the given shape
     * @param shape the other shape to check if the current is colliding
     * @return true if collision has been detected, otherwise false
     */
    boolean isColliding(Shape shape);
    /**
     * Checking if the current shape is colliding with the given circle
     * @param circle the other circle to check if the current is colliding
     * @return true if collision has been detected, otherwise false
     */
    boolean isColliding(Circle circle);
    /**
     * Checking if the current shape is colliding with the given rectangle
     * @param rectangle the other rectangle to check if the current is colliding
     * @return true if collision has been detected, otherwise false
     */
    boolean isColliding(Rectangle rectangle);

}
