package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.physics.api.Shape;

public class Circle implements Shape {
    private Vector2D center;
    private double radius;

    public Circle(final double radius) {
        this.radius = radius;
    }

    public Vector2D getCenter() {
        return this.center;
    }

    public void setCenter(final Vector2D center) {
        this.center = center;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(final double radius) {
        this.radius = radius;
    }

    @Override
    public boolean isColliding(Circle circle) {
        return this.center.distance(circle.getCenter()) <= this.radius+circle.getRadius();
    }

    @Override
    public boolean isColliding(Rectangle r) {
        return false;
    }
    
}
