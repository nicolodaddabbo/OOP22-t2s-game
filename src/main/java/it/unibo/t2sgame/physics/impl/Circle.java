package it.unibo.t2sgame.physics.impl;

import it.unibo.t2sgame.physics.api.Shape;

public class Circle implements Shape {

    @Override
    public boolean isColliding(Shape s) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isColliding(Circle c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isColliding(Rectangle r) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
