package it.unibo.t2sgame.common;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.shapes.Circle;
import it.unibo.t2sgame.common.shapes.Rectangle;

class ShapeTest {

    private static final double HALF_SIZE = 2;
    private static final double SAME_AXIS_INTERSECTION = HALF_SIZE * 2 - 0.1;
    private static final double CIRCLE_CIRCLE_DIAGONAL_INTESECTION = 2.8;
    private static final double CIRCLE_RECTANGLE_DIAGONAL_INTESECTION = 3.4;

    @Test
    void testCircleIsIntersectingWithCircleInXAxis() {
        final Shape circle = new Circle(new Vector2D(SAME_AXIS_INTERSECTION, 0), HALF_SIZE);
        assertTrue(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsIntersectingWithCircleInYAxis() {
        final Shape circle = new Circle(new Vector2D(0, SAME_AXIS_INTERSECTION), HALF_SIZE);
        assertTrue(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsNotIntersectingWithCircleInXAxis() {
        final Shape circle = new Circle(new Vector2D(HALF_SIZE * 2, 0), HALF_SIZE);
        assertFalse(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsNotIntersectingWithCircleInYAxis() {
        final Shape circle = new Circle(new Vector2D(0, HALF_SIZE * 2), HALF_SIZE);
        assertFalse(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsIntersectingWithCircleDiagonally() {
        final Shape circle = new Circle(
                new Vector2D(CIRCLE_CIRCLE_DIAGONAL_INTESECTION, CIRCLE_CIRCLE_DIAGONAL_INTESECTION),
                HALF_SIZE);
        assertTrue(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsNotIntersectingWithCircleDiagonally() {
        final double exitIntersection = CIRCLE_CIRCLE_DIAGONAL_INTESECTION + 0.1;
        final Shape circle = new Circle(
                new Vector2D(exitIntersection, exitIntersection),
                HALF_SIZE);
        assertFalse(this.isCircleIntersecting(circle));
    }

    @Test
    void testRectangleIsIntersectingWithRectangleInXAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(SAME_AXIS_INTERSECTION, 0), HALF_SIZE * 2, HALF_SIZE * 2);
        assertTrue(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsIntersectingWithRectangleInYAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(0, SAME_AXIS_INTERSECTION), HALF_SIZE * 2, HALF_SIZE * 2);
        assertTrue(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsNotIntersectingWithRectangleInXAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(HALF_SIZE * 2, 0), HALF_SIZE * 2, HALF_SIZE * 2);
        assertFalse(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsNotIntersectingWithRectangleInYAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(0, HALF_SIZE * 2), HALF_SIZE * 2, HALF_SIZE * 2);
        assertFalse(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsIntersectingWithRectangleDiagonally() {
        final Shape rectangle = new Rectangle(new Vector2D(SAME_AXIS_INTERSECTION, SAME_AXIS_INTERSECTION),
                HALF_SIZE * 2,
                HALF_SIZE * 2);
        assertTrue(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsNotIntersectingWithRectangleDiagonally() {
        final Shape rectangle = new Rectangle(new Vector2D(HALF_SIZE * 2, HALF_SIZE * 2), HALF_SIZE * 2, HALF_SIZE * 2);
        assertFalse(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testCircleIsIntersectingWithRectangleInXAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(SAME_AXIS_INTERSECTION, 0), HALF_SIZE * 2, HALF_SIZE * 2);
        assertTrue(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsIntersectingWithRectangleInYAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(0, SAME_AXIS_INTERSECTION), HALF_SIZE * 2, HALF_SIZE * 2);
        assertTrue(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsNotIntersectingWithRectangleInXAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(HALF_SIZE * 2, 0), HALF_SIZE * 2, HALF_SIZE * 2);
        assertFalse(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsNotIntersectingWithRectangleInYAxis() {
        final Shape rectangle = new Rectangle(new Vector2D(0, HALF_SIZE * 2), HALF_SIZE * 2, HALF_SIZE * 2);
        assertFalse(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsIntersectingWithRectangleDiagonally() {
        final Shape rectangle = new Rectangle(
                new Vector2D(CIRCLE_RECTANGLE_DIAGONAL_INTESECTION, CIRCLE_RECTANGLE_DIAGONAL_INTESECTION),
                HALF_SIZE * 2, HALF_SIZE * 2);
        assertTrue(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsNotIntersectingWithRectangleDiagonally() {
        final double exitIntersection = CIRCLE_RECTANGLE_DIAGONAL_INTESECTION + 0.1;
        final Shape rectangle = new Rectangle(
                new Vector2D(exitIntersection, exitIntersection),
                HALF_SIZE * 2, HALF_SIZE * 2);
        assertFalse(this.isCircleIntersecting(rectangle));
    }

    private boolean isCircleIntersecting(final Shape shape) {
        final Shape circle = new Circle(new Vector2D(0, 0), HALF_SIZE);
        return circle.isColliding(shape);
    }

    private boolean isRectangleIntersecting(final Shape shape) {
        final Shape rectangle = new Rectangle(new Vector2D(0, 0), HALF_SIZE * 2, HALF_SIZE * 2);
        return rectangle.isColliding(shape);
    }

}
