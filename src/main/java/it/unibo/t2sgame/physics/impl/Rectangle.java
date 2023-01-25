package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.physics.api.Shape;

public class Rectangle implements Shape{
    private Vector2D center;
    private double width;
    private double height;

    public Rectangle(final Vector2D center, final double width, final double height){
        this.center = center;
        this.width = width;
        this.height = height;
    }

    public Vector2D getCenter() {
        return this.center;
    }

    @Override
    public void setCenter(final Vector2D center) {
        this.center = center;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(final double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    @Override
    public boolean isColliding(final Shape shape) {
        return shape.isColliding(this);
    }

    @Override
    public boolean isColliding(final Circle circle) {
        return circle.isColliding(this);
    }

    @Override
    public boolean isColliding(final Rectangle rectangle) {
        return Math.abs(this.center.getX()-rectangle.getCenter().getX()) < (this.width + rectangle.getWidth())/2 
            && Math.abs(this.center.getY()-rectangle.getCenter().getY()) < (this.height + rectangle.getHeight())/2;
    }
    
}
