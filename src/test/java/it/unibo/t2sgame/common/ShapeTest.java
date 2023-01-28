package it.unibo.t2sgame.common;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.shapes.Circle;
import it.unibo.t2sgame.common.shapes.Rectangle;

public class ShapeTest {
    
    @Test
    void testCircleIsIntersectingWithCircleInXAxis() {
        Shape circle = new Circle(new Vector2D(3.9, 0), 2);
        assertTrue(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsIntersectingWithCircleInYAxis() {
        Shape circle = new Circle(new Vector2D(0, 3.9), 2);
        assertTrue(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsNotIntersectingWithCircleInXAxis() {
        Shape circle = new Circle(new Vector2D(4, 0), 2);
        assertFalse(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsNotIntersectingWithCircleInYAxis() {
        Shape circle = new Circle(new Vector2D(0, 4), 2);
        assertFalse(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsIntersectingWithCircleDiagonally() {
        Shape circle = new Circle(new Vector2D(2.8, 2.8), 2);
        assertTrue(this.isCircleIntersecting(circle));
    }

    @Test
    void testCircleIsNotIntersectingWithCircleDiagonally() {
        Shape circle = new Circle(new Vector2D(2.9, 2.9), 2);
        assertFalse(this.isCircleIntersecting(circle));
    }

    @Test
    void testRectangleIsIntersectingWithRectangleInXAxis() {
        Shape rectangle = new Rectangle(new Vector2D(3.9, 0), 4, 4);
        assertTrue(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsIntersectingWithRectangleInYAxis() {
        Shape rectangle = new Rectangle(new Vector2D(0, 3.9), 4, 4);
        assertTrue(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsNotIntersectingWithRectangleInXAxis() {
        Shape rectangle = new Rectangle(new Vector2D(4, 0), 4, 4);
        assertFalse(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsNotIntersectingWithRectangleInYAxis() {
        Shape rectangle = new Rectangle(new Vector2D(0, 4), 4, 4);
        assertFalse(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsIntersectingWithRectangleDiagonally() {
        Shape rectangle = new Rectangle(new Vector2D(3.9, 3.9), 4, 4);
        assertTrue(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testRectangleIsNotIntersectingWithRectangleDiagonally() {
        Shape rectangle = new Rectangle(new Vector2D(4, 4), 4, 4);
        assertFalse(this.isRectangleIntersecting(rectangle));
    }

    @Test
    void testCircleIsIntersectingWithRectangleInXAxis() {
        Shape rectangle = new Rectangle(new Vector2D(3.9, 0), 4, 4);
        assertTrue(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsIntersectingWithRectangleInYAxis() {
        Shape rectangle = new Rectangle(new Vector2D(0, 3.9), 4, 4);
        assertTrue(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsNotIntersectingWithRectangleInXAxis() {
        Shape rectangle = new Rectangle(new Vector2D(4, 0), 4, 4);
        assertFalse(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsNotIntersectingWithRectangleInYAxis() {
        Shape rectangle = new Rectangle(new Vector2D(0, 4), 4, 4);
        assertFalse(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsIntersectingWithRectangleDiagonally() {
        Shape rectangle = new Rectangle(new Vector2D(3.4, 3.4), 4, 4);
        assertTrue(this.isCircleIntersecting(rectangle));
    }

    @Test
    void testCircleIsNotIntersectingWithRectangleDiagonally() {
        Shape rectangle = new Rectangle(new Vector2D(3.5, 3.5), 4, 4);
        assertFalse(this.isCircleIntersecting(rectangle));
    }

    private boolean isCircleIntersecting(final Shape shape){
        Shape circle = new Circle(new Vector2D(0, 0), 2);
        return circle.isColliding(shape);
    }

    private boolean isRectangleIntersecting(final Shape shape){
        Shape rectangle = new Rectangle(new Vector2D(0, 0), 4, 4);
        return rectangle.isColliding(shape);
    }

}
