package it.unibo.t2sgame.common.shapes;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.common.Vector2D;

/**
 * This class represents the circle shape.
 */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getCenter() {
        return this.center;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final Shape shape) {
        return shape.isColliding(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final Circle circle) {
        return this.center.distance(circle.getCenter()) < this.radius + circle.getRadius();
    }

    /**
     * {@inheritDoc}
     * 
     * @see <a>The formula to check intersection has been taken from
     *      https://yal.cc/rectangle-circle-intersection-test/comment-page-1/</a>
     */
    @Override
    public boolean isColliding(final Rectangle rectangle) {
        final var rectangleCenter = rectangle.getCenter();
        final var deltaX = this.calcDelta(this.center.getX(), rectangleCenter.getX(), rectangle.getWidth());
        final var deltaY = this.calcDelta(this.center.getY(), rectangleCenter.getY(), rectangle.getHeight());
        return Math.pow(deltaX, 2) + Math.pow(deltaY, 2) < Math.pow(this.radius, 2);
    }

    private double calcDelta(final double circleCenterAxis, final double rectangleCenterAxis,
            final double rectangleSide) {
        return circleCenterAxis - Math.max(rectangleCenterAxis - rectangleSide / 2,
                Math.min(circleCenterAxis, rectangleCenterAxis + rectangleSide / 2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape copy() {
        return new Circle(this.center, this.radius);
    }

}
