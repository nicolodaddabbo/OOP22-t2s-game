package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.physics.api.Shape;

public class Rectangle implements Shape{
    private Vector2D center;
    private Vector2D size;
    private Vector2D min;
    private Vector2D max;

    public Rectangle(final Vector2D min, final Vector2D max){
        this.min = min;
        this.max = max;
        this.center = min.sum(max).mul(0.5);
        this.size = max.sub(min);
    }

    public Vector2D getCenter() {
        return this.center;
    }

    public void setCenter(Vector2D center) {
        this.center = center;
    }

    public Vector2D getMin(){
        return this.min;
    }

    public Vector2D getMax(){
        return this.max;
    }

    @Override
    public boolean isColliding(Circle circle) {
        return false;
    }

    @Override
    public boolean isColliding(Rectangle rectangle) {
        return false;
    }
    
}
