package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.physics.api.BoundingBox;

public class RectangleBoundingBox implements BoundingBox {
    private Vector2D center;
    private Vector2D size;
    private Vector2D min;
    private Vector2D max;

    public RectangleBoundingBox(final Vector2D min, final Vector2D max){
        this.min = min;
        this.max = max;
        this.center = min.sum(max).mul(0.5);
        this.size = max.sub(min);
    }

    @Override
    public Vector2D getCenter() {
        return this.center;
    }

    @Override
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
    public boolean isContaining(Vector2D point) {
        return this.min.getX() <= point.getX() && this.min.getY() <= point.getY() 
            && this.max.getX() >= point.getX() && this.max.getY() >= point.getY();
    }

    @Override
    public boolean isColliding(Vector2D center, double radius) {
        return false;
    }

    @Override
    public boolean isColliding(Vector2D center, Vector2D min, Vector2D max) {
        return false;
    }
    
}
