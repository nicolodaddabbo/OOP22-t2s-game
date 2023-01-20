package it.unibo.t2sgame.physics.impl;

import java.util.Vector;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.physics.api.BoundingBox;

public class CircleBoundingBox implements BoundingBox {
    private Vector2D center;
    private double radius;

    public CircleBoundingBox(final double radius) {
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

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(final double radius) {
        this.radius = radius;
    }

    @Override
    public boolean isContaining(Vector2D point) {
        return this.center.distance(point) <= this.radius;
    }

    @Override
    public boolean isColliding(Vector2D center, double radius) {
        return this.center.distance(center) <= radius+this.radius;
    }

    @Override
    public boolean isColliding(Vector2D center, Vector2D min, Vector2D max) {
        return false;
    }
    
}
