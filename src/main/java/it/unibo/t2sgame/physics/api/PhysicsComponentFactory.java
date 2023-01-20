package it.unibo.t2sgame.physics.api;

import it.unibo.t2sgame.common.Vector2D;

public interface PhysicsComponentFactory {
    
    PhysicsComponent createRectanglePhyisicsComponent(Vector2D min, Vector2D max);

    PhysicsComponent createCirclePhyisicsComponent(double radius);

}
