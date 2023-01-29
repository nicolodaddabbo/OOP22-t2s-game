package it.unibo.t2sgame.common.shapes;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.common.Vector2D;

public class Circle implements Shape {
    private Vector2D center;
    private double radius;

    /**
     * 
     * @param center the center of the circle
     * @param radius the radius of the circle
     */
    public Circle(final Vector2D center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector2D getCenter() {
        return this.center;
    }

    @Override
    public void setCenter(final Vector2D center) {
        this.center = center;
    }

    /**
     * 
     * @return the radius of the circle
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * 
     * @param radius the new radius of the circle
     */
    public void setRadius(final double radius) {
        this.radius = radius;
    }

    @Override
    public boolean isColliding(final Shape shape) {
        return shape.isColliding(this);
    }

    @Override
    public boolean isColliding(final Circle circle) {
        return this.center.distance(circle.getCenter()) < this.radius+circle.getRadius();
    }

    /**
     * @see <a>The formula to check intersection has been taken from 
     * https://yal.cc/rectangle-circle-intersection-test/comment-page-1/</a>
     */
    @Override
    public boolean isColliding(final Rectangle rectangle) {
        var rectangleCenter = rectangle.getCenter();
        var deltaX = this.calcDelta(this.center.getX(), rectangleCenter.getX(), rectangle.getWidth());
        var deltaY = this.calcDelta(this.center.getY(), rectangleCenter.getY(), rectangle.getHeight());
        return Math.pow(deltaX, 2) + Math.pow(deltaY, 2) < Math.pow(this.radius, 2);
    }

    private double calcDelta(final double circleCenterAxis, final double rectangleCenterAxis, final double rectangleSide){
        return circleCenterAxis - Math.max(rectangleCenterAxis-rectangleSide/2, Math.min(circleCenterAxis, rectangleCenterAxis + rectangleSide/2));
    }
    
}
