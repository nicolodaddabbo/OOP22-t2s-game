package it.unibo.t2sgame.physics.api;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.model.api.Component;

public interface PhysicsComponent extends Component {

    /**
     * Updates the position of the passed entity if it has one.
     * @param entity the entity to update
     */
    @Override
    void update();

    /**
     * 
     * @return the converted speed of the entity
     */
    double getConvertedSpeed();

    /**
     * 
     * @return the velocity of the entity
     */
    Vector2D getVelocity();

    /**
     * 
     * @param velocity the velocity to set
     */
    void setVelocity(Vector2D velocity);

    /**
     * 
     * @return the speed of the entity
     */
    double getSpeed();

    /**
     * 
     * @param speed the speed to set
     */
    void setSpeed(double speed);

}
