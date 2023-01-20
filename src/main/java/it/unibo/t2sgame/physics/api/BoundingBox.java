package it.unibo.t2sgame.physics.api;

import it.unibo.t2sgame.common.Vector2D;

public interface BoundingBox {
    
    /**
     * 
     * @return the center of the bounding box
     */
    Vector2D getCenter();

    /**
     * 
     * @param center the center of the bounding box
     */
    void setCenter(Vector2D center);

    /**
     * This method checks if the given point is inside the bounding box.
     * @param point the point to check
     * @return the result
     */
    boolean isContaining(Vector2D point);

    /**
     * This method checks if the given circle is colliding with the bounding box.
     * @param center center of the circle
     * @param radius radius of the circle
     * @return the result
     */
    boolean isColliding(Vector2D center, double radius);

    /**
     * This method checks if the given rectangle is colliding with the bounding box.
     * @param center center of the rectangle
     * @param min the lower left corner of the rectangle
     * @param max the higher right corner of the rectangle
     * @return the result
     */
    boolean isColliding(Vector2D center, Vector2D min, Vector2D max);

}
